
package com.challengercity.tanks;


import com.challengercity.tanks.events.CollisionEvent;
import com.challengercity.tanks.events.MouseButtonDownEvent;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class TankPlayer extends Tank {

    private String username;
    private boolean clientTank = false;

    public TankPlayer() {
    }

    public TankPlayer(int posX, int posY, String username, Color baseColor, TankModel tankModel, boolean clientTank) {
        super(posX,posY, baseColor, tankModel);
        this.username = username;
        this.clientTank = clientTank;
        
        setTargetVelocity(7.0f);
        
        // Temp controller code
        Controller.setControl("moveRight", Keyboard.KEY_D);
        Controller.setControl("moveDown", Keyboard.KEY_S);
        Controller.setControl("moveLeft", Keyboard.KEY_A);
        Controller.setControl("moveUp", Keyboard.KEY_W);
        
        TanksMain.registerNewListener(new com.challengercity.tanks.events.Listener() {

            @Override
            public void onMouseButtonDown(MouseButtonDownEvent e) {
                if (e.button == 0) {
                    fireBullet(BulletType.BASIC);
                }
            }

            @Override
            public void onCollision(CollisionEvent e) {
                if (TankPlayer.this.equals(e.entity1) && e.entity2 instanceof Bullet) {
                    TanksMain.score = TanksMain.score - 5;
                }
            }
            
        });
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
        super.draw();
        if (visible) {
            /* Name String */
            int strPosX = ViewPort.getViewX(posX)-(ResourcePool.getFont("Courier", 16).getWidth(username)/2);
            int strPosY = ViewPort.getViewY(posY)+(height/2)+2;
            ResourcePool.getFont("Courier", 16).drawString(strPosX, strPosY, username);
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
