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
public class GUI extends JFrame{
    
    private String LABEL_1_TEXT = "1. Upload Customer Records from Database File";
    private String LABEL_2_TEXT = "2. Add New Customer";
    private String LABEL_3_TEXT = "3. Add New Credit Card";
    private String LABEL_4_TEXT = "4. View Information";
    private String LABEL_5_TEXT = "5. Download Statistics";
    private String SUBMIT_BUTTON_TEXT = "Submit";
    private JFrame option1Frame;
    private JFrame option2Frame;
    private JFrame option3Frame;
    private JFrame option4Frame;
    private JFrame option5Frame;
    private JRadioButton option1;
    private JRadioButton option2;
    private JRadioButton option3;
    private JRadioButton option4;
    private JRadioButton option5;
    private Accounts accounts;
    
    /**
     * GUI is a constructor that sets certain values to the GUI 
     */
    public GUI()
    {
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Credit Card Option Panel");
        setLayout(new BorderLayout(5, 5));
        
        createPanel();
        
        setVisible(true);
        accounts = new Accounts();
    }
    
    /**
     * createPanel creates the visible and interactable elements in the GUI and adds them to the frame
     */
    public void createPanel()
    {
        JLabel heading = new JLabel("Welcome to MCC’s Credit Card Company. Please choose from the following options:");
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        
        option1 = new JRadioButton(LABEL_1_TEXT);
        option2 = new JRadioButton(LABEL_2_TEXT);
        option3 = new JRadioButton(LABEL_3_TEXT);
        option4 = new JRadioButton(LABEL_4_TEXT);
        option5 = new JRadioButton(LABEL_5_TEXT);
        
        ButtonGroup bg = new ButtonGroup();
        bg.add(option1);
        bg.add(option2);
        bg.add(option3);
        bg.add(option4);
        bg.add(option5);
        
        JButton submit = new JButton(SUBMIT_BUTTON_TEXT);
        
        ButtonListener bl = new ButtonListener();
        submit.addActionListener(bl);
        
        JPanel radioPanel = new JPanel();
        radioPanel.setLayout(new GridLayout(5,1));
        
        add(heading, BorderLayout.NORTH);
        radioPanel.add(option1, BorderLayout.CENTER);
        radioPanel.add(option2, BorderLayout.CENTER);
        radioPanel.add(option3, BorderLayout.CENTER);
        radioPanel.add(option4, BorderLayout.CENTER);
        radioPanel.add(option5, BorderLayout.CENTER);
        add(submit, BorderLayout.SOUTH);
        
        add(radioPanel);
    }
    
    private class ButtonListener implements ActionListener
    {
        /**
         * actionPerformed creates certain panels based on while radio button was selected when clicking the submit button
         * @param e This is the ActionEvent that was heard
         */
        @Override public void actionPerformed(ActionEvent e)
        {
            String cmd = e.getActionCommand();
            
            try
            {
                if(cmd.equals(SUBMIT_BUTTON_TEXT))
                {
                    if(option1.isSelected())
                    {
                        option1Frame = new option1Frame(accounts);
                    }
                    else if(option2.isSelected())
                    {
                        option2Frame = new option2Frame(accounts);
                    }
                    else if(option3.isSelected())
                    {
                        option3Frame = new option3Frame(accounts);
                    }
                    else if(option4.isSelected())
                    {
                        option4Frame = new option4Frame(accounts);
                    }
                    else if(option5.isSelected())
                    {
                        option5Frame = new option5Frame(accounts);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "You must select an option");
                    }
                }
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(null, "You must upload a file first.");
            }
            
        }
    }
}
