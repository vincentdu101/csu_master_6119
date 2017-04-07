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
public class Bathroom extends RoomDecorator {
    
    MyHome home;
    int numberOf;
    
    public Bathroom(MyHome home, int numberOf) {
        this.home = home;
        this.numberOf = numberOf;
        
        if (numberOf > 1) {
            description = ", " + numberOf + " bathrooms";
        } else if (numberOf == 1) {
            description = ", " + numberOf + " bathroom";
        } else {
            description = ", no bathroom";
        }
    }
    
    @Override
    public String getDescription() {
        return home.getDescription() + description;
    }
    
    @Override
    public int squareFeet() {
        return home.squareFeet() + (115 * numberOf);
    }
    
    @Override 
    public int cost() {
        return home.cost() + (15000 * numberOf);
    }
}
