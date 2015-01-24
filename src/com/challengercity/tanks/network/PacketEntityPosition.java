
package com.challengercity.tanks.network;

/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class PacketEntityPosition extends Packet {

    public int id;
    public int x;
    public int y;
    public byte dir;
    public byte velX;
    public byte velY;

    public PacketEntityPosition() {
    }

    public PacketEntityPosition(int id, int x, int y, byte dir, byte velX, byte velY) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.velX = velX;
        this.velY = velY;
    }
    
}
