
package com.challengercity.tanks.events;

/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class KeyUpEvent extends Event {

    public int key;
    public boolean repeated;
    
    public KeyUpEvent(int key, boolean repeated) {
        this.key = key;
        this.repeated = repeated;
    }
    
}
