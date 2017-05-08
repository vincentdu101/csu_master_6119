/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iteratorpatternassignment5;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vdu
 */
public class StackImpl_1 implements Stack {
    
    List<String> listElements;
    
    public StackImpl_1() {
        clear();
    }
    
    @Override
    public String pop(int position) {
        return listElements.remove(position);
    }
    
    @Override
    public String push(int position, String element) {
        listElements.add(position, element);
        return element;
    }
    
    @Override
    public void clear() {
        listElements = new ArrayList();
    }
    
    @Override
    public int size() {
        return listElements.size();
    }
    
}
