
package com.challengercity.tanks.events;

import com.challengercity.tanks.Entity;

/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class CollisionEvent extends Event {

    public com.challengercity.tanks.Entity entity1;
    public com.challengercity.tanks.Entity entity2;

    public CollisionEvent(Entity e1, Entity e2) {
        this.entity1 = e1;
        this.entity2 = e2;
    }
    
}
