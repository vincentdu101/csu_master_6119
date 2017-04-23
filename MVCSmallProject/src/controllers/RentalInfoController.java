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
import models.Movie;
import models.RentalInfo;

/**
 *
 * @author vincentdu
 */
public class RentalInfoController {
    
    private String fileName = "src/files/rental_info.txt";
    private List<RentalInfo> rentalInfos;
    private int lastId;
    
    public RentalInfoController() {
        loadRentalInfo();
    }   
    
    private void saveToFile(List<RentalInfo> rentalInfoModels) {
        try {
            String contents = "";
            FileWriter fileWriter = new FileWriter(fileName, false);
            
            BufferedWriter bufferWriter = new BufferedWriter(fileWriter);

            for (RentalInfo rental : rentalInfoModels) {
                contents += rental.printInfo() + "\n";
            }
            
            bufferWriter.write(contents);  
            bufferWriter.close();
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
            Integer.parseInt(item[2]),
            Integer.parseInt(item[0]),
            Integer.parseInt(item[1])    
        );
    }
    
    private boolean hasClientReachedLimit(int clientId) {
        int numberRented = rentalInfos
            .stream()
            .filter(e -> e.getClientId() == clientId)
            .filter(e -> e.getReturnDate() == null)
            .collect(Collectors.toList()).size();
        return numberRented >= 3;
    } 
    
    public void loadRentalInfo() {
        rentalInfos = new ArrayList<>();
        
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            Iterator textIterator = stream.iterator();
            
            while(textIterator.hasNext()) {
                String [] item = textIterator.next().toString().split(";");
                int id = Integer.parseInt(item[0]);
                int clientId = Integer.parseInt(item[1]);
                int movieId = Integer.parseInt(item[2]);
                LocalDate rentDate = convertToLocalDate(item[3]);
                LocalDate returnDate = null;
                if (item.length == 5) {
                    returnDate = convertToLocalDate(item[4]);
                }        
                
                rentalInfos.add(new RentalInfo(id, clientId, movieId, rentDate, returnDate));
                lastId = id;
            };
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    public boolean isMovieRented(int movieId) {
        return rentalInfos.stream()
            .filter(e -> e.getMovieId() == movieId)
            .filter(e -> e.getReturnDate() == null)
            .collect(Collectors.toList()).size() > 0;
    }
    
    public void rentMovie(int movieId, int clientId) {
        if (hasClientReachedLimit(clientId)) {
            System.out.println("User has reached max rentals");
            return;
        }
        
        if (isMovieRented(movieId)) {
            System.out.println("Movie has been rented");
            return;
        }
        
        rentalInfos.add(new RentalInfo(
            ++lastId, clientId, movieId, LocalDate.now(), null
        ));
        
        saveToFile(rentalInfos);
    }
    
    public void returnMovie(int movieId, int clientId) {
        for (int i = 0; i < rentalInfos.size(); i++) {
            RentalInfo rental = rentalInfos.get(i);
            boolean movieCorrect = rental.getMovieId() == movieId;
            boolean clientCorrect = rental.getClientId() == clientId;
            boolean notReturned = rental.getReturnDate() == null;
            
            if (movieCorrect && clientCorrect && notReturned) {
                rental.setReturnDate(LocalDate.now());
                rentalInfos.set(i, rental);
                break;
            }
        }
        saveToFile(rentalInfos);
    }     
}
