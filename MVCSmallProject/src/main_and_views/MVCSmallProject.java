/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main_and_views;

import controllers.ClientController;
import controllers.MovieController;
import controllers.RentalInfoController;
import javax.swing.SwingUtilities;
import models.Client;
import models.ClientModel;
import models.Movie;
import models.MovieModel;

/**
 *
 * @author vincentdu
 */
public class MVCSmallProject {
    
    private static ClientController clientController;
    private static ClientModel clientModel;
    
    private static MovieController movieController;
    private static MovieModel movieModel;
    
    private static RentalInfoController rentalInfoController;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        setupSystemComponents();
    }
    
    public static void setupSystemComponents() {
        SwingUtilities.invokeLater(() -> {
            clientModel = new ClientModel();            
            movieModel = new MovieModel();
            
            clientController = new ClientController(clientModel, movieModel);
            rentalInfoController = new RentalInfoController(clientController);
            movieController = new MovieController(movieModel, clientModel, rentalInfoController);
            
            clientController.initializeView();
            movieController.initializeView();
        });        
    }
    
}
