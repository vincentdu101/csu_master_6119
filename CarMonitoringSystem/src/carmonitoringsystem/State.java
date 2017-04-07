/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carmonitoringsystem;

/**
 *
 * @author vdu
 */
public enum State {
    
    NO_NEED_REPAIR("NO_NEED_REPAIR", "No need for repair"),
    IMMENIENT_REPAIR("IMMENIENT_REPAIR", "Immenient Repair needed"),
    MUST_REPAIR("MUST_REPAIR", "Must repair now");
    
    String state;
    String reason;
    
    State(String state, String reason) {
        this.state = state;
        this.reason = reason;
    }
    
    public String getState() {
        return state;
    }
    
    public String getReason() {
        return reason;
    }
    
}
