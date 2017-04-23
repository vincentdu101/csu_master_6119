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
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.swing.*;
import main_and_views.ClientView;
import main_and_views.ControllerInterface;
import models.Client;
import models.ClientModel;
import models.MovieModel;

/**
 *
 * @author vincentdu
 */
public class ClientController implements ControllerInterface {
    
    private String fileName = "src/files/clients.txt";
    private List<Client> clients;
    private int lastId;
    private ClientModel clientModel;
    private ClientView clientView;
    private MovieModel movieModel;
    
    public ClientController(ClientModel clientModel, MovieModel movieModel) {
        this.clientModel = clientModel;
        this.movieModel = movieModel;
        loadResources();
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
    
    public void initializeView() {
        clientView = new ClientView(this, clientModel);
        clientView.setVisible(true);
    }
    
    public void selectClient(String id, JPanel panel, JScrollPane scroll) {
        Client client = findById(Integer.parseInt(id)).get(0);
        clientView.setVisible(false);
        movieModel.notifyViewObserver(client);
    }   
    
    @Override
    public void loadResources() {
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
    
    public String createClient(String name, JPanel panel, JScrollPane scroll) {
        try {
            if (findExact(name).isPresent()) {
                return "Client already exists.";
            }
            
            FileWriter fileWriter = new FileWriter(fileName, true);
            BufferedWriter bufferWriter = new BufferedWriter(fileWriter);

            Client client = new Client(++lastId, name, false);
            clients.add(client);
            
            clientModel.notifyTableObservers(addClientRows(getClients()), panel, scroll);
            bufferWriter.write("\n" + client.printInfo()); 
            bufferWriter.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
        return name + " successfully added";
    }
    
    public Optional<Client> findExact(String name) {
        return clients.stream()
                .filter(e -> e.getName().equals(name))
                .findFirst();        
    }
    
    public List<Client> findByName(String name) {
        String pattern = "\\w+\\ \\w+";
        Pattern namePattern = Pattern.compile(pattern);
        
        Matcher m = namePattern.matcher(name);
        
        if (!m.find()) {
            return Collections.emptyList();
        }
        
        return clients.stream()
                .filter(e -> e.getName().equals(name))
                .collect(Collectors.toList());
    }
    
    public List<Client> findById(int id) {
        return clients.stream()
                .filter(e -> e.getId() == id)
                .collect(Collectors.toList());
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
