/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main_and_views;

import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.*;
import javax.swing.table.*;
import javax.swing.border.EmptyBorder;
import controllers.ClientController;
import java.awt.Component;
import java.awt.event.*;

/**
 *
 * @author vdu
 */
public class ClientMainMenu extends JFrame {
    
    private ClientController clientController;
    
    public ClientMainMenu(ClientController clientController) {
        this.clientController = clientController;
        initUI();
    }

    private void initUI() {
        JPanel panel = new JPanel();
        DefaultTableModel dm = new DefaultTableModel();
        dm.setDataVector(clientController.addClientRows(), generateClientTitleRow());

        JTable table = new JTable(dm);
        
        table.getColumn("Use Client").setCellRenderer(new ClientButtonRenderer());
        table.getColumn("Use Client").setCellEditor(
            new ClientButtonEditor(new JCheckBox()));
        
        table.getColumn("Delete").setCellRenderer(new ClientButtonRenderer());
        table.getColumn("Delete").setCellEditor(
            new ClientButtonEditor(new JCheckBox()));        
        
        JScrollPane scroll = new JScrollPane(table);
        getContentPane().add(scroll);
        setSize(400, 400);
        setVisible(true);
        panel.add(new JButton("Add"));
        add(panel);

        pack();

        setTitle("RigidArea");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    private Object[] generateClientTitleRow() {
        return new Object[] { "ID", "Name", "Active", "Use Client", "Delete" };
    }
    
}
