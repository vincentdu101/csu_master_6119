/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package decoratorpatternassignment3;

import java.util.Scanner;

/**
 *
 * @author vincentdu
 */
public class DecoratorPatternAssignment3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner scanner = new Scanner(System.in);
        MyHome home = new BasicHome();
        
        System.out.println("The basic home includes 1 living room.");
        
        System.out.println("How many bedrooms would you like?");
        int bedrooms = scanner.nextInt();
        
        System.out.println("How many bathrooms would you like?");
        int bathrooms = scanner.nextInt();
        
        System.out.println("How many additional living rooms would you like?");
        int livingRooms = scanner.nextInt();
        
        System.out.println("How many study rooms would you like?");
        int studyRooms = scanner.nextInt();
        
        System.out.println("How many kitchens would you like?");
        int kitchens = scanner.nextInt();
        
        System.out.println("How many walk in closets would you like?");
        int walkInClosets = scanner.nextInt();
        
        home = new Bedroom(home, bedrooms);
        home = new Bathroom(home, bathrooms);
        home = new LivingRoom(home, livingRooms);
        home = new StudyRoom(home, studyRooms);
        home = new Kitchen(home, kitchens);
        home = new WalkInCloset(home, walkInClosets);
        
        System.out.println("House Designed:");
        System.out.println(home.getDescription());
        System.out.println("Square Feet: " + home.squareFeet());
        System.out.println("Total Cost: $" + home.cost());
    }
    
}
