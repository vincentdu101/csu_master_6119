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
public class LastNameComparator implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        Student student1 = (Student)o1;
        Student student2 = (Student)o2;
        
        if (student1.getLastName().equals(student2.getLastName())) {
            GradeComparator gradeCompare = new GradeComparator();
            return gradeCompare.compare(student1, student2);
        } else if (student1.getLastName().compareToIgnoreCase(student2.getLastName()) > 0) {
            return -1;
        } else {
            return 1;
        }
    }
    
}
