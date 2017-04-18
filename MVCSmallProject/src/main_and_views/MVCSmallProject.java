/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main_and_views;

import controllers.ClientController;
import controllers.MovieController;
import javax.swing.SwingUtilities;
import models.ClientModel;
import models.MovieModel;

/**
 *
 * @author vincentdu
 */
public class MVCSmallProject {
    
    private static ClientController clientController;
    private static MainConfig mainConfig;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        mainConfig = new MainConfig();
        
        testOutClient();
        testOutMovie();
        
        testOutClientMenu();
    }
    
    public static void testOutClient() {
        clientController = new ClientController(mainConfig);
        for(ClientModel client : clientController.getClients()) {
            System.out.println(client.printViewInfo());
        }
        clientController.createClient("Katie");
        ClientModel client = clientController.findClientByFirstAndLastName("Katie", null).get();
        System.out.println(client.printViewInfo());
    }
    
    public static void testOutMovie() {
        MovieController movieController = new MovieController();
        System.out.println("All Movies");
        for (MovieModel movie : movieController.getAllMovies()) {
            System.out.println(movie.printViewInfo());
        }
        movieController.createMovie("Titanic");     
        movieController.rentMovie(1);
        MovieModel firstMovie = movieController.findMovie(1);
        System.out.println(firstMovie.printViewInfo());
        System.out.println("Rented Movies");
        for (MovieModel movie : movieController.getRentedMovies()) {
            System.out.println(movie.printViewInfo());
        }           
        movieController.returnMovie(1);
        firstMovie = movieController.findMovie(1);
        System.out.println(firstMovie.printViewInfo());        
    }
    
    public static void testOutClientMenu() {
        SwingUtilities.invokeLater(() -> {
            ClientMainMenu ex = new ClientMainMenu(clientController);
            ex.setVisible(true);
        });        
    }
    
}
