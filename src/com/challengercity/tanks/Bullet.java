
package com.challengercity.tanks;

import static org.lwjgl.opengl.GL11.*;
import org.newdawn.slick.opengl.Texture;

/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class Bullet extends Entity {

    private static int picX = 0;
    private static int picY = 0;
    private static int picWidth = 24;
    private static int picHeight = 12;
    private byte lastAni = 0;
    
    @Override
    public void draw() {
        glRotatef(rotation, 0.0f, 0.0f, 1.0f);
        if (visible) {
            Texture texture = ResourcePool.getTexture("bullets", ".png");
            texture.bind();

            glBegin(GL_QUADS);
            glTexCoord2f(Renderer.getTextureFloat(picX, texture.getImageWidth()), Renderer.getTextureFloat(picY, texture.getImageHeight()+1));  // Upper-Left
            glVertex2i(posX, posY);

            glTexCoord2f(Renderer.getTextureFloat(picX+picWidth, texture.getImageWidth()), Renderer.getTextureFloat(picY, texture.getImageHeight()+1));  // Upper-Right
            glVertex2i(posX+width, posY);

            glTexCoord2f(Renderer.getTextureFloat(picX+picWidth, texture.getImageWidth()), Renderer.getTextureFloat(picY+picHeight, texture.getImageHeight()+1));  // Lower-Right
            glVertex2i(posX+width, posY+height);

            glTexCoord2f(Renderer.getTextureFloat(picX, texture.getImageWidth()), Renderer.getTextureFloat(picY+picHeight, texture.getImageHeight()+1));  // Lower-Left
            glVertex2i(posX, posY+height);
            glEnd();
        }
    }

}
