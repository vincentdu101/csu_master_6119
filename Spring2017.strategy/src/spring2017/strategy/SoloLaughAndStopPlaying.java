/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring2017.strategy;

/**
 *
 * @author vincentdu
 */
public class SoloLaughAndStopPlaying implements SoloBehavior {
    
    public void play() {
        System.out.println("Laugh and stop playing solo.");
    }
    
}
