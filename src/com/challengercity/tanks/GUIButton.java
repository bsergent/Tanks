
package com.challengercity.tanks;

import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;

/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class GUIButton extends GUI {

    private Texture texture;
    private TrueTypeFont font;
    private String label;
    private int fontSize = 16;
    private int hovered = 0;
    public int actionId;
    
    public GUIButton (int x, int y, int width, int height, int actionId, String text, int fontSize) {
        super(x, y, width, height, 1, 0, 128, 24);
        this.actionId = actionId;
        this.label = text;
        this.fontSize = fontSize;
    }

    @Override
    public void checkMouse() {
        if (Mouse.getX()>posX&&Mouse.getX()<(posX+width) && (TanksMain.screenHeight-Mouse.getY())>posY&&(TanksMain.screenHeight-Mouse.getY())<(posY+height) && TanksMain.guiCooldown<=0) {
            hovered = 1;
            if (Mouse.isButtonDown(0)) {
                TanksMain.currentScreen.actionPerformed(actionId);
                TanksMain.guiCooldown=60;
            }
        } else {
            hovered = 0;
        }
        TanksMain.guiCooldown--;
    }

    @Override
    public void draw() {
        if (visible) {
            if (texture == null) {
                texture = ResourcePool.getTexture("gui_button", ".png");
            }
            if (font == null) {
                font = ResourcePool.getFont("Courier", 16);
            }
            
            texture.bind();

//            glBegin(GL_QUADS);
//            glTexCoord2f(Renderer.getTextureFloat(picX, texture.getImageWidth()), Renderer.getTextureFloat(picY+(hovered*picHeight), texture.getImageHeight()));  // Upper-Left
//            glVertex2i(posX, posY);
//
//            glTexCoord2f(Renderer.getTextureFloat(picX+picWidth, texture.getImageWidth()), Renderer.getTextureFloat(picY+(hovered*picHeight), texture.getImageHeight()));  // Upper-Right
//            glVertex2i(posX+width, posY);
//
//            glTexCoord2f(Renderer.getTextureFloat(picX+picWidth, texture.getImageWidth()), Renderer.getTextureFloat(picY+picHeight+(hovered*picHeight), texture.getImageHeight()));  // Lower-Right
//            glVertex2i(posX+width, posY+height);
//
//            glTexCoord2f(Renderer.getTextureFloat(picX, texture.getImageWidth()), Renderer.getTextureFloat(picY+picHeight+(hovered*picHeight), texture.getImageHeight()));  // Lower-Left
//            glVertex2i(posX, posY+height);
//            glEnd();
            
            /* Draw Left Section */
            glBegin(GL_QUADS);
            glTexCoord2f(Renderer.getTextureFloat(picX, texture.getImageWidth()), Renderer.getTextureFloat(picY+(hovered*picHeight), texture.getImageHeight()));  // Upper-Left
            glVertex2i(posX, posY);

            glTexCoord2f(Renderer.getTextureFloat(picX+30, texture.getImageWidth()), Renderer.getTextureFloat(picY+(hovered*picHeight), texture.getImageHeight()));  // Upper-Right
            glVertex2i(posX+30, posY);

            glTexCoord2f(Renderer.getTextureFloat(picX+30, texture.getImageWidth()), Renderer.getTextureFloat(picY+picHeight+(hovered*picHeight), texture.getImageHeight()));  // Lower-Right
            glVertex2i(posX+30, posY+height);

            glTexCoord2f(Renderer.getTextureFloat(picX, texture.getImageWidth()), Renderer.getTextureFloat(picY+picHeight+(hovered*picHeight), texture.getImageHeight()));  // Lower-Left
            glVertex2i(posX, posY+height);
            glEnd();
            
            /* Draw Middle Section */
            glBegin(GL_QUADS);
            glTexCoord2f(Renderer.getTextureFloat(picX+30, texture.getImageWidth()), Renderer.getTextureFloat(picY+(hovered*picHeight), texture.getImageHeight()));  // Upper-Left
            glVertex2i(posX+30, posY);

            glTexCoord2f(Renderer.getTextureFloat(picX+picWidth-30, texture.getImageWidth()), Renderer.getTextureFloat(picY+(hovered*picHeight), texture.getImageHeight()));  // Upper-Right
            glVertex2i(posX+width-30, posY);

            glTexCoord2f(Renderer.getTextureFloat(picX+picWidth-30, texture.getImageWidth()), Renderer.getTextureFloat(picY+picHeight+(hovered*picHeight), texture.getImageHeight()));  // Lower-Right
            glVertex2i(posX+width-30, posY+height);

            glTexCoord2f(Renderer.getTextureFloat(picX+30, texture.getImageWidth()), Renderer.getTextureFloat(picY+picHeight+(hovered*picHeight), texture.getImageHeight()));  // Lower-Left
            glVertex2i(posX+30, posY+height);
            glEnd();
            
            /* Draw Right Section */
            glBegin(GL_QUADS);
            glTexCoord2f(Renderer.getTextureFloat(picX+picWidth-30, texture.getImageWidth()), Renderer.getTextureFloat(picY+(hovered*picHeight), texture.getImageHeight()));  // Upper-Left
            glVertex2i(posX+width-30, posY);

            glTexCoord2f(Renderer.getTextureFloat(picX+picWidth, texture.getImageWidth()), Renderer.getTextureFloat(picY+(hovered*picHeight), texture.getImageHeight()));  // Upper-Right
            glVertex2i(posX+width, posY);

            glTexCoord2f(Renderer.getTextureFloat(picX+picWidth, texture.getImageWidth()), Renderer.getTextureFloat(picY+picHeight+(hovered*picHeight), texture.getImageHeight()));  // Lower-Right
            glVertex2i(posX+width, posY+height);

            glTexCoord2f(Renderer.getTextureFloat(picX+picWidth-30, texture.getImageWidth()), Renderer.getTextureFloat(picY+picHeight+(hovered*picHeight), texture.getImageHeight()));  // Lower-Left
            glVertex2i(posX+width-30, posY+height);
            glEnd();
            
            int strPosX = posX+width/2-(font.getWidth(label)/2);
            int strPosY = posY+height/2-(font.getHeight(label)/2);
            font.drawString(strPosX, strPosY, label);
        }
    }

    @Override
    public void delete() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
