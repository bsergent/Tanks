
package com.challengercity.tanks;

import java.util.ArrayList;
import org.lwjgl.opengl.Display;
import static org.lwjgl.opengl.GL11.*;

/**
 *
 * @author Ben Sergent V <bsergentv@gmail.com>
 */
public class Renderer {

    private static ArrayList<Screen> renderList;
    private TanksMain ug;

    public Renderer(TanksMain ug) {
        
        this.ug = ug;
        renderList = new ArrayList<>();
        TanksMain.log(getClass(), "Initialized");
        
    }
    
    public static void addToRenderList(Screen sc) {
        renderList.add(sc);
    }
    
    public static void removeFromRenderList(Screen sc) {
        renderList.remove(sc.tempScreenId);
    }
    
    public void render() {
        glMatrixMode(GL_PROJECTION);
        glEnable(GL_TEXTURE_2D);
        Display.setVSyncEnabled(true);
        glLoadIdentity(); // Resets any previous projection matrices
        glOrtho(0, TanksMain.screenWidth, TanksMain.screenHeight, 0, 1, -1);
        
        glClear(GL_COLOR_BUFFER_BIT);
        glEnable (GL_BLEND);
        glBlendFunc (GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        
        for (int i = 0; i<renderList.size(); i++) {
            renderList.get(i).render();
        }
        Display.update();
    }
    
    public static float getTextureFloat(int num, int max) {
        return ((float)num)/max;
    }
    
}
