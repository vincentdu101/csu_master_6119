/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factorypatternassignment4;

/**
 *
 * @author vincentdu
 */
public class Battery {
    
    String cells; 
    String casing; 
    String controller; 
    String charger; 
    
    public void collectparts(){
        System.out.println("Collecting Parts: Cells -> " + cells + ", Casing -> " + casing + ", Controller -> " + controller + ", Charger -> " + charger);
    }
    
    public void testparts(){
        System.out.println("Testing Parts: Cells -> " + cells + ", Casing -> " + casing + ", Controller -> " + controller + ", Charger -> " + charger);   
    }
    
    public void assembleparts() {
        System.out.println("Assembling Parts: Cells -> " + cells + ", Casing -> " + casing + ", Controller -> " + controller + ", Charger -> " + charger);   
    }
    
    public void ship() {
        System.out.println("Shipping Parts: Cells -> " + cells + ", Casing -> " + casing + ", Controller -> " + controller + ", Charger -> " + charger);   
    } 
    
}
