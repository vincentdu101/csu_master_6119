/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;

/**
 *
 * @author vincentdu
 */
public class RentalInfoModel implements RentalModelInterface {
    
    private int id;
    private int clientId;
    private int movieId;
    private Date dateOut;
    private Date dateIn;
    
    public RentalInfoModel(int id, int clientId, int movieId, Date dateOut) {
        this.id = id;
        this.clientId = clientId;
        this.movieId = movieId;
        this.dateOut = dateOut;
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
    
    public Date getDateOut() {
        return dateOut;
    }
    
    public Date getDateIn() {
        return dateIn;
    }
    
    public void setDateIn(Date dateIn) {
        this.dateIn = dateIn;
    }
    
}
