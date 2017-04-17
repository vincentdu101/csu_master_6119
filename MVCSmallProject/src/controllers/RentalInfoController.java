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
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import models.MovieModel;
import models.RentalInfoModel;

/**
 *
 * @author vincentdu
 */
public class RentalInfoController {
    
    private String fileName = "src/files/rental_info.txt";
    private List<RentalInfoModel> rentalInfoModels;
    private int lastId;
    private MovieController movieController;
    
    public RentalInfoController(MovieController movieController) {
        this.movieController = movieController;
    }
    
    private void saveToFile(List<RentalInfoModel> rentalInfoModels) {
        try {
            String contents = "";
            FileWriter fileWriter = new FileWriter(fileName, false);
            BufferedWriter bufferWriter = new BufferedWriter(fileWriter);

            for (RentalInfoModel rental : rentalInfoModels) {
                contents += rental.printInfo() + "\n";
            }
            
            bufferWriter.write(contents);   
        } catch(IOException e) {
            e.printStackTrace();
        }
    }    
    
    private LocalDate convertToLocalDate(String date) {
        if (date == null) {
            return null;
        }
        
        String[] item = date.split("/");
        return LocalDate.of(
            Integer.parseInt(item[0]),
            Integer.parseInt(item[1]),
            Integer.parseInt(item[2])    
        );
    }
    
    private boolean hasClientReachedLimit(int clientId) {
        int numberRented = rentalInfoModels
            .stream()
            .filter(e -> e.getClientId() == clientId)
            .filter(e -> e.getReturnDate() == null)
            .collect(Collectors.toList()).size();
        return numberRented >= 3;
    } 
    
    public void loadRentalInfo() {
        rentalInfoModels = new ArrayList<>();
        
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            Iterator textIterator = stream.iterator();
            
            while(textIterator.hasNext()) {
                String [] item = textIterator.next().toString().split(";");
                int id = Integer.parseInt(item[0]);
                int clientId = Integer.parseInt(item[1]);
                int movieId = Integer.parseInt(item[2]);
                LocalDate rentDate = convertToLocalDate(item[3]);
                LocalDate returnDate = convertToLocalDate(item[4]);
                
                rentalInfoModels.add(new RentalInfoModel(id, clientId, movieId, rentDate, returnDate));
                lastId = id;
            };
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    public void rentMovie(int movieId, int clientId) {
        if (hasClientReachedLimit(clientId)) {
            System.out.println("User has reached max rentals");
            return;
        }
        
        if (movieController.findMovie(movieId).isRented()) {
            System.out.println("Movie has been rented");
            return;
        }
        
        rentalInfoModels.add(new RentalInfoModel(
            ++lastId, clientId, movieId, LocalDate.now(), null
        ));
        
        movieController.rentMovie(movieId);
        saveToFile(rentalInfoModels);
    }
    
    public void returnMovie(int movieId) {
        for (int i = 0; i < rentalInfoModels.size(); i++) {
            RentalInfoModel rental = rentalInfoModels.get(i);
            if (rental.getMovieId() == movieId) {
                rental.setReturnDate(LocalDate.now());
                rentalInfoModels.set(i, rental);
                break;
            }
        }
        movieController.returnMovie(movieId);
        saveToFile(rentalInfoModels);
    }     
}
