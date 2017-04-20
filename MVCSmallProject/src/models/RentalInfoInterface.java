/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.time.LocalDate;

/**
 *
 * @author vincentdu
 */
public interface RentalInfoInterface {
    
    public int getId();
    
    public int getClientId();
    
    public int getMovieId();
    
    public LocalDate getRentDate();
    
    public LocalDate getReturnDate();
    
    public void setReturnDate(LocalDate returnDate);
    
    public String printInfo();
    
}
