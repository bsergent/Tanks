
package com.challengercity.tanks;

import com.challengercity.tanks.events.CollisionEvent;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2i;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class Tank extends Entity {
    
    private float turretRotation = 0f;
    private float turretTargetRotation = 0f;
    private float turretRotationSpeed = 0f;
    private final float turretMaxRotationSpeed = 5f;
    protected long lastBulletFired = 0;
    public static final long bulletCooldown = 1000;
    public short maxBulletsAllowed = 5;
    protected Color baseColor;
    protected TankModel tankModel;
    private final String texName = "tanks";
    private final int picX = 0;
    private final int picY = 0;
    private final int picWidth = 64;
    private final int picHeight = 64;
    private int treadAni = 0;

    public Tank() {
    }

    public Tank(int posX, int posY, Color baseColor, TankModel tankModel) {
        super(posX, posY, tankModel.width, tankModel.height);
        this.baseColor = baseColor;
        this.tankModel = tankModel;
        
        TanksMain.registerNewListener(new com.challengercity.tanks.events.Listener() {

            @Override
            public void onCollision(CollisionEvent e) {
                if (Tank.this.equals(e.entity1) && e.entity2 instanceof Tank) {
                    Tank.this.revertMovement();
                    Tank.this.setVelocity(0.0f);
                }
            }
            
        });
    }

    public float getTurretRotation() {
        return turretRotation;
    }

    public void setTurretRotation(float turretRotation) {
        while (turretRotation >= 360) {
            turretRotation -= 360;
        }
        while (turretRotation < 0) {
            turretRotation += 360;
        }
        this.turretRotation = turretRotation;
    }

    public float getTurretTargetRotation() {
        return turretTargetRotation;
    }

    public void setTurretTargetRotation(float targetTurretRotation) {
        while (targetTurretRotation >= 360) {
            targetTurretRotation -= 360;
        }
        while (targetTurretRotation < 0) {
            targetTurretRotation += 360;
        }
        this.turretTargetRotation = targetTurretRotation;
    }

    public float getTurretRotationSpeed() {
        return turretRotationSpeed;
    }

    public void setTurretRotationSpeed(float turretRotationSpeed) {
        this.turretRotationSpeed = turretRotationSpeed;
    }
    
    public boolean fireBullet(BulletType bulletType) {
        if ((System.currentTimeMillis()-lastBulletFired) >= bulletCooldown && TanksMain.getCurrentWorld() != null) {
            Bullet b = new Bullet((int) (posX+(42*Math.cos(Math.toRadians(turretRotation)))),  (int) (posY+(42*Math.sin(Math.toRadians(turretRotation)))), turretRotation, baseColor, bulletType);
            b.setTargetVelocity(10f);
            b.setVelocity(10f);
            TanksMain.getCurrentWorld().addEntity(b);
            return true;
        }
        return false;
    }

    @Override
    public void tick(long delta) {
        if (turretRotation != turretTargetRotation) {
            turretRotationSpeed = turretMaxRotationSpeed;
            if (Math.abs(turretRotation-turretTargetRotation) <= turretRotationSpeed) {
                setTurretRotation(turretTargetRotation);
            } else {
                float distA = turretTargetRotation - turretRotation;  // distance in pos+ direction
                if (distA < 0) distA += 360;
                float distB = turretRotation - turretTargetRotation; // distance in neg- direction
                if (distB < 0) distB += 360;
                if (distA < distB) {
                    setTurretRotation(turretRotation + turretRotationSpeed);
                } else {
                    setTurretRotation(turretRotation - turretRotationSpeed);
                }
            }
        } else {
            turretRotationSpeed = 0.0f;
        }
        super.tick(delta);
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
        }
    }
    
}
