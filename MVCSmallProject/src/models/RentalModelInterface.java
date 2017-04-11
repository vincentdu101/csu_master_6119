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
public interface RentalModelInterface {
    
    public int getId();
    
    public int getClientId();
    
    public int getMovieId();
    
    public Date getDateOut();
    
    public Date getDateIn();
    
    public void setDateIn(Date dateIn);
    
}
