/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package final_project;

/**
 *
 * @author Daniel Polgun
 */
public class BenefitCreditCard extends BasicCreditCard{
    
    private float annualFee;
    private float rewardPercent;
    
    /**
     * BenefitCreditCard is a constructor that takes 7 arguments and passes 5 into their parent constructor 
     * and sets the rest to values stored in this class
     * @param cType This is the card type
     * @param cardNum This is the card number
     * @param limit This is the credit card limit
     * @param interest This is the card's interest rate
     * @param balance This is the card's remaining balance
     * @param annual This is the card's annual fee amount
     * @param reward This is the reward percentage of the card
     * @throws InvalidCustomerException This throws an exception if the reward percent isn't between 0 and 1
     */
    public BenefitCreditCard(String cType, int cardNum, float limit, float interest, float balance, float annual, float reward)throws InvalidCustomerException
    {
        super(cType, cardNum, limit, interest, balance);
        
        annualFee = annual;
        
        if(reward >= 0 && reward <= 1)
            rewardPercent = reward;
        else
            throw new InvalidCustomerException(reward, "Reward Percent");
        
    }
    
    /**
     * toString gives you a formatted String of all card details
     * @return This returns a formatted String of all card details
     */
    @Override public String toString()
    {
        String str = super.toString();
        str += String.format(", Annual Fee: %.2f, Reward Percent: %.2f", annualFee, rewardPercent);
        return str;
    }
    
    /**
     * getViewString gives you a formatted String of certain info when you view a specific card
     * @return This returns a formatted String of certain values
     */
    @Override public String getViewString()
    {
        String str = super.getViewString();
        str += String.format(", Annual Fee: %.2f", annualFee);
        return str;
    }
    
}
