
package com.challengercity.tanks;

/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class ScreenOptions extends Screen {

    public ScreenOptions() {
        startup();
    }
    
    private void startup() {
        GUI b;
        b = new GUIImage(TanksMain.screenWidth/2-256, 20, 512, 147, 0, 0, 512, 147, "gui_logo");
        addToRenderList(b);
        b = new GUIText(TanksMain.screenWidth/2-2, 20+96, "v"+TanksMain.getVersion()+" - Build "+TanksMain.getBuild(), 12, false);
        addToRenderList(b);
        b = new GUIText(TanksMain.screenWidth/2, 170, "Options", 24, true);
        addToRenderList(b);
        b = new GUIButton(TanksMain.screenWidth/2-100, 200, 200, 40, 0, StringHandler.getString("menu_options_fullscreen"), 24);
        addToRenderList(b);
        b = new GUIButton(TanksMain.screenWidth/2-50, TanksMain.screenHeight-60, 100, 40, 99, StringHandler.getString("menu_main_back"), 24);
        addToRenderList(b);
    }
    
    @Override
    public void actionPerformed(int actionId) {
        if (actionId==0) {
            if (!TanksMain.fullscreen) {
                try {
                    TanksMain.setDisplayMode(TanksMain.screenWidth, TanksMain.screenHeight, true); // 1280, 720  |  2560, 1440 | 640, 480
                    TanksMain.fullscreen=true;
                    TanksMain.prefsNode.putBoolean(TanksMain.PREF_FULLSCREEN, true);
                } catch (Exception ex) {
                    TanksMain.log(ScreenOptions.class,"Could not exit fullscreen");
                }
            } else {
                try {
                    TanksMain.setDisplayMode(TanksMain.screenWidth, TanksMain.screenHeight, false); // 1280, 720  |  2560, 1440 | 640, 480
                    TanksMain.fullscreen=false;
                    TanksMain.prefsNode.putBoolean(TanksMain.PREF_FULLSCREEN, false);
                } catch (Exception ex) {
                    TanksMain.log(ScreenOptions.class,"Could not enter fullscreen");
                }
            }
            ViewPort.updateView();
        }
        if (actionId==99) {
            TanksMain.setScreen(new ScreenMenu());
        }
    }

}
