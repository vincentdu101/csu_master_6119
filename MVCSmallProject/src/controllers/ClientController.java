/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.event.ActionListener;
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
import javax.swing.*;
import models.Client;
import models.ClientModel;

/**
 *
 * @author vincentdu
 */
public class ClientController {
    
    private String fileName = "src/files/clients.txt";
    private List<Client> clients;
    private int lastId;
    private ClientModel clientModel;
    
    
    public ClientController(ClientModel clientModel) {
        this.clientModel = clientModel;
        loadClients();
    }
    
    private void saveToFile(List<Client> clients, JPanel panel, JScrollPane scroll) {
        try {
            String contents = "";
            FileWriter fileWriter = new FileWriter(fileName, false);
            
            BufferedWriter bufferWriter = new BufferedWriter(fileWriter);

            for (Client client : clients) {
                contents += client.printInfo() + "\n";
            }
            
            clientModel.notifyTableObservers(addClientRows(getClients()), panel, scroll);
            bufferWriter.write(contents);  
            bufferWriter.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    public void loadClients() {
        clients = new ArrayList<>();
        
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            Iterator textIterator = stream.iterator();
            
            while(textIterator.hasNext()) {
                String [] item = textIterator.next().toString().split(";");
                int id = Integer.parseInt(item[0]);
                boolean deleted = item[2].equals("1");
                clients.add(new Client(id, item[1], deleted));
                lastId = id;
            };
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    public List<Client> getClients() {
        return clients;
    }
    
    public void searchClient(String text, JPanel panel, JScrollPane scroll) {
        List<Client> clients = findByName(text);
        clientModel.notifyTableObservers(addClientRows(clients), panel, scroll);
    }
    
    public Object[][] addClientRows(List<Client> clients) {
        Object[][] tableContents = new Object[clients.size()][3];
        for (int i=0 ; i < clients.size() ; i++) {
            Client client = clients.get(i);
            tableContents[i][0] = Integer.toString(client.getId());
            tableContents[i][1] = client.getName();
            tableContents[i][2] = client.isDeleted() ? "Not Active" : "Active";
        }
        return tableContents;        
    }
    
    public void createClient(String name, JPanel panel, JScrollPane scroll) {
        try {
            if (findByName(name).size() > 0) {
                return;
            }
            
            FileWriter fileWriter = new FileWriter(fileName, true);
            BufferedWriter bufferWriter = new BufferedWriter(fileWriter);

            Client client = new Client(++lastId, name, false);
            clients.add(client);
            
            clientModel.notifyTableObservers(addClientRows(getClients()), panel, scroll);
            bufferWriter.write(client.printInfo()); 
            bufferWriter.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    public List<Client> findByName(String name) {
        return clients.stream()
                .filter(e -> e.getName().equals(name))
                .collect(Collectors.toList());
    }
    
    public Optional<Client> findClientByFirstAndLastName(String firstName, String lastName) {
        String name = lastName == null ? firstName : firstName + " " + lastName;
        
        for (Client client : clients) {
            if (client.getName().matches(name)) {
                return Optional.of(client);
            }
        }
        return Optional.empty();
    }
    
    public void deleteClient(int id, JPanel panel, JScrollPane scroll) {        
        for (int i = 0; i < clients.size(); i++) {
            Client client = clients.get(i);
            if (client.getId() == id) {
                client.setDeleted(true);
                clients.set(i, client);
                break;
            }
        }
        
        saveToFile(clients, panel, scroll);
    }
    
}
