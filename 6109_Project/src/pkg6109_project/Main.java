/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg6109_project;

/**
 *
 * @author vincentdu
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        int[] input = new int[]{1, 20, 3, 19, 4, 18, 5, 17, 6, 7};
        int[] output = new int[input.length];
        int[] z = findIncreasingSequence(input, output, 0);
        for (int x : z) {
            if (x > 0) {
                System.out.print(x + " ");
            }
        }
    }
    
    public static int[] findIncreasingSequence(int[] input, int[] output, int index) {        
        if (input.length == index) {
            return output;
        }
        
        if (output[0] == 0) {
            output[0] = input[0];
            return findIncreasingSequence(input, output, 1);
        }
        
        int temp = input[index];
        
        for (int x = 0; x < index; x++) {
            if (output[x] > temp) {
                output[x] = temp;
                return findIncreasingSequence(input, output, ++index);
            } else if (output[x] == 0) {
                output[x] = temp;
                return findIncreasingSequence(input, output, ++index);
            }
        }     
        return findIncreasingSequence(input, output, ++index);
    }
    
}
