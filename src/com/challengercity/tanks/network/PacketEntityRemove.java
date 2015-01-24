
package com.challengercity.tanks.network;

/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class PacketEntityRemove extends Packet {

    public int entityId;

    public PacketEntityRemove() {
    }

    public PacketEntityRemove(int entityId) {
        this.entityId = entityId;
    }
    
}
