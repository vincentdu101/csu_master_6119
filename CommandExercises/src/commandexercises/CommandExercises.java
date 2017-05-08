/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commandexercises;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author vincentdu
 */
public class CommandExercises {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        List<Student> students = generateStudents();
        
        students.forEach((student) -> printOutStudentInfo(student));
        
        students.sort((s1, s2) -> {
            GradeComparator gradeCompare = new GradeComparator();
            return gradeCompare.compare(s2, s1);
        }).forEach((student) -> printOutStudentInfo(student));
        
    }
    
    public static void printOutStudentInfo(Student student) {
        System.out.println("ID: " + student.getStudentID() + "\nFirst Name: "
                    + student.getFirstName() + "\nLast Name: " + student.getLastName() +
                    "\nGrade: " + student.getGrade());
    }
    
    public static List<Student> generateStudents() {
        Random random = new Random();
        List<Student> output = new LinkedList<>();
        
        for (int i = 0; i < 20; i++) {
            output.add(new Student(
                random.nextInt(1000),
                "FirstName" + i,
                "LastName" + i,
                random.nextDouble() * 100
            ));
        }
        
        return output;
    }
    
}
