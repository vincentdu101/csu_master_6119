/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author vincentdu
 */
public class RentalInfo {
    
    private int id;
    private int clientId;
    private int movieId;
    private LocalDate rentDate = null;
    private LocalDate returnDate = null;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    
    public RentalInfo(int id, int clientId, int movieId, LocalDate rentDate, LocalDate returnDate) {
        this.id = id;
        this.clientId = clientId;
        this.movieId = movieId;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
    }
    
    public String outputDate(LocalDate date) {
        return date.format(formatter);
    }
    
    public int getId() {
        return id;
    }
    
    public int getClientId() {
        return clientId;
    }
    
    public int getMovieId() {
        return movieId;
    }
    
    public LocalDate getRentDate() {
        return rentDate;
    }
    
    public LocalDate getReturnDate() {
        return returnDate;
    }
    
    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
    
    public String printInfo() {
        String returnString = returnDate == null ? "" : outputDate(returnDate);
        String rentString = rentDate == null ? "" : outputDate(rentDate);
        return id + ";" + clientId + ";" + movieId + ";" + rentString + ";" + returnString;
    }
    
}
