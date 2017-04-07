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
public class StudyRoom extends RoomDecorator {
    
    MyHome home;
    int numberOf;
    
    public StudyRoom(MyHome home, int numberOf) {
        this.home = home;
        this.numberOf = numberOf;
        
        if (numberOf > 1) {
            description = ", " + numberOf + " study rooms";
        } else if (numberOf == 1) {
            description = ", " + numberOf + " study room";
        } else {
            description = ", no study room";
        }
    }
    
    @Override
    public String getDescription() {
        return home.getDescription() + description;
    }
    
    @Override
    public int squareFeet() {
        return home.squareFeet() + (100 * numberOf);
    }
    
    @Override 
    public int cost() {
        return home.cost() + (3000 * numberOf);
    }
}
