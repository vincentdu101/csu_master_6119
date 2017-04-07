/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package decoratorpatternassignment3;

/**
 *
 * @author vincentdu
 */
public abstract class MyHome {
    
    String description = "Standard House";
    
    public String getDescription() {
        return description;
    }
    
    public abstract int squareFeet();
    
    public abstract int cost();
    
}
