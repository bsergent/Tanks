
package com.challengercity.tanks.network;

/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class PacketEntityPlayerMove extends Packet {

    public int playerId;
    public int motionX;
    public int motionY;

    public PacketEntityPlayerMove() {
    }

    public PacketEntityPlayerMove(int playerId, int motionX, int motionY) {
        this.playerId = playerId;
        this.motionX = motionX;
        this.motionY = motionY;
    }
    
}
