
package com.challengercity.tanks;

import org.lwjgl.opengl.Display;

/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class ScreenMenu extends Screen {

    public ScreenMenu() {
        super();
        startup();
    }
    
    private void startup() { // TODO Add logout button
        GUI b;
        b = new GUIImage(TanksMain.screenWidth/2-256, 20, 512, 147, 0, 0, 512, 147, "gui_logo");
        addToRenderList(b);
        b = new GUIText(TanksMain.screenWidth/2-2, 20+96, "v"+TanksMain.getVersion()+" - Build "+TanksMain.getBuild(), 12, false);
        addToRenderList(b);
        b = new GUIText(5, 5, TanksMain.getUsername().equals("")?"Not logged in":"Logged in as "+TanksMain.getUsername(), 12, false);
        addToRenderList(b);
        b = new GUIText(TanksMain.screenWidth/2, 170, "Main Menu", 24, true);
        addToRenderList(b);
        if (!TanksMain.getUsername().equals("")) {
            b = new GUIButton(TanksMain.screenWidth/2-95, 200, 190, 40, 0, StringHandler.getString("menu_main_multiplayer"), 24);
            addToRenderList(b);
        } else {
            b = new GUIButton(TanksMain.screenWidth/2-95, 200, 190, 40, 2, StringHandler.getString("menu_main_login"), 24);
            addToRenderList(b);
        }
        b = new GUIButton(TanksMain.screenWidth/2-75, 250, 150, 40, 1, StringHandler.getString("menu_main_options"), 24);
        addToRenderList(b);
//        b = new GUIButton(Underground.screenWidth/2-125, 300, 250, 40, 2, StringHandler.getString("menu_main_options"), 24);
//        addToRenderList(b);
//        b = new GUIButton(Underground.screenWidth/2-125, 350, 250, 40, 5, "Change-Log", 24);
//        addToRenderList(b);
//        b = new GUIButton(Underground.screenWidth/2-125, 400, 250, 40, 3, "Credits", 24);
//        addToRenderList(b);
        b = new GUIButton(TanksMain.screenWidth/2-50, TanksMain.screenHeight-60, 100, 40, 99, StringHandler.getString("menu_main_exit"), 24);
        addToRenderList(b);
    }
    
    @Override
    public void actionPerformed(int actionId) {
        if (actionId==0) {
            TanksMain.setScreen(new ScreenGame());
        }
        if (actionId==1) {
            TanksMain.setScreen(new ScreenOptions());
        }
        if (actionId==2) {
            TanksMain.setScreen(new ScreenLogin());
        }
        if (actionId==99) {
            Display.destroy();
            System.exit(0);
        }
    }

}