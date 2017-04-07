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
public class BasicHome extends MyHome {
    
    public BasicHome() {
        description = "Basic Home with 1 living room";
    }
    
    @Override
    public int squareFeet() {
        return 256;
    }
    
    @Override 
    public int cost() {
        return 7000;
    }
    
}
