/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commandexercises;

import java.util.Comparator;

/**
 *
 * @author vincentdu
 */
public class GradeComparator implements Comparator {
    
    @Override
    public int compare(Object o1, Object o2) {
        Student student1 = (Student)o1;
        Student student2 = (Student)o2;
        
        if (student1.getGrade() > student2.getGrade()) {
            return -1;
        } else if (student1.getGrade() == student2.getGrade()) {
            return 0;
        } else {
            return 1;
        }
    }
    
}
