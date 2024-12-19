/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package final_project;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

/**
 *
 * @author Daniel Polgun
 */
public class option1Frame extends JFrame{
    
    private JTextField fileNameField;
    private String UPLOAD_BUTTON_TEXT = "Upload";
    private String CANCEL_BUTTON_TEXT = "Cancel";
    private Accounts accounts;
    
    /**
     * option1Frame is a constructor that sets certain values to the GUI 
     * @param accounts This is the object that stores all the customers
     */
    public option1Frame(Accounts accounts)
    {
        setSize(200,150);
        setLocationRelativeTo(null);
        setTitle("Upload Records");
        
        createPanel();
        
        setVisible(true);
        this.accounts = accounts;
    }
    
    /**
     * createPanel creates the visible and interactive elements in the GUI and adds them to the frame
     */
    public void createPanel()
    {
        JLabel title = new JLabel("Upload Customer Records");
        JLabel enterFileLabel = new JLabel("Enter file name");
        fileNameField = new JTextField(10);
        
        JButton submit = new JButton(UPLOAD_BUTTON_TEXT);
        JButton cancel = new JButton(CANCEL_BUTTON_TEXT);
        
        JPanel panel = new JPanel();
        panel.add(title);
        panel.add(enterFileLabel);
        panel.add(fileNameField);
        panel.add(cancel);
        panel.add(submit);
        add(panel);
        
        submit.addActionListener(new ButtonListener());
        cancel.addActionListener(new ButtonListener());
    }
    
    private class ButtonListener implements ActionListener
    {
        /**
         * actionPerformed listens for the submit or cancel button to be clicked 
         * and either closes the window or adds customers from a file
         * @param e This is the ActionEvent that was heard
         */
        public void actionPerformed(ActionEvent e) 
        {
            String cmd = e.getActionCommand();
            
            if(cmd.equals(UPLOAD_BUTTON_TEXT))
            {
                fileReader();
            }
            else if(cmd.equals(CANCEL_BUTTON_TEXT))
            {
                dispose();
            }
        }
        
        /**
         * fileReader reads in the file that was put into the text field and checks if the file exists. 
         * If it exists, the file reads in everything and checks if all values are right, then creates Customer and Credit Card objects.
         * This method then adds the cards to their customers and displays a message of success or a message of incorrect lines in the file 
         * while adding the correct ones to the list of customers
         */
        public void fileReader()
        {
            try
                {
                    File f = new File(fileNameField.getText());
                    Scanner infile = new Scanner(f);
                    int count = 0;
                    ArrayList<String> invalidLines = new ArrayList<>();
                    
                    
                    while(infile.hasNext())
                    {
                        try
                        {
                        count++;
                        String line = infile.nextLine();
                        String[] lineArray = line.split(", ");
                        boolean exists = false;
                        
                        if(lineArray.length == 12)
                        {
                            String lName = lineArray[0];
                            String fName = lineArray[1];
                            String ssn = lineArray[2];
                            String username = lineArray[3];
                            String password = lineArray[4];
                            int cardNum = Integer.valueOf(lineArray[5]);
                            String cardType = lineArray[6];
                            float limit = Float.valueOf(lineArray[7]);
                            float interest = Float.valueOf(lineArray[8]);
                            float annualFee = Float.valueOf(lineArray[9]);
                            float rewardPercent = Float.valueOf(lineArray[10]);
                            float balance = Float.valueOf(lineArray[11]);
                            
                            for(Customer c : accounts.getCustomerList())
                            {
                                if(c.getId().equals(ssn))
                                {
                                    exists = true;
                                    break;
                                }
                            }
                            
                            Customer cust = new Customer(lName, fName, ssn, username, password);
                            BasicCreditCard card = new BenefitCreditCard(cardType, cardNum, limit, interest, balance, annualFee, rewardPercent);
                            
                            if(exists == true)
                            {
                                accounts.addCreditCard(cust, card);
                            }
                            else
                            {
                                accounts.addCustomer(cust);
                                cust.addCard(card);
                            }
                        }
                        else if(lineArray.length == 10)
                        {
                            String lName = lineArray[0];
                            String fName = lineArray[1];
                            String ssn = lineArray[2];
                            String username = lineArray[3];
                            String password = lineArray[4];
                            int cardNum = Integer.valueOf(lineArray[5]);
                            String cardType = lineArray[6].toUpperCase();
                            float limit = Float.valueOf(lineArray[7]);
                            float interest = Float.valueOf(lineArray[8]);
                            float balance = Float.valueOf(lineArray[9]);
                            
                            if(!cardType.equals("FREE"))
                                throw new InvalidCustomerException(count);
                            
                            Customer cust = new Customer(lName, fName, ssn, username, password);
                            BasicCreditCard card = new BasicCreditCard(cardType, cardNum, limit, interest, balance);
                            
                            if(exists == true)
                            {
                                accounts.addCreditCard(cust, card);
                            }
                            else
                            {
                                accounts.addCustomer(cust);
                                cust.addCard(card);
                            }
                        }
                        else
                            throw new InvalidCustomerException(count);
                        }
                        catch(InvalidCustomerException exc)
                        {
                            invalidLines.add("Line " + count + exc.getMessage());
                        }
                        catch(NumberFormatException formatExc)
                        {
                            invalidLines.add("Line " + count + " has invalid number format");
                        }
                    }
                    infile.close();
                    if(invalidLines.size() == 0)
                    {
                        JOptionPane.showMessageDialog(null, "Successful Upload!");
                        fileNameField.setText("");
                    }
                    else
                    {
                        String str = String.format("");
                        for(String s : invalidLines)
                        {
                            str += s + String.format("\n");
                        }
                        str += "\nEvery other valid line was uploaded successfully!";
                        JOptionPane.showMessageDialog(null, str);
                    }
                }
                catch(IOException ex)
                {
                    JOptionPane.showMessageDialog(null, "File not found. Try again.");
                }
        }
        
    }
    
    
}
