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
public class Customer implements SearchInterface{
    
    private String lastName;
    private String firstName;
    private String ssn;
    private String username;
    private String password;
    private ArrayList<BasicCreditCard> creditCards;
    
    /**
     * Customer is a constructor that takes 5 arguments and sets them equal to fields stored in this class
     * @param l This is the last name
     * @param f This is the first name
     * @param s This is the SSN
     * @param u This is the username
     * @param p This is the password
     * @throws InvalidCustomerException This throws an exception when the method isValidPassword returns false
     */
    public Customer(String l, String f, String s, String u, String p) throws InvalidCustomerException
    {
        creditCards = new ArrayList<>();
        lastName = l;
        firstName = f;
        ssn = s;
        username = u;
        
        if(isValidPassword(p))
            password = p;
        else
            throw new InvalidCustomerException(u, p);
        creditCards = new ArrayList<>();
    }
    
    /**
     * toString creates a string with the last name, first name, SSN, and username
     * @return This returns the string of values
     */
    public String toString()
    {
        String str = String.format("%s, %s, %s, %s ",lastName, firstName, ssn, username);
        return str;
    }
    
    /**
     * equals compares the getId methods of two Customers
     * @param c This is the other customer object
     * @return 
     */
    public boolean equals(Customer c)
    {
        if(this.getId().equals(c.getId()))
            return true;
        else
            return false;
    }
    
    /**
     * getId gives you the SSN
     * @return This returns the String of instance field ssn
     */
    public String getId()
    {
        return ssn;
    }
    
    /**
     * getViewString gives you a string of a customer and all their credit cards
     * @return This returns the String of the customer and all cards in a certain format
     */
    public String getViewString()
    {
        String str = String.format("First name: %s, Last name: %s, Username: %s\nList of Cards:\n",firstName, lastName, username);
        for(BasicCreditCard c : creditCards)
        {
            str += c + String.format("\n");
        }
        return str;
    }
    
    /**
     * addCard adds a credit card to the list of credit cards
     * @param card This is the credit card to be added
     */
    public void addCard(BasicCreditCard card)
    {
        creditCards.add(card);
    }
    
    /**
     * isValidPaddword checks that the password fits the correct parameters of 8-10 characters, 
     * at least one uppercase letter, at least one lowercase letter, and at least one digit
     * @param password This is the password given to check
     * @return This returns a boolean true if the password fits the parameters and false if it doesn't
     */
    public boolean isValidPassword(String password)
    {
        if(password.length() >= 8 && password.length() <= 10)
        {
            for(int i = 0; i < password.length(); i++)
            {
                if(Character.isLowerCase(password.charAt(i)))
                {
                    for(int j = 0; j < password.length(); j++)
                    {
                        if(Character.isUpperCase(password.charAt(j)))
                        {
                            for(int x = 0; x < password.length(); x++)
                            {
                                if(Character.isDigit(password.charAt(x)))
                                {
                                    for(int y = 0; y < password.length(); y++)
                                    {
                                        if(!Character.isWhitespace(password.charAt(y)))
                                            return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    
    /**
     * getTotalAmountOwed gives you the sum of all balances from all the cards of the customer
     * @return This returns a float of the total amount of money owed
     */
    public float getTotalAmountOwed()
    {
        float total = 0;
        
        for(BasicCreditCard c : creditCards)
        {
            total += c.getAmountOwed();
        }
        
        return total;
    }
    /**
     * getCards gives you the list of credit cards
     * @return This returns the list of credit cards
     */
    public ArrayList<BasicCreditCard> getCards(){ return creditCards; }
    
    /**
     * getUsername gives you the username instance field
     * @return This returns the String of username
     */
    public String getUsername(){ return username; }
    
    /**
     * getSSN gives you the ssn instance field
     * @return This returns the String of ssn
     */
    public String getSSN() { return ssn; }
    
    /**
     * getLastName gives you the last name of the customer
     * @return This returns a String of the last name
     */
    public String getLastName()
    {
        return lastName;
    }
    
    
}
