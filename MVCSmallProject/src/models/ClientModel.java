/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import main_and_views.ClientViewObserver;
import main_and_views.MovieViewObserver;

/**
 *
 * @author vincentdu
 */
public class ClientModel {
    
    List<ClientViewObserver> observers = new ArrayList<>();
    
    public ClientModel() {
        
    }
    
    public void registerObserver(ClientViewObserver observer) {
        observers.add(observer);
    }
    
    public void notifyTableObservers(Object[][] data, JPanel panel, JScrollPane scroll) {
        for (ClientViewObserver observer : observers) {
            observer.updateTable(data, panel, scroll);
        }
    }
    
    public void notifyViewObserver() {
        for (ClientViewObserver observer : observers) {
            observer.updateView();
        }        
    }    
    
}
