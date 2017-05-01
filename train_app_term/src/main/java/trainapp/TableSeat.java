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
public class TableSeat extends Seat {

    public TableSeat() {
        this.taken = false;
        this.description = "Table seat";
        this.seatType = SeatType.TABLE_SEAT;
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
