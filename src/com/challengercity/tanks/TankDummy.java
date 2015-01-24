
package com.challengercity.tanks;

import com.challengercity.tanks.events.CollisionEvent;
import org.newdawn.slick.Color;

/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class TankDummy extends Tank {

    public TankDummy() {
    }

    public TankDummy(int posX, int posY, Color baseColor, TankModel tankModel) {
        super(posX, posY, baseColor, tankModel);
        TanksMain.registerNewListener(new com.challengercity.tanks.events.Listener() {
            @Override
            public void onCollision(CollisionEvent e) {
                if (TankDummy.this.equals(e.entity1) && e.entity2 instanceof Bullet) {
                    java.util.Random rand = new java.util.Random();
                    
                    do {
                        TankDummy.this.posX = rand.nextInt(TanksMain.screenWidth-tankModel.width)+(tankModel.width/2);
                        TankDummy.this.posY = rand.nextInt(TanksMain.screenHeight-tankModel.height)+(tankModel.height/2);
                    } while (TanksMain.getCurrentWorld().checkCollisionsFor(TankDummy.this));
                    
                    float tankRot = rand.nextInt(360);
                    float turretRot = rand.nextInt(360);
                    TankDummy.this.setTargetRotation(tankRot);
                    TankDummy.this.setRotation(tankRot);
                    TankDummy.this.setTurretTargetRotation(turretRot);
                    TankDummy.this.setTurretRotation(turretRot);
                    
                    if (rand.nextInt(4) == 0) {
                        TankDummy.this.fireBullet(BulletType.BASIC);
                    }
                    
                    TanksMain.score++;
                }
            }
        });
    }

}
