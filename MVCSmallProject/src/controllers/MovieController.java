/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import models.Movie;
import models.MovieModel;
import javax.swing.*;
import main_and_views.ControllerInterface;
import main_and_views.MovieView;
import models.Client;
import models.ClientModel;
import models.RentalInfo;

/**
 *
 * @author vincentdu
 */
public class MovieController implements ControllerInterface {
    
    private String fileName = "src/files/movies.txt";
    private List<Movie> movies;
    private int lastId;
    private ClientModel clientModel;
    private MovieModel movieModel;
    private MovieView movieView;
    private RentalInfoController rentalInfoController;
    
    public MovieController(MovieModel movieModel, 
                           ClientModel clientModel, 
                           RentalInfoController rentalInfoController) {
        this.movieModel = movieModel;
        this.clientModel = clientModel;
        this.rentalInfoController = rentalInfoController;
        loadResources();
    }
    
    private void saveToFile(List<Movie> movies, JPanel panel, JScrollPane scroll) {
        try {
            String contents = "";
            FileWriter fileWriter = new FileWriter(fileName, false);
            
            BufferedWriter bufferWriter = new BufferedWriter(fileWriter);

            for (Movie movie : movies) {
                contents += movie.printInfo() + "\n";
            }
            
            movieModel.notifyTableObservers(addMovieRows(getAllMovies()), panel, scroll);
            bufferWriter.write(contents);  
            bufferWriter.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    public void initializeView() {
        movieView = new MovieView(this, movieModel, rentalInfoController);
        movieView.setVisible(false);
    }
    
    public void backToClients(JPanel panel, JScrollPane scroll) {
        movieView.setVisible(false);
        clientModel.notifyViewObserver();
    }      
    
    @Override
    public void loadResources() {
        movies = new ArrayList<>();
        
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            Iterator textIterator = stream.iterator();
            
            while(textIterator.hasNext()) {
                String [] item = textIterator.next().toString().split(";");
                int id = Integer.parseInt(item[0]);
                boolean deleted = item[2].equals("1");
                movies.add(new Movie(id, item[1], deleted));
                lastId = id;
            };
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    public Movie findMovie(int movieId) {
        return movies.stream()
            .filter(e -> e.getId() == movieId)
            .findFirst().get();
    }
    
    public Optional<Movie> findMovieByTitle(String title) {
        return movies.stream()
            .filter(e -> e.getTitle().equals(title))
            .findFirst();
    }
    
    public List<Movie> getAllMovies() {
        List<Movie> allMovies = movies.stream()
            .filter(e -> e.isRented())
            .collect(Collectors.toList());
        
        allMovies.addAll(movies.stream()
            .filter(e -> !e.isRented())
            .collect(Collectors.toList()));
        
        return allMovies;
    }
    
    public Object[][] addMovieRows(List<Movie> movies) {
        Object[][] tableContents = new Object[movies.size()][6];
        Map<Integer, Client> clientMap = rentalInfoController.getClientRentalMap();
        
        for (int i=0 ; i < movies.size() ; i++) {
            Movie movie = movies.get(i);
            tableContents[i][0] = Integer.toString(movie.getId());
            tableContents[i][1] = movie.getTitle();
            tableContents[i][2] = movie.isRented() ? "Rented" : "Available";
            if (movie.isRented() && clientMap.get(movie.getId()) != null) {
                tableContents[i][3] = clientMap.get(movie.getId()).getName();
            } else {
                tableContents[i][3] = "-";
            }
        }
        return tableContents;        
    } 
    
    public Object[][] addClientMovieHistoricRows(int movieId) {
        Movie movie = findMovie(movieId);
        Object[][] tableContents = new Object[movies.size()][6];
        Map<Integer, Client> clientMap = rentalInfoController.getClientRentalMap();
        List<RentalInfo> rentalInfos = rentalInfoController.getRentalInfoForMovie(movie);
        
        for (int i = 0; i < rentalInfos.size(); i++) {
            RentalInfo rentalInfo = rentalInfos.get(i);
            tableContents[i][0] = Integer.toString(movieId);
            tableContents[i][1] = movie.getTitle();
            if (movie.isRented() && clientMap.get(movieId) != null) {
                tableContents[i][3] = clientMap.get(movieId).getName();
            } else {
                tableContents[i][3] = "-";
            }
            tableContents[i][4] = rentalInfo.outputDate(rentalInfo.getRentDate());
            if (rentalInfo.getReturnDate() != null) {
                tableContents[i][5] = rentalInfo.outputDate(rentalInfo.getReturnDate());
            } else {
                tableContents[i][5] = "";
            }
        }
        return tableContents;        
    }     
    
    public List<Movie> getRentedMovies() {
        return  movies.stream()
            .filter(e -> e.isRented())
            .collect(Collectors.toList());
    }
    
    public String createMovie(String name, JPanel panel, JScrollPane scroll) {
        try {
            if (findMovieByTitle(name).isPresent()) {
                return "Movie already exists";
            }
            
            FileWriter fileWriter = new FileWriter(fileName, true);
            BufferedWriter bufferWriter = new BufferedWriter(fileWriter);

            Movie movie = new Movie(++lastId, name, false);
            movies.add(movie);
            
            movieModel.notifyTableObservers(addMovieRows(getAllMovies()), panel, scroll);
            bufferWriter.write("\n" + movie.printInfo());
            bufferWriter.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
        return name + " successfully added";
    }
    
    public void rentMovie(int id, JPanel panel, JScrollPane scroll) {
        for (int i = 0; i < movies.size(); i++) {
            Movie movie = movies.get(i);
            if (movie.getId() == id) {
                movie.setRented(true);
                movies.set(i, movie);
                break;
            }
        }
        
        saveToFile(movies, panel, scroll);
    }
    
    public void returnMovie(int id, JPanel panel, JScrollPane scroll) {
        for (int i = 0; i < movies.size(); i++) {
            Movie movie = movies.get(i);
            if (movie.getId() == id) {
                movie.setRented(false);
                movies.set(i, movie);
                break;
            }
        }
        
        saveToFile(movies, panel, scroll);
    }    
    
}
