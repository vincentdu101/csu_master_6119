/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvcsmallproject.models;

/**
 *
 * @author vincentdu
 */
public class MovieModel implements ModelInterface {
    
    private boolean deleted;
    private int id;
    private String title;
    
    public MovieModel(int id, String title, boolean deleted) {
        this.id = id;
        this.title = title;
        this.deleted = deleted;
    }
    
    @Override
    public boolean isDeleted() {
        return deleted;        
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
    
    @Override
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    };
    
    @Override 
    public String printInfo() {
        String deletedValue = deleted ? "1" : "0";
        return id + ";" + title + ";" + deletedValue;
    }
    
}
