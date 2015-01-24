
package com.challengercity.tanks.network;

/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class PacketLoginResponse extends Packet {

    public boolean accept;
    public String reason;

    public PacketLoginResponse() {
    }
    
    public PacketLoginResponse(boolean accept, String reason) {
        this.accept = accept;
        this.reason = reason;
    }
    
}
