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
import java.awt.FlowLayout;
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
    private String clientIdChosen = null;
    private JLabel status = new JLabel("None");
    
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
        setSize(new Dimension(150, 150));
        setVisible(true);
        setLayout(new GridLayout(1, 2));
        
        addPanel(scroll); 
        pack();
    }
    
    private JPanel addButtons(JTextField text, JPanel panel, JScrollPane scroll) {
        JButton addBtn = new JButton("Add");
        JButton allBtn = new JButton("All Clients");
        JButton searchBtn = new JButton("Search");
        JButton selectBtn = new JButton("Select");
        JButton deleteBtn = new JButton("Delete");

        addBtn.addActionListener(new ActionListener(){
           public void actionPerformed( ActionEvent event) { 
               if (!text.getText().trim().equals("")) {
                    String response = clientController.createClient(text.getText(), panel, scroll);
                    status.setText(response);
                    status.setVisible(true);
               } else {
                    status.setText("Name must be First \nName and Last Name.");
                    status.setVisible(true);
               }
           }
        });

        allBtn.addActionListener(new ActionListener(){
           public void actionPerformed( ActionEvent event) { 
               removeView(panel, scroll);
               createView(clientController.addClientRows(clientController.getClients()));
           }
        });
        
        searchBtn.addActionListener(new ActionListener(){
           public void actionPerformed( ActionEvent event) {
               if (text.getText() != null) {
                    clientController.searchClient(text.getText(), panel, scroll);
               }
           }
        });
        
        selectBtn.addActionListener(new ActionListener(){
           public void actionPerformed( ActionEvent event) { 
               if (clientIdChosen != null) {
                    clientController.selectClient(clientIdChosen, panel, scroll);
               }
           }
        });
        
        deleteBtn.addActionListener(new ActionListener(){
           public void actionPerformed( ActionEvent event) { 
               if (clientIdChosen != null) {
                    int id = Integer.parseInt(clientIdChosen);
                    clientController.deleteClient(id, panel, scroll); 
               }
           }
        });
        
        panel.setLayout (new GridLayout(6, 1)); 
        panel.setSize(new Dimension(5, 200));
        panel.add(new JLabel("Search will work for only \"First Name Last Name\" format"));
        panel.add(text);
        panel.add(addBtn);
        panel.add(allBtn);
        panel.add(searchBtn);
        panel.add(selectBtn);
        panel.add(deleteBtn);  
        return panel;
    }
    
    private void addPanel(JScrollPane scroll) {
        JPanel panel = new JPanel();
        JTextField text = new JTextField(10); 
        status.setVisible(false);
        panel.add(status);
        addButtons(text, panel, scroll);
        add(panel);
    }
    
    private void removeView(JPanel panel, JScrollPane scroll) {
        setVisible(false);
        clientIdChosen = null;
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
