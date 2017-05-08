/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iteratorpatternassignment5;

import java.util.Vector;

/**
 *
 * @author vdu
 */
public class StackImpl_2 implements Stack{
    
    Vector<String> vectorElements;
    
    public StackImpl_2() {
        clear();
    }
    
    @Override
    public String pop(int position) {
        return vectorElements.remove(position);
    }
    
    @Override
    public String push(int position, String element) {
        vectorElements.add(position, element);
        return element;
    }
    
    @Override
    public void clear() {
        vectorElements = new Vector();
    }
    
    @Override
    public int size() {
        return vectorElements.size();
    }
}
