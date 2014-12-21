
package com.challengercity.tanks;

/**
 *
 * @author Ben Sergent V <bsergentv@gmail.com>
 */
public class ScreenGame extends Screen {

    private World currentWorld = new World();
    
    public ScreenGame() {
        super();
        startup();
    }
    
    private void startup() { // TODO Add logout button
        GUI b;
        b = new GUIButton(TanksMain.screenWidth-100, 0, 100, 40, 1, StringHandler.getString("menu_game_leave"), 24);
        addToRenderList(b);
        currentWorld.addEntity(new TankPlayer(100, 100, TanksMain.getUsername(), new org.newdawn.slick.Color(0.1176f, 0.2157f, 0.4627f), TankModel.BASIC, true));
        currentWorld.addEntity(new Bullet(200, 100));
//        b = new GUIImage(Hedgehog.screenWidth/2-256, 20, 512, 147, 0, 0, 512, 147, "gui_logo");
//        addToRenderList(b);
//        b = new GUIText(Hedgehog.screenWidth/2-2, 20+96, "v"+Hedgehog.getVersion()+" - Build "+Hedgehog.getBuild(), 12, false);
//        addToRenderList(b);
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
        super.tick(delta); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void render() {
        super.render(); //To change body of generated methods, choose Tools | Templates.
        if (currentWorld != null) {
            currentWorld.draw();
        }
    }

}
