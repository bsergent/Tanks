
package com.challengercity.tanks;

/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class ListenerKeyboard extends Listener {

    private final boolean continuous;

    public ListenerKeyboard(boolean continuous) {
        this.continuous = continuous;
    }
    
    public void keyUp(int key) {
        
    }
    
    public void keyDown(int key) {
        
    }

    public boolean isContinuous() {
        return continuous;
    }
    
}
