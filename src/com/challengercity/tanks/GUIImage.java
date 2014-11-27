/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.challengercity.tanks;

import static org.lwjgl.opengl.GL11.*;
import org.newdawn.slick.opengl.*;
/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class GUIImage extends GUI {
    
    public GUIImage (int x, int y, int width, int height, int picX, int picY, int picWidth, int picHeight, String texName) {
        super(x, y, width, height, picX, picY, picWidth, picHeight);
        this.texName=texName;
    }
    
    public void delete() {
        
    }
    
    public void draw() {
        if (visible) {
            Texture texture = ResourcePool.getTexture(texName, ".png");
            texture.bind();

            glBegin(GL_QUADS);
            glTexCoord2f(Renderer.getTextureFloat(picX, texture.getImageWidth()), Renderer.getTextureFloat(picY, texture.getImageHeight()));  // Upper-Left
            glVertex2i(posX, posY);

            glTexCoord2f(Renderer.getTextureFloat(picX+picWidth, texture.getImageWidth()), Renderer.getTextureFloat(picY, texture.getImageHeight()));  // Upper-Right
            glVertex2i(posX+width, posY);

            glTexCoord2f(Renderer.getTextureFloat(picX+picWidth, texture.getImageWidth()), Renderer.getTextureFloat(picY+picHeight, texture.getImageHeight()));  // Lower-Right
            glVertex2i(posX+width, posY+height);

            glTexCoord2f(Renderer.getTextureFloat(picX, texture.getImageWidth()), Renderer.getTextureFloat(picY+picHeight, texture.getImageHeight()));  // Lower-Left
            glVertex2i(posX, posY+height);
            glEnd();
        }
    }
    
    public void checkMouse() {
    }
    
}
