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
public interface Iterator {
    
    public boolean hasNext();
    
    public String next();
    
    public void remove();
    
    public void add(String item);
    
}
