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
public interface ModelInterface {
    
    public boolean isDeleted();
    
    public int getId();
    
    public void setDeleted(boolean deleted);
    
    public String printInfo();
    
}
