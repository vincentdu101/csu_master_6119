/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main_and_views;

import models.Client;
import models.Movie;

/**
 *
 * @author vincentdu
 */
public class MainConfig {
    
    private Client clientChosen;
    private Movie movieChosen;
    
    public MainConfig() {}
    
    public void setClientChosen(Client clientChosen) {
        this.clientChosen = clientChosen;
    }
    
    public Client getClientChosen() {
        return clientChosen;
    }
    
}
