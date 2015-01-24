
package com.challengercity.tanks.events;

/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class KeyDownEvent extends Event {

    public int key;
    public boolean repeated;
    
    public KeyDownEvent(int key, boolean repeated) {
        this.key = key;
        this.repeated = repeated;
    }
    
}
