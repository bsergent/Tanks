
package com.challengercity.tanks;

import java.awt.Desktop;
import java.net.URL;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.lwjgl.input.Keyboard;

/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class ScreenLogin extends Screen {

    private GUIText statusLabel;
    private GUITextBox userBox;
    private GUIPassBox passBox;
    
    public ScreenLogin() {
        startup();
    }
    
    private void startup() {
        GUI b;
        b = new GUIImage(TanksMain.screenWidth/2-256, 20, 512, 147, 0, 0, 512, 147, "gui_logo");
        addToRenderList(b);
        b = new GUIText(TanksMain.screenWidth/2-2, 20+96, "v"+TanksMain.getVersion()+" - Build "+TanksMain.getBuild(), 12, false);
        addToRenderList(b);
        b = new GUIText(TanksMain.screenWidth/2, 170, "Login", 24, true);
        addToRenderList(b);
        
        statusLabel = new GUIText(TanksMain.screenWidth/2, 220, StringHandler.getString("menu_login_enterInfo"), 16, true);
        addToRenderList(statusLabel);
        userBox = new GUITextBox(TanksMain.screenWidth/2-150, 240, 310, 30, TanksMain.prefsNode.get(TanksMain.PREF_USERNAME, ""), 24, 20, 2);
        addToRenderList(userBox);
        passBox = new GUIPassBox(TanksMain.screenWidth/2-150, 280, 310, 30, "", 16, 20, 2);
        addToRenderList(passBox);
        
        b = new GUIButton(TanksMain.screenWidth/2-155, TanksMain.screenHeight-120, 200, 40, 1, StringHandler.getString("menu_login_create"), 24);
        addToRenderList(b);
        b = new GUIButton(TanksMain.screenWidth/2+55, TanksMain.screenHeight-120, 100, 40, 0, StringHandler.getString("menu_login_submit"), 24);
        addToRenderList(b);
        b = new GUIButton(TanksMain.screenWidth/2-50, TanksMain.screenHeight-60, 100, 40, 99, StringHandler.getString("menu_main_back"), 24);
        addToRenderList(b);
        
        if (!userBox.text.isEmpty()) {
            passBox.hovered = 1;
        } else {
            userBox.hovered = 0;
        }
        
        Controller.addListenerKeyboard(new ListenerKeyboard(false) {

            @Override
            public void keyDown(int key) {
                if (key == Keyboard.KEY_TAB) {
                    if ((Controller.isKeyDown(Keyboard.KEY_LSHIFT) || Controller.isKeyDown(Keyboard.KEY_RSHIFT)) && passBox.hovered == 1) {
                        userBox.hovered = 1;
                        passBox.hovered = 0;
                    } else if (!(Controller.isKeyDown(Keyboard.KEY_LSHIFT) || Controller.isKeyDown(Keyboard.KEY_RSHIFT)) && userBox.hovered == 1) {
                        userBox.hovered = 0;
                        passBox.hovered = 1;
                    }
                } else if (key == Keyboard.KEY_RETURN) {
                    if (userBox.hovered == 1) {
                        userBox.hovered = 0;
                        passBox.hovered = 1;
                    } else if (passBox.hovered == 1) {
                        actionPerformed(0);
                    }
                }
            }
            
        });
    }
    
    @Override
    public void actionPerformed(int actionId) {
        if (actionId==0) {
            try {
                String result = Request.Post("http://account.challengercity.com/Login.php")
                        .socketTimeout(1000)
                        .bodyForm(Form.form().add("username", userBox.text).add("email", userBox.text).add("password",passBox.text).add("product", "hedgehog").build())
                        .execute()
                        .returnContent()
                        .asString();
                if (result.charAt(0) == '1') {
                    TanksMain.setUsername(userBox.text);
                    TanksMain.setSessionId(result.substring(result.indexOf(031, 2)));
                    TanksMain.prefsNode.put(TanksMain.PREF_USERNAME, userBox.text);
                    TanksMain.setScreen(new ScreenMenu());
                    TanksMain.log(ScreenLogin.class, TanksMain.getSessionId());
                } else {
                    statusLabel.setLabel("Incorrect username-password combination");
                    passBox.text = "";
                }
            } catch (Exception ex) {
                statusLabel.setLabel("Error connecting to login server");
                TanksMain.log(ScreenLogin.class, ex.getLocalizedMessage());
            }
        }
        if (actionId==1) {
            try {
                Desktop.getDesktop().browse(new URL("http://account.challengercity.com/RegisterStandalone.php").toURI());
                statusLabel.setLabel("The account creation page has been opened in your default browser");
            } catch (Exception e) {
                statusLabel.setLabel("Go to http://account.challengercity.com/RegisterStandalone.php");
            }
        }
        if (actionId==99) {
            TanksMain.setScreen(new ScreenMenu());
        }
    }

}

