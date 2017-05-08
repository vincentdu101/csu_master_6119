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
public interface Stack {
    
    public String pop(int position);
    
    public String push(int position, String element);
    
    public void clear();
    
    public int size();
    
}
