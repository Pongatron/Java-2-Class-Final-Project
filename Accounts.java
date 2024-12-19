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
public class Accounts {
    
    private ArrayList<Customer> customers;
    
    /**
     * Accounts is a constructor that sets stored ArrayList customers to a new ArrayList
     */
    public Accounts()
    {
        customers = new ArrayList<>();
    }
    
    /**
     * addCustomer adds a Customer to the stored ArrayList
     * @param customer This is the Customer to add
     */
    public void addCustomer(Customer customer)
    {
        customers.add(customer);
    }
    
    /**
     * addCreditCard adds a credit card to a specified customer
     * @param customer This is the customer who the card is being added to
     * @param card This is the credit card being added
     */
    public void addCreditCard(Customer customer, BasicCreditCard card)
    {
        for(Customer c : customers)
        {
            if(c.equals(customer))
            {
                c.addCard(card);
            }
        }
    }
    
    /**
     * getCustomer gives you the Customer by searching for their username
     * @param username This is the username used to search for the customer
     * @return This returns the Customer
     * @throws InvalidCustomerException This throws an exception when a customer is not found
     */
    public Customer getCustomer(String username) throws InvalidCustomerException
    {
        boolean found = false;
        int location = 0;
        for(Customer c : customers)
        {
            if(c.getUsername().equals(username))
            {
                found = true;
                location = customers.indexOf(c);
            }
        }
        if(found)
            return customers.get(location);
        else
            throw new InvalidCustomerException(customers);
    }
    
    /**
     * getCustomerBySSN gives you the Customer by searching for their SSN
     * @param ssn This is the SSN used to search for the customer
     * @return This returns the customer
     */
    public Customer getCustomerBySSN(String ssn)
    {
        Customer cust = null;
        for(Customer c : customers)
        {
            if(c.getSSN().equals(ssn))
                cust = c;
        }
        return cust;
    }
    
    /**
     * getCreditCard gives you the credit card by searching for its card number
     * @param cardNum This is the card number used to search for the credit card
     * @return This returns the credit card
     */
    public BasicCreditCard getCreditCard(int cardNum)
    {
        BasicCreditCard card = null;
        for(int i = 0; i < customers.size(); i++)
        {
            for(int j = 0; j < customers.get(i).getCards().size(); j++)
            {
                if(cardNum == customers.get(i).getCards().get(j).getCardNum())
                {
                    card = customers.get(i).getCards().get(j);
                }
            }
        }
        return card;
    }
    
    /**
     * getCustomerList gives you the list of customers
     * @return This returns the ArrayList of customers
     */
    public ArrayList<Customer> getCustomerList(){ return customers; }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        GUI window = new GUI();
        
    }
    
}
