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
public class BasicCreditCard{
    
    enum CreditCardType {FREE, REWARDS, PLATINUM}
    
    private int cardNum;
    private CreditCardType cardType;
    private float limit;
    private float interest;
    private float balance;
    private static int nextCardNum = 0;
    
    /**
     * BasicCreditCard is a constructor that takes 5 arguments and sets them equals to things stored in the class
     * @param cType This is the card type
     * @param cardNum This is the card number
     * @param limit This is the credit card limit
     * @param interest This is the interest
     * @param balance This is the balance or amount owed on the card
     * @throws InvalidCustomerException This throws an exception if the limit is below 0, the balance is below 0, or when the interest isn't between 0 and 1
     */
    public BasicCreditCard(String cType, int cardNum, float limit, float interest, float balance) throws InvalidCustomerException
    {
        cardType = CreditCardType.valueOf(cType.toUpperCase());
        this.cardNum = cardNum;
        
        if(limit < 0)
            throw new InvalidCustomerException("Limit", limit);
        this.limit = limit;
        
        if(balance < 0)
            throw new InvalidCustomerException("Balance", balance);
        this.balance = balance;
        
        if(interest < 0 || interest > 1)
            throw new InvalidCustomerException(interest, "Interest");
        this.interest = interest;
        
        if(cardNum >= nextCardNum)
            nextCardNum = cardNum + 1;
    }
    
    /**
     * toString gives a formated string of all credit card details
     * @return This returns The string of the formatted card
     */
    public String toString()
    {
        String str = String.format("Card Num: %d, Card Type: %s, Limit: %.2f, Interest: %.2f, Balance: %.2f", cardNum, cardType, limit, interest, balance);
        return str;
    }
    
    /**
     * getId gives you the String value of the card number
     * @return This returns a String of the card number
     */
    public String getId()
    {
        return String.valueOf(cardNum);
    }
    
    /**
     * getViewString gives you a formatted string of credit card information to be used when viewing a certain card
     * @return This returns the formatted String of card details
     */
    public String getViewString()
    {
        String str = String.format("Card Type: %s, Interest: %.2f, Limit: %.2f, Balance: %.2f", cardType, interest, limit, balance);
        return str;
    }
    
    /**
     * getNextAvailableCreditCardNum gives you the next available card number
     * @return This returns an int of the next card number available
     */
    public int getNextAvailableCreditCardNum()
    {
        return nextCardNum;
    }
    
    /**
     * getAmountOwed gives you the amount owed on the card
     * @return This returns the float of the balance / amount owed
     */
    public float getAmountOwed()
    {
        return balance;
    }
    
    /**
     * getCardNum gives you the card number of this card
     * @return This returns the int of the card number
     */
    public int getCardNum(){ return cardNum; }
    
}
