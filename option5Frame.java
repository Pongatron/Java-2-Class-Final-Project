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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Daniel Polgun
 */
public class option5Frame extends JFrame{
    
    private String DOWNLOAD_BUTTON_TEXT = "Download";
    private String CANCEL_BUTTON_TEXT = "Cancel";
    private JTextField fileField;
    private Accounts accounts;
    
    /**
     * option5Frame is a constructor that sets certain values to the GUI.
     * @param accounts This is the object that stores all the customers
     */
    public option5Frame(Accounts accounts)
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
        JLabel title = new JLabel("Download Statistics", SwingConstants.CENTER);
        JLabel enterFileLabel = new JLabel("Enter file name");
        
        fileField = new JTextField(10);
        
        JButton download = new JButton(DOWNLOAD_BUTTON_TEXT);
        JButton cancel = new JButton(CANCEL_BUTTON_TEXT);
        
        JPanel panel = new JPanel();
        JPanel panel2 = new JPanel();
        
        panel.add(enterFileLabel);
        panel.add(fileField);
        panel2.add(cancel);
        panel2.add(download);
        
        add(title, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        add(panel2, BorderLayout.SOUTH);
        
        ButtonListener bl = new ButtonListener();
        download.addActionListener(bl);
        cancel.addActionListener(bl);
    }
    
    private class ButtonListener implements ActionListener
    {
        /**
         * actionPerformed listens for the download or cancel button to be clicked 
         * and either closes the window or writes a statistics file of every customer and card to a file
         * @param e This is the ActionEvent that was heard
         */
        public void actionPerformed(ActionEvent e)
        {
            String cmd = e.getActionCommand();
            
            if(cmd.equals(DOWNLOAD_BUTTON_TEXT))
            {
                createFile();
            }
            else if(cmd.equals(CANCEL_BUTTON_TEXT))
            {
                dispose();
            }
        }
        
        /**
         * createFile creates a file named whatever the user typed in and writes every customer and card to it. 
         * It first orders the file by last name, and then writes it again ordered by total amount owed.
         */
        public void createFile()
        {
            String filename = fileField.getText();
            ArrayList<Customer> list = accounts.getCustomerList();
            try
            {
                if(!filename.endsWith(".txt"))
                {
                    throw new Exception();
                }
                else
                {
                    FileWriter fw = new FileWriter(filename);
                    PrintWriter pw = new PrintWriter(fw);
                    
                    pw.println("Sorted by Last Name:");
                    
                    Collections.sort(list, (a, b) -> a.getLastName().compareTo(b.getLastName()));

                    for(Customer c : list)
                    {
                        pw.println(c);
                        for(BasicCreditCard b : c.getCards())
                        {
                            pw.printf("\t%s\n", b);
                        }
                    }
                    
                    pw.println("\nSorted by amount owed:");
                    
                    Collections.sort(list, (a, b) -> (int)a.getTotalAmountOwed() - (int)b.getTotalAmountOwed());
                    
                    for(Customer c : list)
                    {
                        pw.println(c);
                        for(BasicCreditCard b : c.getCards())
                        {
                            pw.printf("\t%s\n", b);
                        }
                    }
                    
                    fw.close();
                    pw.close();
                }
                JOptionPane.showMessageDialog(null, "File Successfully Created!");
                fileField.setText("");
            }
            catch(Exception eee)
            {
                JOptionPane.showMessageDialog(null, "Invalid File Name. Must end with '.txt'");
            }
        }
    }
    
}
