
package com.challengercity.tanks;

import java.util.ArrayList;
import java.util.HashMap;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class Controller {

    private static int cooldown = 0;
    private static boolean[] keyStates = new boolean[256];
    private static boolean[] mouseStates = new boolean[3];
    private static HashMap<String, Integer> controls = new HashMap();
    //private static boolean[] keySpecialStates = new boolean[246];
    private static ArrayList<ListenerKeyboard> keyListeners = new ArrayList<>();
    private static ArrayList<ListenerMouse> mouseListeners = new ArrayList<>();
    
    public Controller() {
        try {
            Mouse.create();
            Keyboard.create();
        } catch (org.lwjgl.LWJGLException ex) {
           TanksMain.log(Controller.class, "Keyboard could not be initialized");
        }
        TanksMain.log(Controller.class, "Initialized");
    }
    
    public static void checkInput() {
        for (int i = 0; i < keyStates.length; i++) { // TODO Allow backspace key to be held down
            if (Keyboard.isKeyDown(i)) { // If down now
                if (!keyStates[i]) { // But was up
                    for (ListenerKeyboard lk : keyListeners) {
                        if (!lk.isContinuous()) {
                            lk.keyDown(i);
                        }
                    }
                } else {
                    for (ListenerKeyboard lk : keyListeners) {
                        if (lk.isContinuous()) {
                            lk.keyDown(i);
                        }
                    }
                }
                keyStates[i] = true;
            } else { // If up now
                if (keyStates[i]) { // But was down
                    for (ListenerKeyboard lk : keyListeners) {
                        lk.keyUp(i);
                    }
                } else {
                    for (ListenerKeyboard lk : keyListeners) {
                        if (lk.isContinuous()) {
                            lk.keyUp(i);
                        }
                    }
                }
                keyStates[i] = false;
            }
        }
        for (int i = 0; i < mouseStates.length; i++) {
            if (Mouse.isButtonDown(i)) { // If down now
                if (!mouseStates[i]) { // But was up
                    for (ListenerMouse lm : mouseListeners) {
                        lm.buttonDown(i, Mouse.getX(), Display.getHeight()-Mouse.getY());
                    }
                }
                mouseStates[i] = true;
            } else { // If up now
                if (mouseStates[i]) { // But was down
                    for (ListenerMouse lm : mouseListeners) {
                        lm.buttonUp(i, Mouse.getX(), Display.getHeight()-Mouse.getY());
                    }
                }
                mouseStates[i] = false;
            }
        }
    }
    
    public static boolean isKeyDown(int key) {
        return keyStates[key];
    }
    
    public static int getControl(String name) {
        if (controls.containsKey(name)) {
            return controls.get(name);
        }
        return 0;
    }
    
    public static void setControl(String name, int value) {
        if (controls.containsKey(name)) {
            controls.replace(name, value);
        } else {
            controls.put(name, value);
        }
    }
    
    public static void addListenerKeyboard(ListenerKeyboard lst) {
        keyListeners.add(lst);
    }
    
    public static void addListenerMouse(ListenerMouse lst) {
        mouseListeners.add(lst);
    }
    
}
