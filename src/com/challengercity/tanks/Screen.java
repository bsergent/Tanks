
package com.challengercity.tanks;

import java.util.ArrayList;
import static org.lwjgl.opengl.GL11.glColor4f;

/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public abstract class Screen {
    
    private ArrayList<GUI> renderGUIList = new ArrayList<GUI>();
    private TanksMain ug = TanksMain.getGameInstance();
    private int idCount = 0;
    protected int tempScreenId;

    public Screen() {
        Renderer.addToRenderList(this);
    }
    
    public abstract void actionPerformed(int actionId);
    
    public void render() {
        for (int i = 0; i<renderGUIList.size(); i++) {
            glColor4f(1.0f,1.0f,1.0f,1.0f);
            renderGUIList.get(i).draw();
        }
    }
    
    public int getRenderId() {
        int id = idCount;
        idCount++;
        return id;
    }
    
    public ArrayList getRenderList() {
        return renderGUIList;
    }
    
    public void addToRenderList(RenderableObject ro) {
        if (ro instanceof GUI) {
            renderGUIList.add((GUI) ro);
        } else {
            TanksMain.log(Screen.class, "Failed to add GUI to render list, "+ro.getClass());
        }
    }
    
    public void removeFromRenderList(RenderableObject ro) {
        renderGUIList.remove((GUI) ro);
    }
    
    public void tick(long delta) {
    }
    
    public void mouseUpdate() {
        for (int i = 0; i<renderGUIList.size(); i++) {
            renderGUIList.get(i).checkMouse();
        }
    }
    
}
