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
public class GuitarHeroXXI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GameCharacter player1 = new GameCharacterNirvana();
        GameCharacter player2 = new GameCharacterBrianMay();
        GameCharacter player3 = new GameCharacterJimiHendrix();
        player1.playGuitar();
        player2.playGuitar();
        player3.playGuitar();
        player1.playSolo();
        player2.playSolo();
        player3.playSolo();
    }
    
}
