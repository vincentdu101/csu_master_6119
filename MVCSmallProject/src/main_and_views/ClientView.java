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
import models.ClientModel;

/**
 *
 * @author vdu
 */
public class ClientView extends JFrame implements ClientViewObserver {
    
    private ClientController clientController;
    private ClientModel clientModel;
    private String clientIdChosen;
    
    public ClientView(ClientController clientController, ClientModel model) {
        this.clientController = clientController;
        this.clientModel = model;
        this.clientModel.registerObserver((ClientViewObserver)this);
        initUI();
    }

    public void initUI() {
        createView(clientController.addClientRows(clientController.getClients()));
        setTitle("Client List");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    private Object[] generateClientTitleRow() {
        return new Object[] { "ID", "Name", "Active" };
    }
    
    private void selectClient(String id) {
        System.out.println(id);
    }
    
    private void createView(Object[][] data) {
        DefaultTableModel dm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
               return false;
            }
        };
        dm.setDataVector(data, generateClientTitleRow());        
        
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
        
        addPanel(scroll); 
        pack();
    }
    
    private JPanel addButtons(JTextField text, JPanel panel, JScrollPane scroll) {
        JButton addBtn = new JButton("Add");
        JButton searchBtn = new JButton("Search");
        JButton selectBtn = new JButton("Select");
        JButton deleteBtn = new JButton("Delete");

        addBtn.addActionListener(new ActionListener(){
           public void actionPerformed( ActionEvent event) { 
               clientController.createClient(text.getText(), panel, scroll);
           }
        });

        searchBtn.addActionListener(new ActionListener(){
           public void actionPerformed( ActionEvent event) { 
               clientController.searchClient(text.getText(), panel, scroll);
           }
        });
        
        selectBtn.addActionListener(new ActionListener(){
           public void actionPerformed( ActionEvent event) { 
               clientController.selectClient(clientIdChosen, panel, scroll);
           }
        });
        
        deleteBtn.addActionListener(new ActionListener(){
           public void actionPerformed( ActionEvent event) { 
               int id = Integer.parseInt(clientIdChosen);
               clientController.deleteClient(id, panel, scroll); 
           }
        });
        
        panel.add(new JLabel("Search will work for only \"First Name Last Name\" format"));
        panel.add(text);
        panel.add(addBtn);
        panel.add(searchBtn);
        panel.add(selectBtn);
        panel.add(deleteBtn);  
        return panel;
    }
    
    private void addPanel(JScrollPane scroll) {
        JPanel panel = new JPanel();
        JTextField text = new JTextField(10); 
        addButtons(text, panel, scroll);
        add(panel);
    }
    
    private void removeView(JPanel panel, JScrollPane scroll) {
        setVisible(false);
        getContentPane().remove(scroll);
        remove(panel);
        pack();
    }
    
    @Override
    public void updateTable(Object[][] data, JPanel panel, JScrollPane scroll) {
        removeView(panel, scroll);
        createView(data);
    }
    
    @Override
    public void updateView() {
        getContentPane().removeAll();
        initUI();
    }
}
