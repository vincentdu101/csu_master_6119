/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trainapp;

/**
 *
 * @author vdu
 */
public class SpecialReservedSeat extends Seat {
    
    Seat seat;
    boolean taken;
    
    public SpecialReservedSeat() {
        this.taken = false;
        this.description = "Special Reserved seat";
    }
    
    @Override
    public boolean isTaken() {
        return taken;
    }
    
    @Override 
    public void setTaken(boolean taken) {
        this.taken = taken;
    }
    
}
