/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iteratorpatternassignment5;

/**
 *
 * @author vdu
 */
public class IteratorPatternAssignment5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Iterator iterator1 = new StackIterator1();
        Iterator iterator2 = new StackIterator2();
        
        StackClient stackClient1 = new StackClient(iterator1);
        StackClient stackClient2 = new StackClient(iterator2);
        
        iterator1.add("This");
        iterator1.add("is");
        iterator1.add("a");
        iterator1.add("test");
        
        iterator2.add("Need");
        iterator2.add("more");
        iterator2.add("tests");
        iterator2.add("!");        
        
        stackClient1.printStack();
        stackClient2.printStack();
    }
    
}
