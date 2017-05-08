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
public class StackIterator2 implements Iterator {
    
    StackImpl_2 stack;
    int position;
    
    public StackIterator2() {
        stack = new StackImpl_2();
    }
    
    @Override
    public boolean hasNext() {
        return position < stack.size();
    }
    
    @Override
    public String next() {
        String item = stack.pop(position);
        stack.push(position, item);
        position++;
        return item;
    }
    
    @Override
    public void remove() {
        stack.pop(position);
        position--;
    }
    
    @Override
    public void add(String item) {
        stack.push(position, item);
        position++;
    }    
}
