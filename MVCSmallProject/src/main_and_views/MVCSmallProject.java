/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main_and_views;

import controllers.ClientController;
import models.ClientModel;

/**
 *
 * @author vincentdu
 */
public class MVCSmallProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ClientController clientController = new ClientController();
        for(ClientModel client : clientController.getClients()) {
            System.out.println(client.printViewInfo());
        }
        clientController.createClient("Katie");
        ClientModel client = clientController.findClientByFirstAndLastName("Katie", null).get();
        System.out.println(client.printViewInfo());
    }
    
}
