/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package final_project;

import java.util.ArrayList;

/**
 *
 * @author Daniel Polgun
 */
public class InvalidCustomerException extends Exception{
    
    /**
     * InvalidCustomerException gives a message when there is a duplicate username
     * @param duplicateUsername This is the duplicate username
     */
    public InvalidCustomerException(String duplicateUsername)
    {
        super("Username " + duplicateUsername + " is invalid");
    }
    /**
     * InvalidCustomerException gives a message when a customer is not found in a list
     * @param list This is the ArrayList where the customer wasn't found
     */
    public InvalidCustomerException(ArrayList<Customer> list)
    {
        super("Customer not found");
    }
    /**
     * InvalidCustomerException gives a message when the password is invalid for a certain username
     * @param user This is the username
     * @param invalidPassword This is the invalid password
     */
    public InvalidCustomerException(String user, String invalidPassword)
    {
        super(String.format(" Invalid password for Username: '%s', Password: '%s'", user, invalidPassword));
    }
    /**
     * InvalidCustomerException gives a message when information is missing from a certain line in a file
     * @param lineCount This is the amount of information in the line
     */
    public InvalidCustomerException(int lineCount)
    {
        super(" is missing information.");
    }
    /**
     * InvalidCustomerException gives a message when a certain field needs to be greater than 0
     * @param field This is the type of field that needs to be greater than 0
     * @param entered This is the field that was incorrectly entered
     */
    public InvalidCustomerException(String field, float entered)
    {
        super(String.format(" %s must be 0 or higher. %s entered: '%.2f'", field, field, entered));
    }
    /**
     * InvalidCustomerException gives a message when a certain field needs to be between 0 and 1
     * @param entered This is the field incorrectly entered
     * @param field This is the type of field that needs to be between 0 and 1
     */
    public InvalidCustomerException(float entered, String field)
    {
        super(String.format(" %s must be between 0 and 1. %s entered: '%.2f'", field, field, entered));
    }
    
    
}
