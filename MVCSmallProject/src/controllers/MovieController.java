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
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import models.Movie;
import models.MovieModel;
import javax.swing.*;

/**
 *
 * @author vincentdu
 */
public class MovieController {
    
    private String fileName = "src/files/movies.txt";
    private List<Movie> movies;
    private int lastId;
    private MovieModel movieModel;
    
    public MovieController(MovieModel movieModel) {
        this.movieModel = movieModel;
        loadMovies();
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
    
    public void loadMovies() {
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
    
    public Movie findMovieByTitle(String title) {
        return movies.stream()
            .filter(e -> e.getTitle().equals(title))
            .findFirst().get();
    }
    
    public List<Movie> getAllMovies() {
        List<Movie> allMovies = movies.stream()
            .filter(e -> e.isRented())
            .collect(Collectors.toList());
        
        allMovies.addAll(movies.stream()
            .filter(e -> !e.isRented())
            .collect(Collectors.toList()));
        
        return movies;
    }
    
    public Object[][] addMovieRows(List<Movie> movies) {
        Object[][] tableContents = new Object[movies.size()][3];
        for (int i=0 ; i < movies.size() ; i++) {
            Movie movie = movies.get(i);
            tableContents[i][0] = Integer.toString(movie.getId());
            tableContents[i][1] = movie.getTitle();
            tableContents[i][2] = movie.isRented() ? "Rented" : "Available";
        }
        return tableContents;        
    }    
    
    public List<Movie> getRentedMovies() {
        return  movies.stream()
            .filter(e -> e.isRented())
            .collect(Collectors.toList());
    }
    
    public void createMovie(String name, JPanel panel, JScrollPane scroll) {
        try {
            if (findMovieByTitle(name) != null) {
                System.out.println("Movie already exists");
                return;
            }
            
            FileWriter fileWriter = new FileWriter(fileName, true);
            BufferedWriter bufferWriter = new BufferedWriter(fileWriter);

            Movie movie = new Movie(++lastId, name, false);
            movies.add(movie);
            
            movieModel.notifyTableObservers(addMovieRows(getAllMovies()), panel, scroll);
            bufferWriter.write(movie.printInfo());   
        } catch(IOException e) {
            e.printStackTrace();
        }
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
