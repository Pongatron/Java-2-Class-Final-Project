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
public interface SearchInterface {
    /**
     * getId is a required method by whoever implements this interface that gets a certain String by any type of Id
     * @return This should return a String of the Id, whatever the Id might be
     */
    public String getId();
    /**
     * getViewString is a required method by whoever implements this interface that gives a String of things for Viewing
     * @return This should return a String of some formatted values to be viewed nicely
     */
    public String getViewString();
    
}
