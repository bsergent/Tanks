
package com.challengercity.tanks;

import com.challengercity.tanks.events.KeyDownEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;
import org.lwjgl.input.Keyboard;

/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class World {

    private UUID worldID;
    private TileEnum[][] worldTiles;
    private ArrayList<Entity> entities = new ArrayList<>();
    public final static short tileSpace = 50;
    
    private final int viewSpeed = 2;

    public World() {
        //worldTiles = new Tile[][] {new Tile[] {new Tile(TileEnum.PLAINS), new Tile(TileEnum.FOREST)},new Tile[] {new Tile(TileEnum.OCEAN), new Tile(TileEnum.MOUNTAINS)}};
        TanksMain.registerNewListener(new com.challengercity.tanks.events.Listener() {

            @Override
            public void onKeyDown(KeyDownEvent e) {
                if (World.this != null) {
                    World.this.keyDown(e.key);
                    
                    if (e.key == Keyboard.KEY_P) { // TODO Remove, only used to screenshot bullet
                        for (Entity e2 : entities) {
                            e2.setVelocity(0f);
                            e2.setTargetVelocity(0f);
                        }
                    }
                }
            }
            
        });
    }

    public void tick(long delta) {
        Entity ent;
        for(Iterator iterator = entities.iterator(); iterator.hasNext();) {
            ent = (Entity) iterator.next();
            ent.tick(delta);
        }
    }
    
    public void addEntity(Entity e) {
        entities.add(e);
    }
    
    public void removeEntity(Entity e) {
        entities.remove(e);
    }
    
    private ArrayList<Entity> getCopyOfEntityList() {
        return (ArrayList<Entity>) entities.clone();
    }
    
    public void checkCollisions() {
        ArrayList<Entity> eList1 = getCopyOfEntityList();
        Iterator<Entity> it1 = eList1.iterator();
        
        while(it1.hasNext()) {
            Entity e1 = it1.next();
            ArrayList<Entity> eList2 = getCopyOfEntityList();
            Iterator<Entity> it2 = eList2.iterator();
            
            while(it2.hasNext()) {
                Entity e2 = it2.next();
                
                if (!e1.equals(e2)) {
                    int r1 = e1.height>e1.width?e1.height/2:e1.width/2;
                    int r2 = e2.height>e2.width?e2.height/2:e2.width/2;
                    int dist = (int) Math.sqrt((Math.pow(e1.posX-e2.posX, 2))+(Math.pow(e1.posY-e2.posY, 2)));
                    if (dist <= r1+r2 && dist >= 0) {
                        TanksMain.callEvent(new com.challengercity.tanks.events.CollisionEvent(e1, e2));
                    }
                }
            }
        }
    }
    
    public boolean checkCollisionsFor(Entity e2) {
        ArrayList<Entity> eList1 = getCopyOfEntityList();
        Iterator<Entity> it1 = eList1.iterator();
        
        while(it1.hasNext()) {
            Entity e1 = it1.next();
            
            if (!e1.equals(e2)) {
                int r1 = e1.height>e1.width?e1.height/2:e1.width/2;
                int r2 = e2.height>e2.width?e2.height/2:e2.width/2;
                int dist = (int) Math.sqrt((Math.pow(e1.posX-e2.posX, 2))+(Math.pow(e1.posY-e2.posY, 2)));
                if (dist <= r1+r2 && dist >= 0) {
                    return true;
                }
            }
        }
        
        return false;
    }

    public void draw() {
//        for (int x = 0; x < worldTiles.length; x++) {
//            for (int y = 0; y < worldTiles[x].length; y++) {
//                if (worldTiles[x][y] != null) {
//                    //worldTiles[x][y].draw((x*Tile.tileSize)+(x*tileSpace),(y*Tile.tileSize)+(y*tileSpace));
//                }
//            }
//        }
        
        Entity ent;
        for(Iterator iterator = entities.iterator(); iterator.hasNext();) {
            ent = (Entity) iterator.next();
            ent.draw();
        }
    }

    private void keyDown(int key) {
        if (key == Keyboard.KEY_LEFT) {
            ViewPort.moveX(-viewSpeed);
        }
        if (key == Keyboard.KEY_RIGHT) {
            ViewPort.moveX(viewSpeed);
        }
        if (key == Keyboard.KEY_UP) {
            ViewPort.moveY(-viewSpeed);
        }
        if (key == Keyboard.KEY_DOWN) {
            ViewPort.moveY(viewSpeed);
        }
    }
    
}
