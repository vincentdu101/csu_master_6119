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
        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);
        
        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Active Status");
        model.addColumn("Actions");
        
        clientController.addClientRows(model, (ActionEvent event) -> {
            System.exit(0);
        });
        
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(new Insets(40, 60, 40, 60)));
        panel.add(table);
        panel.add(new JButton("Add"));
        add(panel);

        pack();

        setTitle("RigidArea");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
}
