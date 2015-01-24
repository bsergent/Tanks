
package com.challengercity.tanks.events;

import com.challengercity.tanks.Entity;

/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class OutsideWorldBorderEvent extends Event {

    public com.challengercity.tanks.Entity entity;
    public OutsideWorldBorderType type;

    public OutsideWorldBorderEvent(Entity entity, OutsideWorldBorderType type) {
        this.entity = entity;
        this.type = type;
    }
    
    public enum OutsideWorldBorderType {
        TOUCHING, OUTSIDE
    }
    
}
