/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main_and_views;

import controllers.MovieController;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import models.MovieModel;

/**
 *
 * @author vincentdu
 */
public class MovieView extends JFrame implements MovieViewObserver{
    
    private MovieController movieController;
    private MovieModel movieModel;
    private String movieIdChosen;
    
    public MovieView(MovieController movieController, MovieModel model) {
        this.movieController = movieController;
        this.movieModel = model;
        this.movieModel.registerObserver((MovieViewObserver)this);
        initUI();
    }

    public void initUI() {
        createView(movieController.addMovieRows(movieController.getAllMovies()));
        setTitle("Movies");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    private Object[] generateClientTitleRow() {
        return new Object[] { "ID", "Title", "Active" };
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
                movieIdChosen = table.getValueAt(table.getSelectedRow(), 0).toString();
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
        JButton rentBtn = new JButton("Rent");

        addBtn.addActionListener(new ActionListener(){
           public void actionPerformed( ActionEvent event) { 
               movieController.createMovie(text.getText(), panel, scroll);
           }
        });

        searchBtn.addActionListener(new ActionListener(){
           public void actionPerformed( ActionEvent event) { 
//               movieController.searchClient(text.getText(), panel, scroll);
           }
        });
        
        selectBtn.addActionListener(new ActionListener(){
           public void actionPerformed( ActionEvent event) { 
//               selectClient(clientIdChosen);
           }
        });
        
        rentBtn.addActionListener(new ActionListener(){
           public void actionPerformed( ActionEvent event) { 
               int id = Integer.parseInt(movieIdChosen);
               movieController.rentMovie(id, panel, scroll); 
           }
        });
        
        panel.add(text);
        panel.add(addBtn);
        panel.add(searchBtn);
        panel.add(selectBtn);
        panel.add(rentBtn);  
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
    
}
