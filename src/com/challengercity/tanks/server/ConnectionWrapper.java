
package com.challengercity.tanks.server;

import com.esotericsoftware.kryonet.Connection;

/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class ConnectionWrapper {

    public Connection c;
    public String r;
    public int entId;

    public ConnectionWrapper(Connection c) {
        this.c = c;
    }
    
}
