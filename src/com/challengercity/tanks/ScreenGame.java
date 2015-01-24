
package com.challengercity.tanks;

import static com.challengercity.tanks.GUIText.font24;
import org.newdawn.slick.TrueTypeFont;

/**
 *
 * @author Ben Sergent V <bsergentv@gmail.com>
 */
public class ScreenGame extends Screen {

    private World currentWorld = new World();
    private TrueTypeFont debugFont;
    
    public ScreenGame() {
        super();
        startup();
    }
    
    private void startup() { // TODO Add logout button
        GUI b;
        b = new GUIButton(TanksMain.screenWidth-100, 0, 100, 40, 1, StringHandler.getString("menu_game_leave"), 24);
        addToRenderList(b);
        currentWorld.addEntity(new TankPlayer(100, 100, TanksMain.getUsername(), new org.newdawn.slick.Color(0.1176f, 0.2157f, 0.4627f), TankModel.BASIC, true));
        Tank dummyTank = new TankDummy(TanksMain.screenWidth-100, TanksMain.screenHeight-100, new org.newdawn.slick.Color(0.5922f, 0.0784f, 0.0784f), TankModel.BASIC);
        dummyTank.setTargetRotation(180);
        dummyTank.setTurretTargetRotation(180);
        dummyTank.setRotation(180);
        dummyTank.setTurretRotation(180);
        currentWorld.addEntity(dummyTank);
    }

    public World getCurrentWorld() {
        return currentWorld;
    }
    
    @Override
    public void actionPerformed(int actionId) {
        if (actionId == 1) {
            TanksMain.setScreen(new ScreenMenu());
        }
    }

    @Override
    public void tick(long delta) {
        if (currentWorld != null) {
            currentWorld.tick(delta);
        }
        super.tick(delta);
    }

    @Override
    public void render() {
        super.render();
        if (debugFont == null) {
            debugFont = ResourcePool.getFont("Courier", 12);
        }
        debugFont.drawString(5, 5, "FPS: "+TanksMain.currentFPS, new org.newdawn.slick.Color(0, 0, 0, 255));
        debugFont.drawString(5, 17, "Score: "+TanksMain.score, new org.newdawn.slick.Color(0, 0, 0, 255));
        
        if (currentWorld != null) {
            currentWorld.draw();
        }
    }

}
