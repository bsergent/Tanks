/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.challengercity.tanks;

import org.newdawn.slick.TrueTypeFont;
/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class GUIText extends GUI {

    protected static TrueTypeFont font48;
    protected static TrueTypeFont font36;
    protected static TrueTypeFont font24;
    protected static TrueTypeFont font16;
    protected static TrueTypeFont font12;
    protected String label;
    protected int fontSize;
    protected boolean centered;
    protected org.newdawn.slick.Color color;
    
    public GUIText (int x, int y, String text, int fontSize, boolean centered) {
        super(x, y, 0, 0, 1, 0, 128, 24);
        this.label = text;
        this.centered = centered;
        this.fontSize = fontSize;
        this.color = new org.newdawn.slick.Color(0, 0, 0, 255);
    }
    
    public GUIText (int x, int y, Screen screen, String text, int fontSize, boolean centered, org.newdawn.slick.Color color) {
        super(x, y, 0, 0, 1, 0, 128, 24);
        this.label = text;
        this.centered = centered;
        this.fontSize = fontSize;
        this.color = color;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    
    public void draw() {
        if (visible) {
            if (font48 == null || font36 == null || font24 == null || font16 == null || font12 == null) {
                loadFonts();
            }
            int strPosX = posX;
            int strPosY = posY;
            switch (fontSize) {
                case 48:
                    if (centered) {
                        strPosX = posX+width/2-(font48.getWidth(label)/2);
                        strPosY = posY+height/2-(font48.getHeight(label)/2);
                    }
                    font48.drawString(strPosX, strPosY, label, color);
                    break;
                case 36:
                    if (centered) {
                        strPosX = posX+width/2-(font36.getWidth(label)/2);
                        strPosY = posY+height/2-(font36.getHeight(label)/2);
                    }
                    font36.drawString(strPosX, strPosY, label, color);
                    break;
                case 24:
                    if (centered) {
                        strPosX = posX+width/2-(font24.getWidth(label)/2);
                        strPosY = posY+height/2-(font24.getHeight(label)/2);
                    }
                    font24.drawString(strPosX, strPosY, label, color);
                    break;
                case 16:
                    if (centered) {
                        strPosX = posX+width/2-(font16.getWidth(label)/2);
                        strPosY = posY+height/2-(font16.getHeight(label)/2);
                    }
                    font16.drawString(strPosX, strPosY, label, color);
                    break;
                case 12:
                    if (centered) {
                        strPosX = posX+width/2-(font12.getWidth(label)/2);
                        strPosY = posY+height/2-(font12.getHeight(label)/2);
                    }
                    font12.drawString(strPosX, strPosY, label, color);
                    break;
            }
        }
    }
    
    public void loadFonts() {
        font48 = ResourcePool.getFont("Courier", 48); // TODO Check if the font shortcuts are worth-while
        font36 = ResourcePool.getFont("Courier", 36);
        font24 = ResourcePool.getFont("Courier", 24);
        font16 = ResourcePool.getFont("Courier", 16);
        font12 = ResourcePool.getFont("Courier", 12);
    }
    
    public void delete() {
        
    }
    
    public void checkMouse() {
    }
}
