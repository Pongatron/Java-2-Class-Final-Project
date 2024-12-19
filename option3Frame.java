/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package final_project;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Random;
import java.util.ArrayList;

/**
 *
 * @author Daniel Polgun
 */
public class option3Frame extends JFrame{
    
    private String ADD_BUTTON_TEXT = "Add";
    private String CANCEL_BUTTON_TEXT = "Cancel";
    private String FREE_CARD_TEXT = "FREE";
    private String REWARDS_CARD_TEXT = "REWARDS";
    private String PLATINUM_CARD_TEXT = "PLATINUM";
    private JTextField usernameField;
    private JTextField limitField;
    private JTextField interestField;
    private JRadioButton freeRadio;
    private JRadioButton rewardsRadio;
    private JRadioButton platinumRadio;
    private ButtonGroup bg;
    private JLabel cardNumberField;
    private Accounts accounts;
    private Random rand;
    private int cardNum;
    float interest;
    
    /**
     * option3Frame is a constructor that sets certain values to the GUI. It also sets the interest and card number.
     * @param accounts This is the object that stores all the customers
     */
    public option3Frame(Accounts accounts)
    {
        this.accounts = accounts;
        rand = new Random();
        cardNum = accounts.getCustomerList().get(0).getCards().get(0).getNextAvailableCreditCardNum();
        interest = (float)((rand.nextInt(9) + 1) / 100.0);
        
        setSize(500,275);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        
        createPanel();
        
        setVisible(true);
    }
    
    /**
     * createPanel creates the visible and interactive elements in the GUI and adds them to the frame.
     */
    public void createPanel()
    {
        JLabel title = new JLabel("Add New Credit Card", SwingConstants.CENTER);
        JLabel username = new JLabel("Username:", SwingConstants.CENTER);
        JLabel cardType = new JLabel("Card Type:", SwingConstants.CENTER);
        JLabel limit = new JLabel("Desired Limit:", SwingConstants.CENTER);
        JLabel cardNumberLabel = new JLabel("Card Number:", SwingConstants.CENTER);
        cardNumberField = new JLabel(String.valueOf(cardNum), SwingConstants.CENTER);
        JLabel interestLabel = new JLabel("Interest Rate:", SwingConstants.CENTER);
        
        usernameField = new JTextField(10);
        
        bg = new ButtonGroup();
        freeRadio = new JRadioButton(FREE_CARD_TEXT);
        rewardsRadio = new JRadioButton(REWARDS_CARD_TEXT);
        platinumRadio = new JRadioButton(PLATINUM_CARD_TEXT);
        bg.add(freeRadio);
        bg.add(rewardsRadio);
        bg.add(platinumRadio);
        
        limitField = new JTextField(10);
        interestField = new JTextField(10);
        interestField.setEditable(false);
        interestField.setText(String.valueOf(interest));
        
        JButton addButton = new JButton(ADD_BUTTON_TEXT);
        JButton cancel = new JButton(CANCEL_BUTTON_TEXT);
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6,2));
        
        JPanel panel2 = new JPanel();
        panel2.add(cancel);
        panel2.add(addButton);
        
        JPanel radioPanel = new JPanel();
        radioPanel.add(freeRadio);
        radioPanel.add(rewardsRadio);
        radioPanel.add(platinumRadio);
        
        panel.add(cardNumberLabel);
        panel.add(cardNumberField);
        panel.add(username);
        panel.add(usernameField);
        panel.add(cardType);
        panel.add(radioPanel);
        panel.add(limit);
        panel.add(limitField);
        panel.add(interestLabel);
        panel.add(interestField);
        
        add(title, BorderLayout.NORTH);
        add(panel);
        add(panel2, BorderLayout.SOUTH);
        
        ButtonListener bl = new ButtonListener();
        addButton.addActionListener(bl);
        cancel.addActionListener(bl);
    }
    private class ButtonListener implements ActionListener
    {
        /**
         * actionPerformed listens for the add or cancel button to be clicked 
         * and either closes the window or adds a credit card
         * @param e This is the ActionEvent that was heard
         */
        public void actionPerformed(ActionEvent e)
        {
            String cmd = e.getActionCommand();
            
            if(cmd.equals(ADD_BUTTON_TEXT))
            {
                createCard();
            }
            else if(cmd.equals(CANCEL_BUTTON_TEXT))
            {
                dispose();
            }
        }
        
        /**
         * createCard takes user inputs to create a credit card based on selecting free, rewards, or platinum.
         * This method checks if the username exists, the card type was selected, and the limit is valid, 
         * then displays a pop-up if something was invalid.
         * If everything is valid it displays a pop-up that tells you it was successfully added and sets all fields blank and the next card number and 
         */
        public void createCard()
        {
            ArrayList<String> errors = new ArrayList<>();
            
            try
            {
                String username = usernameField.getText();
                Customer customer = accounts.getCustomer(username);
                
                float limit = Float.valueOf(limitField.getText());                
                
                if(freeRadio.isSelected())
                {
                    String cardType = REWARDS_CARD_TEXT;
                    
                    BasicCreditCard card = new BasicCreditCard(cardType, cardNum, limit, interest, 0);
                    customer.addCard(card);
                }
                else if(rewardsRadio.isSelected())
                {
                    String cardType = REWARDS_CARD_TEXT;
                    float annualFee = (rand.nextInt(50) + 50);
                    float rewardPercent = (float)((rand.nextInt(5) + 1) / 100.0);
                    
                    BasicCreditCard card = new BenefitCreditCard(cardType, cardNum, limit, interest, 0, annualFee, rewardPercent);
                    customer.addCard(card);
                }
                else if(platinumRadio.isSelected())
                {
                    String cardType = PLATINUM_CARD_TEXT;
                    float annualFee = (rand.nextInt(50) + 50);
                    float rewardPercent = (float)((rand.nextInt(5) + 1) / 100.0);
                    
                    BasicCreditCard card = new BenefitCreditCard(cardType, cardNum, limit, interest, 0, annualFee, rewardPercent);
                    customer.addCard(card);
                }
                else
                    errors.add("You must select a card type");
            }
            catch(InvalidCustomerException ee)
            {
                errors.add(ee.getMessage());
            }
            catch(NumberFormatException eee)
            {
                errors.add("Desired Limit field must be number");
            }
            
            if(errors.size() == 0)
            {
                JOptionPane.showMessageDialog(null, "Card Added Successfully!");
                cardNum = accounts.getCustomerList().get(0).getCards().get(0).getNextAvailableCreditCardNum();
                interest = (float)((rand.nextInt(9) + 1) / 100.0);
                bg.clearSelection();
                
                cardNumberField.setText(String.valueOf(cardNum));
                usernameField.setText("");
                limitField.setText("");
                interestField.setText(String.valueOf(interest));
            }
            else
            {
                System.out.println(errors.size());
                String str = String.format("");
                for(String s : errors)
                {
                    System.out.println("bruh");
                    str += s + String.format("\n");
                }
                JOptionPane.showMessageDialog(null, str);
            }
        }
    }
    
}
