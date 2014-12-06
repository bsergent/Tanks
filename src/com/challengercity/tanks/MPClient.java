
package com.challengercity.tanks;

//import com.challengercity.datura.network.*;
//import com.esotericsoftware.kryonet.Client;
//import com.esotericsoftware.kryonet.Connection;
//import com.esotericsoftware.kryonet.Listener;
import java.io.IOException;

/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class MPClient {

//    private static Client clnt;
    
    public MPClient() {
        
    }
    
//    public static void connectMP() {
//        if (clnt == null) {
//            /* Establish Connection */
//            clnt = new Client(8192, 4096);
//            com.challengercity.datura.network.ClassRegister.registerClasses(clnt.getKryo());
//            clnt.start();
//            try {
//                clnt.connect(5000, Datura.DEBUG?"localhost":"play.challengercity.com", 20150, 20151); // Time-out, address, TCP port, UDP port
//            } catch (IOException ex) {
//                Datura.log(MPClient.class, "Lost connection");
//                clnt.stop();
//                clnt = null;
//                Datura.setScreen(new ScreenDisconnected(StringHandler.getString("server_lost")));
//                return;
//            }
//
//            PacketLogin pLogin = new PacketLogin(Datura.getUsername(), Datura.getGameName(), Datura.getVersion());
//            clnt.sendTCP(pLogin);
//
//            /* Packet Reception Listener */
//            clnt.addListener(new Listener() {
//                @Override
//                public void received (Connection connection, java.lang.Object object) {
//                    if (object instanceof PacketLoginResponse) {
//                        PacketLoginResponse response = (PacketLoginResponse) object;
//                        if (response.accept) {
//                            Datura.setScreen(new ScreenGame());
//                            Datura.log(MPClient.class, "Connected to server");
//                        } else {
//                            clnt.stop();
//                            clnt = null;
//                            Datura.setScreen(new ScreenDisconnected(response.reason));
//                        }
//                    } else if (object instanceof PacketRoom) {
//                        PacketRoom rsp = (PacketRoom) object;
//                        if (Datura.currentScreen instanceof ScreenGame) {
//                            ((ScreenGame) Datura.currentScreen).setCurrentRoom(rsp.room);
//                            ViewPort.centerView(15*Tile.SIZE, 15*Tile.SIZE);
//                            ScreenGame.requestingRoom = false;
//                        }
//                    } else if (object instanceof PacketEntityPosition) {
//                        PacketEntityPosition pep = (PacketEntityPosition) object;
//                        Entity e = Datura.getCurrentRoom().getEntityFromId(pep.id);
//                        if (e != null) {
//                            e.posX = pep.x;
//                            e.posY = pep.y;
//                            e.motionX = pep.velX;
//                            e.motionY = pep.velY;
//                            e.facing = pep.dir;
//                        }
//                        connection.updateReturnTripTime();
//                        Datura.lastPing = connection.getReturnTripTime();
//                    } else if (object instanceof PacketEntity) {
//                        PacketEntity pe = (PacketEntity) object;
//                        Datura.getCurrentRoom().addEntity(pe.entity);
//                        Datura.getCurrentRoom().getNextEntityId();
//                    } else if (object instanceof PacketEntityRemove) {
//                        PacketEntityRemove per = (PacketEntityRemove) object;
//                        Datura.getCurrentRoom().removeEntity(Datura.getCurrentRoom().getEntityFromId(per.entityId));
//                    } else if (object instanceof PacketChat) {
//                        PacketChat pc = (PacketChat) object;
//                        if (Datura.currentScreen instanceof ScreenGame) {
//                            ((ScreenGame) Datura.currentScreen).chatWindow.addChat(pc.msg);
//                        }
//                    }
//                }
//
//                @Override
//                public void disconnected(Connection connection) {
//                    super.disconnected(connection);
//                    Datura.log(MPClient.class, "Lost connection");
//                    clnt = null;
//                    Datura.setScreen(new ScreenDisconnected(StringHandler.getString("server_lost")));
//                }
//                
//                
//            });
//        } else {
//            Datura.log(MPClient.class, "Connection already open");
//        }
//    }
//    
//    public static int getOwnId() {
//        if (clnt == null) {
//            return 0;
//        }
//        return clnt.getID();
//    }
//    
//    public static void sendTCPPacket(Packet pkt) {
//        clnt.sendTCP(pkt);
//    }
//    
//    public static void sendUDPPacket(Packet pkt) {
//        clnt.sendUDP(pkt);
//    }
//    
//    public static void disconnectMP() {
//        Datura.log(MPClient.class, "Disconnected");
//        //clnt.close();
//        clnt.stop();
//        clnt = null;
//        
//        Datura.setScreen(new ScreenDisconnected("server_disconnect"));
//    }
    
}
