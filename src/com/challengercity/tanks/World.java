
package com.challengercity.tanks;

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
        Controller.addListenerKeyboard(new ListenerKeyboard(true) {

            @Override
            public void keyDown(int key) {
                if (World.this != null) {
                    World.this.keyDown(key);
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
