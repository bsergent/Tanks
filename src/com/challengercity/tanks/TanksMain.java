
package com.challengercity.tanks;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.glViewport;

/**
 *
 * @author Ben Sergent V <bsergentv@gmail.com>
 */
public class TanksMain {

    private Renderer renderer;
    private static Controller control;
    private static final String gameName = "Tanks";
    private static final String version = "0.0.2 Alpha";
    private static String build;
    final static ResourceBundle rb = ResourceBundle.getBundle("version"); 
    private static String username = "";
    private static String sessionId = "";
    public static long lastPing = 0;
    public static Screen currentScreen;
    public static boolean fullscreen = false;
    public static int screenWidth = 1280; // 1280
    public static int screenHeight = 720; // 720
    private static long lastFrame;
    private static long lastFPS;
    private static int fps;
    public static int currentFPS;
    public static long lastDelta;
    public static int guiCooldown = 0;
    private static TanksMain ug;
    public static final boolean DEBUG = true;
    
    public static Preferences prefsNode = Preferences.userNodeForPackage(TanksMain.class);
    public static final String PREF_FULLSCREEN = "Fullscreen";
    public static final String PREF_USERNAME = "Username";
    public static final String PREF_SKINADDRESS = "SkinAddress";
    
    public TanksMain() {
        if (DEBUG) username = "Debugger";
        run();
    }
    
    public static TanksMain getGameInstance() {
        return ug;
    }
    
    public static String getVersion() {
        return version;
    }
    
    public static String getBuild() {
        return build;
    }
    
    public static String getGameName() {
        return gameName;
    }
    
    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        TanksMain.username = username;
    }

    public static String getSessionId() {
        return sessionId;
    }

    public static void setSessionId(String sessionId) {
        TanksMain.sessionId = sessionId;
    }
    
    public static void main(String[] args) {
        TanksMain.build = getRbTok("BUILD");
        ug = new TanksMain();
    }
    
    public static String getRbTok(String propToken) { 
        String msg = ""; 
        try { 
            msg = rb.getString(propToken); 
        } catch (MissingResourceException e) { 
            System.err.println("Token ".concat(propToken).concat(" not in Propertyfile!")); 
        } 
        return msg; 
    }
    
    public static void log(Class cls, String str) {
        System.out.println("["+cls.getSimpleName()+"] "+str);
    }
    
    public static void setScreen(Screen scr) {
        Renderer.removeFromRenderList(currentScreen);
        currentScreen = scr;
    }
    
    public long getTime() {
        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    }
    
    public int getDelta() {
        long time = getTime();
        int delta = (int) (time - lastFrame);
        lastFrame = time;

        if (delta > (1000/60)*3) {
            delta = (1000/60)*3;
        }
        if (delta < (1000/60)) {
            delta = 1000/60;
        }
        lastDelta = delta;
        return delta;
    }
    
    public void updateFPS() {
        if (getTime() - lastFPS > 1000) { 
            currentFPS=fps;
            fps = 0; //reset the FPS counter
            lastFPS += 1000; //add one second
        }
        fps++;
    }
    
    public static void setDisplayMode(int width, int height, boolean fullscreen) {

        // return if requested DisplayMode is already set
        if ((Display.getDisplayMode().getWidth() == width) && 
            (Display.getDisplayMode().getHeight() == height) && 
            (Display.isFullscreen() == fullscreen)) {
                return;
        }

        try {
            DisplayMode targetDisplayMode = null;

            if (fullscreen) {
                DisplayMode[] modes = Display.getAvailableDisplayModes();
                int freq = 0;

                for (int i=0;i<modes.length;i++) {
                    DisplayMode current = modes[i];

                    if ((current.getWidth() == width) && (current.getHeight() == height)) {
                        if ((targetDisplayMode == null) || (current.getFrequency() >= freq)) {
                            if ((targetDisplayMode == null) || (current.getBitsPerPixel() > targetDisplayMode.getBitsPerPixel())) {
                                targetDisplayMode = current;
                                freq = targetDisplayMode.getFrequency();
                            }
                        }

                        // if we've found a match for bpp and frequence against the 
                        // original display mode then it's probably best to go for this one
                        // since it's most likely compatible with the monitor
                        if ((current.getBitsPerPixel() == Display.getDesktopDisplayMode().getBitsPerPixel()) &&
                            (current.getFrequency() == Display.getDesktopDisplayMode().getFrequency())) {
                                targetDisplayMode = current;
                                break;
                        }
                    }
                }
            } else {
                targetDisplayMode = new DisplayMode(width,height);
            }

            if (targetDisplayMode == null) {
                System.out.println("Failed to find value mode: "+width+"x"+height+" fs="+fullscreen);
                return;
            }

            Display.setDisplayMode(targetDisplayMode);
            Display.setFullscreen(fullscreen);

        } catch (LWJGLException e) {
            System.out.println("Unable to setup mode "+width+"x"+height+" fullscreen="+fullscreen + e);
        }
    }
    
    private void run() {
        TanksMain.log(getClass(), "Initialized - v"+version);
        
        try {
            setDisplayMode(screenWidth, screenHeight, prefsNode.getBoolean(PREF_FULLSCREEN, false));
            Display.setTitle(gameName+" - v"+version);
            Display.create();
            glViewport(0, 0, Display.getWidth(), Display.getHeight());
            screenHeight=Display.getHeight();
            screenWidth=Display.getWidth();
            ViewPort.updateView();
        } catch(Exception ex) {
            TanksMain.log(getClass(), "Could not setup display");
            System.exit(1);
        }
        
        ResourcePool.loadFrequentResources();
        
        renderer = new Renderer(this);
        currentScreen = new ScreenMenu();
        control = new Controller(); // Listen for input
        
        lastFPS = getTime();
        getDelta();
        
        while(!Display.isCloseRequested()) { // Game Loop
            Controller.checkInput();
            currentScreen.tick(getDelta());
            currentScreen.mouseUpdate();
            updateFPS();
            renderer.render();
            Display.sync(60);
        }
        
        Display.destroy();
        System.exit(0);
    }

}
