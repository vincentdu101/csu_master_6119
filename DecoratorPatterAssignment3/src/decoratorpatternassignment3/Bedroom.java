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
public class Bedroom extends RoomDecorator {
    
    MyHome home;
    int numberOf;
    
    public Bedroom(MyHome home, int numberOf) {
        this.home = home;
        this.numberOf = numberOf;
        
        if (numberOf > 1) {
            description = ", " + numberOf + " bedrooms";
        } else if (numberOf == 1) {
            description = ", " + numberOf + " bedroom";
        } else {
            description = ", no kitchen";
        }
    }
    
    @Override
    public String getDescription() {
        return home.getDescription() + description;
    }
    
    @Override
    public int squareFeet() {
        return home.squareFeet() + (231 * numberOf);
    }
    
    @Override 
    public int cost() {
        return home.cost() + (8000 * numberOf);
    }
}
