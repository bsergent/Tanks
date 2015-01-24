
package com.challengercity.tanks;

import com.challengercity.tanks.events.CollisionEvent;
import static org.lwjgl.opengl.GL11.*;
import org.newdawn.slick.Color;
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
    private Color baseColor;
    private byte lastAni = 0;
    private BulletType type;

    public Bullet() {
    }

    public Bullet(int posX, int posY, float rotation, Color color, BulletType type) {
        super(posX, posY, 24, 12);
        this.setTargetRotation(rotation);
        this.setRotation(rotation);
        this.baseColor = color;
        setBulletType(type);
        TanksMain.registerNewListener(new com.challengercity.tanks.events.Listener() {
            @Override
            public void onCollision(CollisionEvent e) {
                if (Bullet.this.equals(e.entity1)) {
                    Bullet.this.delete();
                }
            }
        });
    }
    
    private void setBulletType(BulletType type) {
        this.type = type;
        switch (this.type) {
            case BASIC:
                picX = 0;
                picY = 0;
                this.setVelocity(10.0f);
                this.setAcceleration(10.0f);
                break;
            case FAST:
                picX = 24;
                picY = 0;
                this.setVelocity(20.0f);
                this.setAcceleration(20.0f);
                break;
            case ANGLER:
                picX = 0;
                picY = 0;
                this.setVelocity(10.0f);
                this.setAcceleration(10.0f);
                break;
            case EXPLOSIVE:
                picX = 0;
                picY = 0;
                this.setVelocity(10.0f);
                this.setAcceleration(10.0f);
                break;
        }
    }
    
    @Override
    public void draw() {
        if (visible) {
            /* Draw base color */
            glTranslatef(ViewPort.getViewX(posX), ViewPort.getViewY(posY), 0.0f);
            glRotatef(getRotation(),0.0f,0.0f,1.0f);

            glDisable(GL_TEXTURE_2D);
            glColor4f(baseColor.r,baseColor.g,baseColor.b,1.0f);
            glBegin(GL_QUADS);
            glVertex2i(4, -3);
            glVertex2i(7, -3);
            glVertex2i(7, 3);
            glVertex2i(4, 3);
            glEnd();
            glEnable(GL_TEXTURE_2D);

            /* Draw bullet */
            glColor4f(1.0f,1.0f,1.0f,1.0f);

            Texture texture = ResourcePool.getTexture("bullets", ".png");
            texture.bind();

            glBegin(GL_QUADS);
            glTexCoord2f(Renderer.getTextureFloat(picX, texture.getImageWidth()), Renderer.getTextureFloat(picY+(lastAni*picHeight), texture.getImageHeight()+1));  // Upper-Left
            glVertex2i(-width/2, -height/2);

            glTexCoord2f(Renderer.getTextureFloat(picX+picWidth, texture.getImageWidth()), Renderer.getTextureFloat(picY+(lastAni*picHeight), texture.getImageHeight()+1));  // Upper-Right
            glVertex2i(width/2, -height/2);

            glTexCoord2f(Renderer.getTextureFloat(picX+picWidth, texture.getImageWidth()), Renderer.getTextureFloat(picY+picHeight+(lastAni*picHeight), texture.getImageHeight()+1));  // Lower-Right
            glVertex2i(width/2, height/2);

            glTexCoord2f(Renderer.getTextureFloat(picX, texture.getImageWidth()), Renderer.getTextureFloat(picY+picHeight+(lastAni*picHeight), texture.getImageHeight()+1));  // Lower-Left
            glVertex2i(-width/2, height/2);
            glEnd();
            glRotatef(-getRotation(),0.0f,0.0f,1.0f);
            glTranslatef(-ViewPort.getViewX(posX), -ViewPort.getViewY(posY), 0.0f);
            
            if (this.getVelocity() != 0 && Math.random()>0.9) {
                lastAni++;
            }
            if (lastAni > 4) {
                lastAni = 0;
            }
        }
    }

}
