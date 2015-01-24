
package com.challengercity.tanks.network;

/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class PacketChat extends Packet{

    public String msg;

    public PacketChat() {
    }
    
    public PacketChat(String msg) {
        this.msg = msg;
    }
    
}
