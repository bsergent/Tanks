
package com.challengercity.tanks.network;

import com.challengercity.tanks.Entity;

/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class PacketEntity extends Packet {

    public Entity entity;

    public PacketEntity() {
    }

    public PacketEntity(Entity entity) {
        this.entity = entity;
    }
    
}
