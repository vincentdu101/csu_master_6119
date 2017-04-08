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
public class ClientModel implements ModelInterface {
    
    private boolean deleted;
    private int id;
    private String name;
    
    public ClientModel(int id, String name, boolean deleted) {
        this.id = id;
        this.name = name;
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
    
    public String getName() {
        return name;
    };
    
    public void setName(String name) {
        this.name = name;
    };
    
    @Override
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    };
    
    @Override 
    public String printInfo() {
        String deletedValue = deleted ? "1" : "0";
        return id + ";" + name + ";" + deletedValue;
    }
    
}
