/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main_and_views;

import models.ClientModel;
import models.MovieModel;

/**
 *
 * @author vincentdu
 */
public class MainConfig {
    
    private ClientModel clientChosen;
    private MovieModel movieChosen;
    
    public MainConfig() {}
    
    public void setClientChosen(ClientModel clientChosen) {
        this.clientChosen = clientChosen;
    }
    
    public ClientModel getClientChosen() {
        return clientChosen;
    }
    
}
