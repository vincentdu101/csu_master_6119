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
public class RegularSeat extends Seat {
    
    public RegularSeat() {
        this.taken = false;
        this.description = "Regular seat";
        this.seatType = SeatType.REGULAR_SEAT;
    }
    
    @Override
    public boolean isTaken() {
        return taken;
    }
    
    @Override 
    public void setTaken(boolean taken) {
        this.taken = taken;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }
    
}
