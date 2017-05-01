/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trainapp;

import java.util.Date;

/**
 *
 * @author vincentdu
 */
public abstract class Seat {

    Date createdAt;
    Date modifiedAt;
    boolean taken = false;
    String description = "Seat for someone to sit i n.";
    SeatType seatType;
    
    public String getDescription() {
        return description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public SeatType getSeatType() { return seatType; }
    
    public abstract boolean isTaken();
    
    public abstract void setTaken(boolean taken);

    public abstract void setDescription(String description);
    
}
