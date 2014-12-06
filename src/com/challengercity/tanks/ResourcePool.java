
package com.challengercity.tanks;

import java.awt.Font;
import java.util.HashMap;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class ResourcePool {

    private static HashMap<String,Texture> loadedTextures = new HashMap<String,Texture>();
    private static HashMap<String,TrueTypeFont> loadedFonts = new HashMap<String,TrueTypeFont>();
    
    public static Texture getTexture(String imageName, String fileExt) { // Tiles.png
        if (!loadedTextures.containsKey(imageName+fileExt)) {
            try {
                loadedTextures.put(imageName+fileExt, TextureLoader.getTexture(fileExt, ClassLoader.getSystemClassLoader().getResourceAsStream("com/challengercity/tanks/resources/"+imageName+(fileExt.toLowerCase()))));
            } catch (Exception ex) {
                TanksMain.log(ResourcePool.class, "Could not load texture - "+imageName+fileExt);
                Display.destroy();
                System.exit(1);
            }
        }
        return loadedTextures.get(imageName+fileExt);
    }
    
    public static TrueTypeFont getFont(String fontName, int fontSize) { // Courier_12.ttf
        if (!loadedFonts.containsKey(fontName+"_"+fontSize+".ttf")) {
            try {
                loadedFonts.put(fontName+"_"+fontSize+".ttf", new TrueTypeFont(new Font(fontName, Font.PLAIN, fontSize),true));
            } catch (Exception ex) {
                TanksMain.log(ResourcePool.class, "Could not load font - "+fontName+".ttf");
                Display.destroy();
                System.exit(1);
            }
        }
        return loadedFonts.get(fontName+"_"+fontSize+".ttf");
    }
    
    public static void loadFrequentResources() {
        if (!loadedTextures.containsKey("gui_button.png")) {
            try {
                loadedTextures.put("gui_button.png", TextureLoader.getTexture(".png", ClassLoader.getSystemClassLoader().getResourceAsStream("com/challengercity/tanks/resources/gui_button.png")));
            } catch (Exception ex) {
                TanksMain.log(ResourcePool.class, "Could not load texture - gui_button.png");
            }
        }
        if (!loadedFonts.containsKey("Courier_14.ttf")) {
            try {
                loadedFonts.put("Courier_14.ttf", new TrueTypeFont(new Font("Courier", Font.PLAIN, 14),true));
            } catch (Exception ex) {
                TanksMain.log(ResourcePool.class, "Could not load font - Courier_14.ttf");
            }
        }
    }
}
