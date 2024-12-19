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
public class option2Frame extends JFrame{
    
    private String ADD_BUTTON_TEXT = "Add";
    private String CANCEL_BUTTON_TEXT = "Cancel";
    
    private JTextField first;
    private JTextField last;
    private JTextField SSN;
    private JTextField username;
    private JTextField password;
    private JTextField confirmPassword;
    
    private Accounts accounts;
    
    /**
     * option2Frame is a constructor that sets certain values to the GUI 
     * @param accounts This is the object that stores all the customers
     */
    public option2Frame(Accounts accounts)
    {
        setSize(300,275);
        setLocationRelativeTo(null);
        setTitle("Add New Customer");
        setLayout(new BorderLayout());
        
        createPanel();
        
        setVisible(true);
        this.accounts = accounts;
    }
    
    /**
     * createPanel creates the visible and interactive elements in the GUI and adds them to the frame
     */
    public void createPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8,2));
        
        first = new JTextField(10);
        last = new JTextField(10);
        SSN = new JTextField(10);
        username = new JTextField(10);
        password = new JTextField(10);
        confirmPassword = new JTextField(10);
        
        add(new JLabel("Add New Customer", SwingConstants.CENTER), BorderLayout.NORTH);
        
        panel.add(new JLabel("First Name:", SwingConstants.CENTER));
        panel.add(first);
        panel.add(new JLabel("Last Name:", SwingConstants.CENTER));
        panel.add(last);
        panel.add(new JLabel("SSN:", SwingConstants.CENTER));
        panel.add(SSN);
        panel.add(new JLabel("Username:", SwingConstants.CENTER));
        panel.add(username);
        panel.add(new JLabel("Password:", SwingConstants.CENTER));
        panel.add(password);
        panel.add(new JLabel("Confirm Password:", SwingConstants.CENTER));
        panel.add(confirmPassword);
        
        JButton addButton = new JButton(ADD_BUTTON_TEXT);
        JButton cancel = new JButton(CANCEL_BUTTON_TEXT);
        
        JPanel panel2 = new JPanel();
        
        panel2.add(cancel);
        panel2.add(addButton);
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
         * and either closes the window or adds a customer
         * @param e This is the ActionEvent that was heard
         */
        public void actionPerformed(ActionEvent e)
        {
            String cmd = e.getActionCommand();
            
            if(cmd.equals(ADD_BUTTON_TEXT))
            {
                checkInputs();
            }
            else if(cmd.equals(CANCEL_BUTTON_TEXT))
            {
                dispose();
            }
        }
        
        /**
         * checkInputs checks all text fields to make sure they meet criteria for creating a customer.
         * If the fields are invalid, it sets those boxes red.
         * When a customer is successfully added it clears the text fields for another customer to be added.
         */
        public void checkInputs()
        {
            try
            {
                for(Customer c : accounts.getCustomerList())
                {
                    if(c.getUsername().equals(username.getText()) || username.getText().equals(""))
                    {
                        password.setBackground(Color.WHITE);
                        confirmPassword.setBackground(Color.WHITE);
                        throw new InvalidCustomerException(username.getText());
                    }
                        
                }
                username.setBackground(Color.WHITE);
                
                try
                {
                    if(!password.getText().equals(confirmPassword.getText()))
                    {
                        throw new InvalidCustomerException(username.getText(), password.getText());
                    }
                    confirmPassword.setBackground(Color.WHITE);
                
                    try
                    {
                        Customer cust = new Customer(last.getText(), first.getText(), SSN.getText(), username.getText(), password.getText());
                        accounts.addCustomer(cust);

                        password.setBackground(Color.WHITE);
                        confirmPassword.setBackground(Color.WHITE);
                        JOptionPane.showMessageDialog(null, "Customer successfully added!");
                        first.setText("");
                        last.setText("");
                        SSN.setText("");
                        username.setText("");
                        password.setText("");
                        confirmPassword.setText("");
                    }
                    catch(InvalidCustomerException eee)
                    {
                        password.setBackground(Color.RED);
                        confirmPassword.setBackground(Color.WHITE);
                    }
                }
                catch(InvalidCustomerException ee)
                {
                    password.setBackground(Color.WHITE);
                    confirmPassword.setBackground(Color.RED);
                }
            }
            catch(InvalidCustomerException e)
            {
                username.setBackground(Color.RED);
            }
        }
    }
    
}
