/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg6109_project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author vincentdu
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter file name: ");
        
        
        int[] input = readFileInput("src/pkg6109_project/" + scanner.nextLine().toString());
        int[] output = new int[input.length];
        int[] z = findIncreasingSequence(input, output, 0);
        for (int x : z) {
            if (x > 0) {
                System.out.print(x + " ");
            }
        }
    }
    
    public static int[] readFileInput(String file) {
        int[] intOutput = new int[]{};
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }

            String patternString = "\\n|\\ ";
            Pattern pattern = Pattern.compile(patternString);
        
            String[] output = pattern.split(sb);

            intOutput = new int[output.length];
            for(int i = 0; i < output.length; i++) {
                intOutput[i] = Integer.parseInt(output[i]);
            }
            return intOutput;
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        return intOutput;
    }
    
    public static int[] findIncreasingSequence(int[] input, int[] output, int index) {        
        if (input.length == index) {
            return output;
        }
        
        if (index == 0) {
            output[0] = input[0];
            return findIncreasingSequence(input, output, ++index);
        }
        
        int temp = input[index];
        
        for (int x = 0; x < index; x++) {
            if (output[x] > temp && output.length > index) {
                output[x] = temp;
                return findIncreasingSequence(input, output, ++index);
            } else if (output[x] == 0) {
                output[x] = temp;
                return findIncreasingSequence(input, output, ++index);
            } else if (output[x] < temp && x + 1 == index) {
                output[x+1] = temp;
            }
        }     
        return findIncreasingSequence(input, output, ++index);
    }
    
}
