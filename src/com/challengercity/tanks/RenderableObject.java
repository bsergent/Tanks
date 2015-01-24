
package com.challengercity.tanks;

import java.awt.Rectangle;

/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public abstract class RenderableObject {

    public int posX, posY, width, height;
    protected boolean visible = true;
    protected Rectangle hitbox = new Rectangle();
    
    public abstract void draw();
    public abstract void delete();

    public RenderableObject() {
    }
    
    public RenderableObject(int posX, int posY, int width, int height) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
    }
    
    public void setVisible(boolean bl) {
        visible = bl;
    }
    
    public Rectangle getHitbox(){
        hitbox.setBounds(posX, posY, width, height);
        return hitbox;
    }
    
//    public boolean intersects(RenderableObject ro) {
//	return getHitbox().intersects(new Rectangle(ro.getHitbox()));
//    }
//    
//    public boolean intersectsField(int x, int y, int width, int height) {
//	return getHitbox().intersects(new Rectangle(x, y, width, height));
//    }
//    
//    public boolean intersectsOffset(RenderableObject ro, int offsetX, int offsetY) {
//        Rectangle offsetHitbox = new Rectangle(getHitbox().x+offsetX, getHitbox().y+offsetY, getHitbox().width, getHitbox().height);
//	return offsetHitbox.intersects(new Rectangle(ro.getHitbox()));
//    }
    
}
