
package com.challengercity.tanks;

/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public abstract class GUI extends RenderableObject {

    protected int picX, picY, picWidth, picHeight;
    protected String texName;
    protected String texExt;
    
    public GUI(int x, int y, int width, int height, int picX, int picY, int picWidth, int picHeight) {
        super(x, y, width, height);
        this.picX=picX-1;
        this.picY=picY;
        this.picWidth=picWidth;
        this.picHeight=picHeight;
        
    }
    
    public abstract void checkMouse();
    
}
