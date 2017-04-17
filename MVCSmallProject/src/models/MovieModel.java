/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author vincentdu
 */
public class MovieModel implements ModelInterface {
    
    private boolean rented;
    private int id;
    private String title;
    
    public MovieModel(int id, String title, boolean rented) {
        this.id = id;
        this.title = title;
        this.rented = rented;
    }
    
    public boolean isRented() {
        return rented;        
    };
    
    @Override
    public int getId() {
        return id;
    };
    
    public String getTitle() {
        return title;
    };
    
    public void setTitle(String title) {
        this.title = title;
    };
    
    public void setRented(boolean rented) {
        this.rented = rented;
    };
    
    @Override 
    public String printInfo() {
        String rentedStatus = rented ? "1" : "0";
        return id + ";" + title + ";" + rentedStatus;
    }
    
    @Override
    public String printViewInfo() {
        String rentedResponse = rented ? "Rented" : "Available";
        return "ID: " + id + "\n" + "Movie Title: " + title +
                "\nStatus: " + rentedResponse; 
    }
    
}
