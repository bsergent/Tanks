/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.challengercity.tanks;

import java.awt.Rectangle;
import org.lwjgl.opengl.Display;
/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class ViewPort {

    public static Rectangle rect = new Rectangle(0, 0, Display.getWidth(), Display.getHeight());
    
    public static void updateView() {
        setWidth(Display.getWidth());
        setHeight(Display.getHeight());
    }
    
    public static boolean checkOnScreen(double x, double y, int width, int height) {
        return rect.intersects(x, y, width, height);
    }

    public static void setHeight(int height) {
        ViewPort.rect.height = height;
    }

    public static void setWidth(int width) {
        ViewPort.rect.width = width;
    }

    public static int getX() {
        return rect.x;
    }

    public static int getY() {
        return rect.y;
    }
    
    public static void setX(int x) {
        rect.x = x;
    }

    public static void setY(int y) {
        rect.y = y;
    }
    
    public static void moveX(int x) {
        rect.x = rect.x+x;
    }
    
    public static void moveY(int y) {
        rect.y = rect.y+y;
    }
    
    public static int getViewX(int x) {
        return x-getX();
    }
    
    public static int getViewY(int y) {
        return y-getY();
    }
    
    public static void centerView(int newX, int newY) {
        rect.x = newX-(rect.width/2);
        rect.y = newY-(rect.height/2);
    }
    
}
