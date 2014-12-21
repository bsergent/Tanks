
package com.challengercity.tanks;

/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public abstract class Tank extends Entity {
    
    private float turretRotation = 0f;
    private float turretTargetRotation = 0f;
    private float turretRotationSpeed = 0f;
    private float turretMaxRotationSpeed = 5f;

    public Tank() {
    }

    public Tank(int posX, int posY, int width, int height) {
        super(posX, posY, width, height);
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
    
}
