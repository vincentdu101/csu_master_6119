/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main_and_views;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import models.Client;

/**
 *
 * @author vincentdu
 */
public interface MovieViewObserver {
    
    public void updateTable(Object[][] data, JPanel panel, JScrollPane scroll);
    
    public void updateView(Client client);
    
}
