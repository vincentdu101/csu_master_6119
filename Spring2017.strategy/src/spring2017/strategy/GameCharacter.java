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
public class GameCharacter {
    
    public GuitarBehavior guitarBehavior;
    public SoloBehavior soloBehavior;
    
    public void playGuitar() {
        guitarBehavior.play();
    }
    
    public void playSolo() {
        soloBehavior.play();
    }
}
