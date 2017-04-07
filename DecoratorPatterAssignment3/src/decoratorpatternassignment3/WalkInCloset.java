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
public class WalkInCloset extends RoomDecorator {
    
    MyHome home;
    int numberOf;
    
    public WalkInCloset(MyHome home, int numberOf) {
        this.home = home;
        this.numberOf = numberOf;
        
        if (numberOf > 1) {
            description = ", " + numberOf + " walk in closets";
        } else if (numberOf == 1) {
            description = ", " + numberOf + " walk in closet";
        } else {
            description = ", no walk in closet";
        }
    }
    
    @Override
    public String getDescription() {
        return home.getDescription() + description;
    }
    
    @Override
    public int squareFeet() {
        return home.squareFeet() + (106 * numberOf);
    }
    
    @Override 
    public int cost() {
        return home.cost() + (3000 * numberOf);
    }
}
