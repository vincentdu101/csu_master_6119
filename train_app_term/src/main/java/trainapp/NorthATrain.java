/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trainapp;

/**
 *
 * @author vdu
 */
public class NorthATrain extends Train {

    public NorthATrain() {
        createTrain();
    }

    public void createTrain() {
        this.name = "North A Station";
        this.description = "North A Station";
        this.create();
    }

}
