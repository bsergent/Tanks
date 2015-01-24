
package com.challengercity.tanks.network;

/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class PacketLogin extends Packet {

    public String username;
    public String gameType;
    public String version;

    public PacketLogin() {
    }
    
    public PacketLogin(String username, String gameType, String version) {
        this.username = username;
        this.gameType = gameType;
        this.version = version;
    }
    
}
