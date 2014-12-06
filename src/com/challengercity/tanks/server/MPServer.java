
package com.challengercity.tanks.server;

//import com.challengercity.datura.*;
//import com.challengercity.datura.network.*;
//import com.challengercity.datura.server.saves.DataPlayer;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.yamlbeans.YamlReader;
import com.esotericsoftware.yamlbeans.YamlWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class MPServer {

    private static Server svr;
    public static final int MAXPLAYERS = 16;
    private static final String SPAWNROOM = "room_D";
    private static final int SPAWNX = 24;
    private static final int SPAWNY = 17;
//    private static ArrayList<ConnectionWrapper> connectionWrappers = new ArrayList<ConnectionWrapper>();
//    private static HashMap<String, Room> loadedRooms = new HashMap<String, Room>();
    
    public MPServer() {
//        run();
    }
    
    public static void main(String[] args) {
        MPServer mps = new MPServer();
    }
    
//    private void run() {
//        svr = new Server();
//        com.challengercity.datura.network.ClassRegister.registerClasses(svr.getKryo());
//        svr.start();
//        try {
//            svr.bind(20150, 20151);
//            System.out.println("Server v"+Datura.getVersion()+" started up");
//        } catch (IOException ex) {
//            Logger.getLogger(MPServer.class.getName()).log(Level.SEVERE, "Failed to bind port", ex);
//        }
//        svr.addListener(new Listener() {
//                @Override
//                public void received (Connection connection, java.lang.Object object) {
//                    ConnectionWrapper cw = getWrapperFromConnection(connection);
//                    if (object instanceof PacketLogin) {
//                        PacketLogin request = (PacketLogin)object;
//
//                        PacketLoginResponse rspns = new PacketLoginResponse();
//                        if (request.gameType.equals(Datura.getGameName()) && request.version.equals(Datura.getVersion()) && connectionWrappers.size() < MAXPLAYERS) {
//                            rspns.accept = true;
//                            connection.setName(request.username);
//                            
//                            // Tell the player he/she can join
//                            connection.sendTCP(rspns);
//                            
//                            // Spawn new player entity
//                            DataPlayer dp = loadPlayerData(connection.toString());
//                            EntityPlayer ep = new EntityPlayer(dp.posX*Tile.SIZE,dp.posY*Tile.SIZE,connection.getID(),getRoom(dp.room).getNextEntityId(), request.username);
//                            
//                            // Add it to the spawn room
//                            getRoom(dp.room).addEntity(ep);
//                            
//                            // Tell everyone in the spawn room of the new player
//                            PacketEntity pe = new PacketEntity(ep);
//                            sendTCPRoom(pe, dp.room);
//                            
//                            // Send the player the spawn room
//                            connection.sendTCP(new PacketRoom(getRoom(dp.room)));
//                            
//                            // Add the player's connection wrapper
//                            cw = new ConnectionWrapper(connection);
//                            cw.r = dp.room;
//                            cw.entId = ep.getEntityId();
//                            connectionWrappers.add(cw);
//                            
//                            sendChatAll(connection+" joined the game");
//                            
//                        } else {
//                            rspns.reason = StringHandler.getString("server_deny");
//                            if (!request.gameType.equals(Datura.getGameName())) {
//                                rspns.reason = "Incorrect game";
//                            }
//                            if (!request.version.equals(Datura.getVersion())) {
//                                rspns.reason = "Outdated client. Please download v"+Datura.getVersion();
//                            }
//                            if (connectionWrappers.size() >= MAXPLAYERS) {
//                                rspns.reason = "Server full";
//                            }
//                            rspns.accept = false;
//                            connection.sendTCP(rspns);
//                        }
//                    } else if (object instanceof PacketEntityPosition) {
//                        PacketEntityPosition pep = (PacketEntityPosition) object; // TODO Make server check for pvp collisions
//                        
//                        Entity e = getRoom(getWrapperFromConnection(connection).r).getEntityFromId(pep.id);
//                        if (e != null) {
//                            e.posX = pep.x;
//                            e.posY = pep.y;
//                            e.motionX = pep.velX;
//                            e.motionY = pep.velY;
//                            e.facing = pep.dir;
//                        }
//                        // Make own position packet after checking collision
//                        
//                        sendTCPRoom((PacketEntityPosition) object, cw.r); // TODO Check if it's old
//                    } else if (object instanceof PacketRoomRequest) {
//                        PacketRoomRequest prr = (PacketRoomRequest) object;
//                        
//                        // Remove from current room
//                        getRoom(cw.r).removeEntity(getRoom(cw.r).getEntityFromId(cw.entId));
//                        
//                        // Tell the others in the room to remove him
//                        PacketEntityRemove per = new PacketEntityRemove(cw.entId);
//                        sendTCPRoom(per, cw.r);
//                        
//                        // Add the player to the new room
//                        EntityPlayer ep = new EntityPlayer(prr.door.destX*Tile.SIZE,prr.door.destY*Tile.SIZE,connection.getID(),getRoom(prr.door.destRoom).getNextEntityId(), connection.toString());
//                        getRoom(prr.door.destRoom).addEntity(ep);
//                        cw.r = prr.door.destRoom;
//                        cw.entId = ep.getEntityId();
//                        
//                        // Notify others in room of new player
//                        PacketEntity pe = new PacketEntity(ep);
//                        sendTCPRoom(pe, prr.door.destRoom);
//                        
//                        // Send new room to player
//                        PacketRoom pr = new PacketRoom(getRoom(cw.r));
//                        cw.c.sendTCP(pr);
//                    } else if (object instanceof PacketChat) {
//                        sendChatAll(((PacketChat)object).msg);
//                    }
//                }
//
//            @Override
//            public void disconnected(Connection connection) {
//                ConnectionWrapper cw = getWrapperFromConnection(connection);
//                
//                if (cw != null) {
//                    savePlayerData(cw);
//                    
//                    // Remove from current room
//                    getRoom(cw.r).removeEntity(getRoom(cw.r).getEntityFromId(cw.entId));
//
//                    // Tell the others in the room to remove him
//                    PacketEntityRemove per = new PacketEntityRemove(cw.entId);
//                    sendTCPRoom(per, cw.r);
//
//                    connection.close();
//                    connectionWrappers.remove(cw);
//
//                    sendChatAll(connection+" left the game");
//                }
//            }
//                
//                
//            });
//    }
//    
//    private DataPlayer loadPlayerData(String username) {
//        File file = new File ("Players/"+username+".yml");
//        if (file.exists() && file.canRead()) {
//            try {
//                YamlReader reader = new YamlReader(new FileReader(file));
//                DataPlayer dp = reader.read(DataPlayer.class);
//                reader.close();
//                return dp;
//            } catch (java.io.IOException ex) {
//                // Do nothing
//            }
//        }
//        return new DataPlayer(username, SPAWNX, SPAWNY, SPAWNROOM, new Item[23]);
//    }
//    
//    private void savePlayerData(ConnectionWrapper cw) {
//        EntityPlayer player = (EntityPlayer) getRoom(cw.r).getEntityFromId(cw.entId);
//        DataPlayer dp = new DataPlayer(player.getUsername(), player.posX/Tile.SIZE, player.posY/Tile.SIZE, cw.r, player.getInventory());
//        File file = new File("Players");
//        file.mkdir();
//        file = new File(file+"/"+cw.c.toString()+".yml");
//        try {
//            file.createNewFile();
//            YamlWriter writer = new YamlWriter(new FileWriter(file));
//            writer.write(dp);
//            writer.close();
//        } catch (java.io.IOException ex) {
//            System.err.println("Error saving player "+player.getUsername());
//        }
//    }
//    
//    private Room getRoom(String str) {
//        if (!loadedRooms.containsKey(str)) {
//            loadedRooms.put(str, new Room(str));
//        }
//        return loadedRooms.get(str);
//    }
//    
//    private ConnectionWrapper getWrapperFromConnection(Connection c) {
//        for (ConnectionWrapper cw : connectionWrappers) {
//            if (cw.c == c) {
//                return cw;
//            }
//        }
//        return null;
//    }
//    
//    private void sendTCPRoom(Packet p, String r) {
//        for (ConnectionWrapper cw : connectionWrappers) {
//            if (cw.r.equals(r)) {
//                cw.c.sendTCP(p);
//            }
//        }
//    }
//    
//    private void senUDPRoom(Packet p, String r) {
//        for (ConnectionWrapper cw : connectionWrappers) {
//            if (cw.r.equals(r)) {
//                cw.c.sendUDP(p);
//            }
//        }
//    }
//    
//    private void sendTCPAll(Packet p) {
//        for (ConnectionWrapper cw : connectionWrappers) {
//            cw.c.sendTCP(p);
//        }
//    }
//    
//    private void sendUDPAll(Packet p) {
//        for (ConnectionWrapper cw : connectionWrappers) {
//            cw.c.sendUDP(p);
//        }
//    }
//    
//    private void sendChatAll(String msg) {
//        System.out.println("[All] "+msg);
//        PacketChat pc = new PacketChat(msg);
//        sendTCPAll(pc);
//    }
//    
//    private void sendChatRoom(String msg, String r) {
//        System.out.println("["+r+"] "+msg);
//        PacketChat pc = new PacketChat(msg);
//        sendTCPRoom(pc, r);
//    }
//    
//    private void sendChatConnection(String msg, Connection c) {
//        System.out.println("["+c+"] "+msg);
//        PacketChat pc = new PacketChat(msg);
//        c.sendTCP(pc);
//    }
    
}