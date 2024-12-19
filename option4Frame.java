/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package final_project;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 *
 * @author Daniel Polgun
 */
public class option4Frame extends JFrame{
    
    private String SUBMIT_BUTTON_TEXT = "Submit";
    private String CANCEL_BUTTON_TEXT = "Cancel";
    private JTextField viewField;
    private Accounts accounts;
    
    /**
     * option4Frame is a constructor that sets certain values to the GUI.
     * @param accounts This is the object that stores all the customers
     */
    public option4Frame(Accounts accounts)
    {
        this.accounts = accounts;
        
        setSize(200,150);
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
        JLabel title = new JLabel("View Information", SwingConstants.CENTER);
        JLabel heading = new JLabel("Type SSN or Card Number", SwingConstants.CENTER);
        
        viewField = new JTextField(10);
        
        JButton submit = new JButton(SUBMIT_BUTTON_TEXT);
        JButton cancel = new JButton(CANCEL_BUTTON_TEXT);
        
        JPanel panel = new JPanel();
        JPanel panel2 = new JPanel();
        
        panel.add(heading);
        panel.add(viewField);
        panel2.add(cancel);
        panel2.add(submit);
        
        add(title, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        add(panel2, BorderLayout.SOUTH);
        
        ButtonListener bl = new ButtonListener();
        submit.addActionListener(bl);
        cancel.addActionListener(bl);
        
    }
    
    private class ButtonListener implements ActionListener
    {
        /**
         * actionPerformed listens for the submit or cancel button to be clicked 
         * and either closes the window or displays information based on what was typed in
         * @param e This is the ActionEvent that was heard
         */
        public void actionPerformed(ActionEvent e)
        {
            String cmd = e.getActionCommand();
            
            if(cmd.equals(SUBMIT_BUTTON_TEXT))
            {
                findInfo();
            }
            else if(cmd.equals(CANCEL_BUTTON_TEXT))
            {
                dispose();
            }
        }
        
        /**
         * findInfo displays a pop-up based on if an SSN or Card number was typed in. 
         * It shows information in a certain format for each choice. 
         * If the input was invalid, it displays a pop-up showing what can be input.
         */
        public void findInfo()
        {
            String choice = viewField.getText();
            
            if(choice.length() == 6)
            {
                BasicCreditCard returned = accounts.getCreditCard(Integer.valueOf(choice));
                if(returned == null)
                    JOptionPane.showMessageDialog(null, "Card does not exist.");
                else
                    JOptionPane.showMessageDialog(null, returned.getViewString());
            }
            else if(choice.length() == 11)
            {
                Customer returned = accounts.getCustomerBySSN(choice);
                if(returned == null)
                    JOptionPane.showMessageDialog(null, "Customer does not exist.");
                else
                    JOptionPane.showMessageDialog(null, returned.getViewString());
            }
            else
                JOptionPane.showMessageDialog(null, "Neither SSN nor Card Number was entered. Card number is 6 digits, SSN is 11 (including dashes). Try again.");
        }
    }
}
