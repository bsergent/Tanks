
package com.challengercity.tanks;

/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public abstract class Entity extends RenderableObject {

    private float rotation = 0f;
    private float targetRotation = 0f;
    private float rotationSpeed = 0f;
    private float maxRotationSpeed = 1.5f;
    private float velocity = 0f;
    private float targetVelocity = 0f;
    private final float maxVelocity = 20f;
    private float acceleration = 0f;
    private int lastPosX, lastPosY;
    private float lastRotation = 0f;

    public Entity() {
    }

    public Entity(int posX, int posY, int width, int height) {
        super(posX, posY, width, height);
        this.lastPosX = posX;
        this.lastPosY = posY;
    }
    
    public void tick(long delta) {
        lastPosX = posX;
        lastPosY = posY;
        lastRotation = rotation;
        
        // Rotation
        if (rotation != targetRotation) {
            rotationSpeed = maxRotationSpeed;
            if (Math.abs(rotation-targetRotation) <= rotationSpeed) {
                setRotation(targetRotation);
            } else {
                float distA = targetRotation - rotation;  // distance in pos+ direction
                if (distA < 0) distA += 360;
                float distB = rotation - targetRotation; // distance in neg- direction
                if (distB < 0) distB += 360;
                if (distA < distB) {
                    setRotation(rotation + rotationSpeed);
                } else {
                    setRotation(rotation - rotationSpeed);
                }
            }
        } else {
            rotationSpeed = 0.0f;
        }
        
        // Velocity
        if (rotation == targetRotation) {
            setVelocity(velocity+acceleration);
        } else if (Math.abs(targetRotation-rotation) >= 90) {
            setVelocity(velocity-acceleration);
        }
        if (targetVelocity-velocity < acceleration) {
            setVelocity(targetVelocity);
        }
//        if (velocity == 0 && acceleration < 0) {
//            acceleration = 0;
//        } else if (velocity >= maxVelocity && acceleration > 0) {
//            acceleration = 0;
//        }
        
        // Position
        posX = posX + (int)(velocity * Math.cos(Math.toRadians(rotation)));
        posY = posY + (int)(velocity * Math.sin(Math.toRadians(rotation)));
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        while (rotation >= 360) {
            rotation -= 360;
        }
        while (rotation < 0) {
            rotation += 360;
        }
        this.rotation = rotation;
    }

    public float getTargetRotation() {
        return targetRotation;
    }

    public void setTargetRotation(float targetRotation) {
        while (targetRotation >= 360) {
            targetRotation -= 360;
        }
        while (targetRotation < 0) {
            targetRotation += 360;
        }
        this.targetRotation = targetRotation;
    }

    public float getMaxRotationSpeed() {
        return rotationSpeed;
    }

    public void setMaxRotationSpeed(float maxRotationSpeed) {
        this.maxRotationSpeed = maxRotationSpeed;
    }

    public float getVelocity() {
        return velocity;
    }

    public void setVelocity(float velocity) {
        if (velocity > maxVelocity) {
            velocity = maxVelocity;
        } else if (velocity < 0) {
            velocity = 0;
        }
        this.velocity = velocity;
    }

    public float getTargetVelocity() {
        return targetVelocity;
    }

    public void setTargetVelocity(float targetVelocity) {
        this.targetVelocity = targetVelocity>maxVelocity?maxVelocity:targetVelocity;
    }

    public float getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(float acceleration) {
        this.acceleration = acceleration;
    }
    
    public void revertMovement() {
        posX = lastPosX;
        posY = lastPosY;
        rotation = lastRotation;
    }
    
    @Override
    public void delete() {
        TanksMain.getCurrentWorld().removeEntity(this);
    }
    
}
