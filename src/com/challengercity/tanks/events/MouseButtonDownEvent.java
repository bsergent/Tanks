
package com.challengercity.tanks.events;

/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class MouseButtonDownEvent extends Event {

    public int button;
    public int x;
    public int y;
    
    public MouseButtonDownEvent(int button, int x, int y) {
        this.button = button;
        this.x = x;
        this.y = y;
    }
    
}
