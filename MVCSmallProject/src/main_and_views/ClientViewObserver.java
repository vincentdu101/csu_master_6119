/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main_and_views;

import javax.swing.*;

/**
 *
 * @author vincentdu
 */
public interface ClientViewObserver {
    
    public void updateTable(Object[][] data, JPanel panel, JScrollPane scroll);
    
    public void updateView();
    
}
