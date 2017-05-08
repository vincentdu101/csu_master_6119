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
public class StackClient {
    
    Iterator iterator;
    
    public StackClient(Iterator iterator) {
        this.iterator = iterator;
    }
    
    public void printStack() {
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
    
}
