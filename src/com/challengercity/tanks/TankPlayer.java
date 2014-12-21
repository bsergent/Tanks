
package com.challengercity.tanks;


import org.lwjgl.input.Keyboard;
import static org.lwjgl.opengl.GL11.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.*;
/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class TankPlayer extends Tank {

    private String username;
    private String texName = "tanks";
    private Color baseColor;
    private TankModel tankModel;
    private int picX = 0;
    private int picY = 0;
    private int picWidth = 64;
    private int picHeight = 64;
    private boolean clientTank = false;
    private int treadAni = 0;

    public TankPlayer() {
    }

    public TankPlayer(int posX, int posY, String username, Color baseColor, TankModel tankModel, boolean clientTank) {
        super(posX,posY, 64, 64);
        this.username = username;
        this.baseColor = baseColor;
        this.tankModel = tankModel;
        this.clientTank = clientTank;
        
        // Temp controller code
        Controller.setControl("moveRight", Keyboard.KEY_D);
        Controller.setControl("moveDown", Keyboard.KEY_S);
        Controller.setControl("moveLeft", Keyboard.KEY_A);
        Controller.setControl("moveUp", Keyboard.KEY_W);
    }

    @Override
    public void tick(long delta) {
        if (clientTank) {
            // Set turret rotation
            int[] mousePos = Controller.getMousePosition();
            float xDistance = mousePos[0] - posX;
            float yDistance = mousePos[1] - posY;
            double newTargetRot = Math.toDegrees(Math.atan2(yDistance, xDistance));
            setTurretTargetRotation((float)newTargetRot<0 ? (float)newTargetRot+360 : (float)newTargetRot);
            
            // Set tank rotation
            float newRot = 0.0f;
            if (Controller.isKeyDown(Controller.getControl("moveRight")) && !Controller.isKeyDown(Controller.getControl("moveUp"))) {
                newRot = Controller.isKeyDown(Controller.getControl("moveDown"))?45.0f:0.0f;
                setAcceleration(0.5f);
            } else if (Controller.isKeyDown(Controller.getControl("moveDown"))) {
                newRot = Controller.isKeyDown(Controller.getControl("moveLeft"))?135.0f:90.0f;
                setAcceleration(0.5f);
            } else if (Controller.isKeyDown(Controller.getControl("moveLeft"))) {
                newRot = Controller.isKeyDown(Controller.getControl("moveUp"))?225.0f:180.0f;
                setAcceleration(0.5f);
            } else if (Controller.isKeyDown(Controller.getControl("moveUp"))) {
                newRot = Controller.isKeyDown(Controller.getControl("moveRight"))?315.0f:270.0f;
                setAcceleration(0.5f);
            } else {
                newRot = getRotation();
                setAcceleration(-0.5f);
            }
            setTargetRotation(newRot);
        }
        super.tick(delta);
    }
    
    @Override
    public void draw() {
        /* Draw base color */
        glTranslatef(ViewPort.getViewX(posX), ViewPort.getViewY(posY), 0.0f);
        glRotatef(getRotation(),0.0f,0.0f,1.0f);
        
        glDisable(GL_TEXTURE_2D);
        glColor4f(baseColor.r,baseColor.g,baseColor.b,1.0f);
        glBegin(GL_QUADS);
        glVertex2i((-width/2)+7, (-height/2)+16);
        glVertex2i((width/2)-7, (-height/2)+16);
        glVertex2i((width/2)-7, (height/2)-16);
        glVertex2i((-width/2)+7, (height/2)-16);
        glEnd();
        glBegin(GL_QUADS);
        glVertex2i((-width/2)+5, (-height/2)+24);
        glVertex2i((-width/2)+7, (-height/2)+24);
        glVertex2i((-width/2)+7, (height/2)-24);
        glVertex2i((-width/2)+5, (height/2)-24);
        glEnd();
        glEnable(GL_TEXTURE_2D);
        
        /* Draw tank */
        glColor4f(1.0f,1.0f,1.0f,1.0f);
        
        Texture texture = ResourcePool.getTexture(texName, ".png");
        texture.bind();
        
        glBegin(GL_QUADS);
        glTexCoord2f(Renderer.getTextureFloat(picX, texture.getImageWidth()), Renderer.getTextureFloat(picY, texture.getImageHeight()+1));  // Upper-Left
        glVertex2i(-width/2, -height/2);

        glTexCoord2f(Renderer.getTextureFloat(picX+picWidth, texture.getImageWidth()), Renderer.getTextureFloat(picY, texture.getImageHeight()+1));  // Upper-Right
        glVertex2i(width/2, -height/2);

        glTexCoord2f(Renderer.getTextureFloat(picX+picWidth, texture.getImageWidth()), Renderer.getTextureFloat(picY+picHeight, texture.getImageHeight()+1));  // Lower-Right
        glVertex2i(width/2, height/2);

        glTexCoord2f(Renderer.getTextureFloat(picX, texture.getImageWidth()), Renderer.getTextureFloat(picY+picHeight, texture.getImageHeight()+1));  // Lower-Left
        glVertex2i(-width/2, height/2);
        glEnd();
        glRotatef(-getRotation(),0.0f,0.0f,1.0f);
        
        /* Draw turret */
        glTranslatef(0.0f, 0.0f, 0.0f);
        glRotatef(getTurretRotation(),0.0f,0.0f,1.0f);
        glBegin(GL_QUADS);
        glTexCoord2f(Renderer.getTextureFloat(picX+picWidth, texture.getImageWidth()), Renderer.getTextureFloat(picY+picHeight, texture.getImageHeight()+1));  // Upper-Left
        glVertex2i(-(width/2)+2, -(height/2)+2);

        glTexCoord2f(Renderer.getTextureFloat(picX+picWidth+56, texture.getImageWidth()), Renderer.getTextureFloat(picY+picHeight, texture.getImageHeight()+1));  // Upper-Right
        glVertex2i((width/2)-2, -(height/2)+2);

        glTexCoord2f(Renderer.getTextureFloat(picX+picWidth+56, texture.getImageWidth()), Renderer.getTextureFloat(picY+picHeight+56, texture.getImageHeight()+1));  // Lower-Right
        glVertex2i((width/2)-2, (height/2)-2);

        glTexCoord2f(Renderer.getTextureFloat(picX+picWidth, texture.getImageWidth()), Renderer.getTextureFloat(picY+picHeight+56, texture.getImageHeight()+1));  // Lower-Left
        glVertex2i(-(width/2)+2, (height/2)-2);
        glEnd();
        glRotatef(-getTurretRotation(),0.0f,0.0f,1.0f);
        glTranslatef(-ViewPort.getViewX(posX), -ViewPort.getViewY(posY), 0.0f);
        
        /* Name String */
        int strPosX = ViewPort.getViewX(posX)-(ResourcePool.getFont("Courier", 16).getWidth(username)/2);
        int strPosY = ViewPort.getViewY(posY)+(height/2)+2;
        ResourcePool.getFont("Courier", 16).drawString(strPosX, strPosY, username);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
