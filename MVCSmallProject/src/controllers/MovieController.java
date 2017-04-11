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
import models.MovieModel;

/**
 *
 * @author vincentdu
 */
public class MovieController {
    
    private String fileName = "src/files/movies.txt";
    private List<MovieModel> movieModels;
    private int lastId;
    
    
    public MovieController() {
        loadMovies();
    }
    
    private void saveToFile(List<MovieModel> movies) {
        try {
            String contents = "";
            FileWriter fileWriter = new FileWriter(fileName, true);
            BufferedWriter bufferWriter = new BufferedWriter(fileWriter);

            for (MovieModel movie : movies) {
                contents += movie.printInfo() + "\n";
            }
            
            bufferWriter.write(contents);   
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    public void loadMovies() {
        movieModels = new ArrayList<>();
        
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            Iterator textIterator = stream.iterator();
            
            while(textIterator.hasNext()) {
                String [] item = textIterator.next().toString().split(";");
                int id = Integer.parseInt(item[0]);
                boolean deleted = item[2].equals("1");
                movieModels.add(new MovieModel(id, item[1], deleted));
                lastId = id;
            };
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    public List<MovieModel> getMovies() {
        return movieModels;
    }
    
    public void createMovie(String name) {
        try {
            FileWriter fileWriter = new FileWriter(fileName, true);
            BufferedWriter bufferWriter = new BufferedWriter(fileWriter);

            MovieModel movie = new MovieModel(++lastId, name, false);
            movieModels.add(movie);
            
            bufferWriter.write(movie.printInfo());   
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    public void rentMovie(int id) {
        for (int i = 0; i < movieModels.size(); i++) {
            MovieModel movie = movieModels.get(i);
            if (movie.getId() == id) {
                movie.setRented(true);
                movieModels.set(i, movie);
                break;
            }
        }
        
        saveToFile(movieModels);
    }
    
    public void returnMovie(int id) {
        for (int i = 0; i < movieModels.size(); i++) {
            MovieModel movie = movieModels.get(i);
            if (movie.getId() == id) {
                movie.setRented(false);
                movieModels.set(i, movie);
                break;
            }
        }
        
        saveToFile(movieModels);
    }    
    
}
