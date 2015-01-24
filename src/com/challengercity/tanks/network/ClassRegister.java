
package com.challengercity.tanks.network;

import com.esotericsoftware.kryo.Kryo;
import java.util.ArrayList;

/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class ClassRegister {

    public static void registerClasses(Kryo kryo) {
        kryo.register(int[][].class);
        kryo.register(int[].class);
        kryo.register(ArrayList.class);
        kryo.register(java.awt.Rectangle.class);
        kryo.register(PacketLogin.class);
        kryo.register(PacketLoginResponse.class);
        kryo.register(PacketChat.class);
        kryo.register(PacketEntity.class);
        kryo.register(PacketEntityPosition.class);
        kryo.register(PacketEntityRemove.class);
        kryo.register(com.challengercity.tanks.Bullet.class);
        kryo.register(com.challengercity.tanks.RenderableObject.class);
        kryo.register(com.challengercity.tanks.Entity.class);
        kryo.register(com.challengercity.tanks.Tank.class);
        kryo.register(com.challengercity.tanks.TankPlayer.class);
        kryo.register(com.challengercity.tanks.TankNPC.class);
    }
    
}
