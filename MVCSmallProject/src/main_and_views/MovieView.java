/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main_and_views;

import controllers.MovieController;
import controllers.RentalInfoController;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import models.Client;
import models.MovieModel;

/**
 *
 * @author vincentdu
 */
public class MovieView extends JFrame implements MovieViewObserver{
    
    private MovieController movieController;
    private RentalInfoController rentalInfoController;
    private MovieModel movieModel;
    private Client clientSelected;
    private String movieIdChosen;
    private JLabel status = new JLabel("None");
    
    public MovieView(MovieController movieController, MovieModel model, RentalInfoController rentalInfoController) {
        this.movieController = movieController;
        this.movieModel = model;
        this.movieModel.registerObserver((MovieViewObserver)this);
        this.rentalInfoController = rentalInfoController;
        initUI();
    }

    public void initUI() {
        createView(movieController.addMovieRows(movieController.getAllMovies()), 
                generateClientTitleRow());
        setTitle("Movies");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    private Object[] generateClientTitleRow() {
        return new Object[] { "ID", "Title", "Active", "Rented By" };
    }
    
    private Object[] generateClientDateTitleRow() {
        return new Object[] { "ID", "Title", "Active", "Rented By", "Date Rented", "Date Returned"};
    }    
    
    private void selectClient(String id) {
        System.out.println(id);
    }
    
    private void createView(Object[][] data, Object[] headers) {
        DefaultTableModel dm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
               return false;
            }
        };
        dm.setDataVector(data, headers);        
        
        JTable table = new JTable(dm);
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                movieIdChosen = table.getValueAt(table.getSelectedRow(), 0).toString();
            }
        });           
        
        JScrollPane scroll = new JScrollPane(table);
        getContentPane().add(scroll);
        setVisible(true);
        setLayout(new GridLayout(1, 2));
        setSize(new Dimension(150, 150));
        
        addPanel(scroll); 
        pack();
    }
    
    private JPanel addButtons(JTextField text, JPanel panel, JScrollPane scroll) {
        JButton addBtn = new JButton("Add");
        JButton returnBtn = new JButton("Return");
        JButton rentedBtn = new JButton("View Rented Movies");
        JButton viewPastRentals = new JButton("View Movie Past Rentals");
        JButton clientBtn = new JButton("Back to Clients");
        JButton rentBtn = new JButton("Rent");
        JButton allBtn = new JButton("All Movies");
        JLabel clientChosenLabel = null; 
        
        if (clientSelected != null) {
            clientChosenLabel = new JLabel("Current Client: " + clientSelected.getName());
        }
        
        addBtn.addActionListener(new ActionListener(){
            public void actionPerformed( ActionEvent event) { 
                String response = movieController.createMovie(text.getText(), panel, scroll);
                status.setText(response);
                status.setVisible(true);
            }
        });

        returnBtn.addActionListener(new ActionListener(){
           public void actionPerformed( ActionEvent event) {
               if (movieIdChosen != null) {
                   int id = Integer.parseInt(movieIdChosen);
                   movieController.returnMovie(id, panel, scroll);
                   rentalInfoController.returnMovie(id, clientSelected.getId());                   
               }
           }
        });
        
        rentedBtn.addActionListener(new ActionListener(){
           public void actionPerformed( ActionEvent event) { 
               movieModel.notifyTableObservers(
                       movieController.addMovieRows(
                               movieController.getRentedMovies()
                       ), panel, scroll
               );
           }
        });

        allBtn.addActionListener(new ActionListener(){
           public void actionPerformed( ActionEvent event) { 
               movieModel.notifyTableObservers(
                       movieController.addMovieRows(
                               movieController.getAllMovies()
                       ), panel, scroll
               );
           }
        });

        
        viewPastRentals.addActionListener(new ActionListener(){
           public void actionPerformed( ActionEvent event) { 
               int id = Integer.parseInt(movieIdChosen);
               removeView(panel, scroll);
               createView(movieController.addClientMovieHistoricRows(id), 
                       generateClientDateTitleRow());
           }
        });        
        
        clientBtn.addActionListener(new ActionListener(){
           public void actionPerformed( ActionEvent event) { 
               movieController.backToClients(panel, scroll);
           }
        });
        
        rentBtn.addActionListener(new ActionListener(){
           public void actionPerformed( ActionEvent event) { 
               int id = Integer.parseInt(movieIdChosen);
               movieController.rentMovie(id, panel, scroll); 
               rentalInfoController.rentMovie(id, clientSelected.getId());
           }
        });
        
        panel.setLayout (new GridLayout(6, 1)); 
        if (clientChosenLabel != null) {
            panel.add(clientChosenLabel);
        }        
        panel.add(text);
        panel.add(rentedBtn);
        panel.add(viewPastRentals);
        panel.add(allBtn);
        panel.add(addBtn);
        panel.add(rentBtn);
        panel.add(returnBtn);
        panel.add(clientBtn); 
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
        getContentPane().remove(scroll);
        remove(panel);
        pack();
    }
    
    @Override
    public void updateTable(Object[][] data, JPanel panel, JScrollPane scroll) {
        removeView(panel, scroll);
        createView(data, generateClientTitleRow());
    }    
    
    @Override
    public void updateView(Client client) {
        getContentPane().removeAll();
        this.clientSelected = client;
        initUI();
    }
    
}
