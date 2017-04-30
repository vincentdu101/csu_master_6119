/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trainapp;

/**
 *
 * @author vincentdu
 */
public abstract class Seat {

    boolean taken = false;
    String description = "Seat for someone to sit in.";
    
    public String getDescription() {
        return description;
    }
    
    public abstract boolean isTaken();
    
    public abstract void setTaken(boolean taken);

    public abstract void setDescription(String description);
    
}
