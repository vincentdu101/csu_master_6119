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
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author vdu
 */
public class ClientView extends JFrame {
    
    private ClientController clientController;
    private String clientIdChosen;
    
    public ClientView(ClientController clientController) {
        this.clientController = clientController;
        initUI();
    }

    private void initUI() {
        JPanel panel = new JPanel();
        DefaultTableModel dm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
               return false;
            }
        };
        dm.setDataVector(clientController.addClientRows(), generateClientTitleRow());

        JTable table = new JTable(dm);
        
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            
            public void valueChanged(ListSelectionEvent event) {
                clientIdChosen = table.getValueAt(table.getSelectedRow(), 0).toString();
            }
            
        });      
        
        JScrollPane scroll = new JScrollPane(table);
        getContentPane().add(scroll);
        setSize(new Dimension(400, 400));
        setVisible(true);
        setLayout(new GridLayout(1, 2));
        
        JButton addBtn = new JButton("Add");
        JButton selectBtn = new JButton("Select");
        JButton deleteBtn = new JButton("Delete");
        
        deleteBtn.addActionListener(new ActionListener(){
           public void actionPerformed( ActionEvent event) { 
               clientController.deleteClient(Integer.parseInt(clientIdChosen)); 
           }
        });
        
        panel.add(addBtn);
        panel.add(selectBtn);
        panel.add(deleteBtn);
        add(panel);

        pack();

        setTitle("RigidArea");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    private Object[] generateClientTitleRow() {
        return new Object[] { "ID", "Name", "Active" };
    }
    
    private void selectUser(String id) {
        System.out.println(id);
    }
}
