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
import models.ClientModel;

/**
 *
 * @author vincentdu
 */
public class ClientController {
    
    private String fileName = "src/files/clients.txt";
    private List<ClientModel> clientModels;
    private int lastId;
    
    
    public ClientController() {
        loadClients();
    }
    
    private void saveToFile(List<ClientModel> clients) {
        try {
            String contents = "";
            FileWriter fileWriter = new FileWriter(fileName, true);
            BufferedWriter bufferWriter = new BufferedWriter(fileWriter);

            for (ClientModel client : clients) {
                contents += client.printInfo() + "\n";
            }
            
            bufferWriter.write(contents);   
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    public void loadClients() {
        clientModels = new ArrayList<>();
        
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            Iterator textIterator = stream.iterator();
            
            while(textIterator.hasNext()) {
                String [] item = textIterator.next().toString().split(";");
                int id = Integer.parseInt(item[0]);
                boolean deleted = item[2].equals("1");
                clientModels.add(new ClientModel(id, item[1], deleted));
                lastId = id;
            };
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    public List<ClientModel> getClients() {
        return clientModels;
    }
    
    public void createClient(String name) {
        try {
            FileWriter fileWriter = new FileWriter(fileName, true);
            BufferedWriter bufferWriter = new BufferedWriter(fileWriter);

            ClientModel client = new ClientModel(++lastId, name, false);
            clientModels.add(client);
            
            bufferWriter.write(client.printInfo());   
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    public Optional<ClientModel> findClientByFirstAndLastName(String firstName, String lastName) {
        String name = lastName == null ? firstName : firstName + " " + lastName;
        
        for (ClientModel client : clientModels) {
            if (client.getName().matches(name)) {
                return Optional.of(client);
            }
        }
        return Optional.empty();
    }
    
    public void deleteClient(int id) {
        for (int i = 0; i < clientModels.size(); i++) {
            ClientModel client = clientModels.get(i);
            if (client.getId() == id) {
                client.setDeleted(true);
                clientModels.set(i, client);
                break;
            }
        }
        
        saveToFile(clientModels);
    }
    
}
