/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.challengercity.tanks;

import com.challengercity.tanks.events.KeyDownEvent;
import java.awt.Font;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import static org.lwjgl.opengl.GL11.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;

/**
 *
 * @author Ben Sergent V <bsergentv@gmail.com>
 */
public class GUITextBox extends GUI {

    private static Texture texture;
    private static TrueTypeFont font;
    private int fontSize = 16;
    protected byte hovered = 0;
    public int actionId;
    private int cursorCoolDown = 20;
    private int charLimit = 20;
    
    public String text = "";
    
    public GUITextBox(int x, int y, int width, int height, String defaultText, int fontSize, int charLimit, int actionId) {
        //super(x, y, width, height, 1, 96, 256, 48);
        super(x, y, width, height, 0, 0, 128, 24);
        this.fontSize = fontSize;
        this.actionId = actionId;
        this.text = defaultText;
        this.charLimit = charLimit;
        TanksMain.registerNewListener(new com.challengercity.tanks.events.Listener() {

            @Override
            public void onKeyDown(KeyDownEvent e) {
                if (GUITextBox.this != null && !e.repeated) {
                    GUITextBox.this.keyDown(e.key);
                }
            }
            
        });
    }
    
    public void draw() {
        if (visible) {
            if (texture == null) {
                texture = ResourcePool.getTexture("gui_button",".png");
            }
            if (font == null) {
                font = new TrueTypeFont(new Font("Courier", Font.PLAIN, fontSize),true);
            }
            texture.bind();

            /* Draw Left Section */
            glBegin(GL_QUADS);
            glTexCoord2f(Renderer.getTextureFloat(picX, texture.getImageWidth()), Renderer.getTextureFloat(picY+(hovered*picHeight), texture.getImageHeight()+1));  // Upper-Left
            glVertex2i(posX, posY);

            glTexCoord2f(Renderer.getTextureFloat(picX+30, texture.getImageWidth()), Renderer.getTextureFloat(picY+(hovered*picHeight), texture.getImageHeight()+1));  // Upper-Right
            glVertex2i(posX+30, posY);

            glTexCoord2f(Renderer.getTextureFloat(picX+30, texture.getImageWidth()), Renderer.getTextureFloat(picY+picHeight+(hovered*picHeight), texture.getImageHeight()+1));  // Lower-Right
            glVertex2i(posX+30, posY+height);

            glTexCoord2f(Renderer.getTextureFloat(picX, texture.getImageWidth()), Renderer.getTextureFloat(picY+picHeight+(hovered*picHeight), texture.getImageHeight()+1));  // Lower-Left
            glVertex2i(posX, posY+height);
            glEnd();
            
            /* Draw Middle Section */
            glBegin(GL_QUADS);
            glTexCoord2f(Renderer.getTextureFloat(picX+30, texture.getImageWidth()), Renderer.getTextureFloat(picY+(hovered*picHeight), texture.getImageHeight()+1));  // Upper-Left
            glVertex2i(posX+30, posY);

            glTexCoord2f(Renderer.getTextureFloat(picX+picWidth-30, texture.getImageWidth()), Renderer.getTextureFloat(picY+(hovered*picHeight), texture.getImageHeight()+1));  // Upper-Right
            glVertex2i(posX+width-30, posY);

            glTexCoord2f(Renderer.getTextureFloat(picX+picWidth-30, texture.getImageWidth()), Renderer.getTextureFloat(picY+picHeight+(hovered*picHeight), texture.getImageHeight()+1));  // Lower-Right
            glVertex2i(posX+width-30, posY+height);

            glTexCoord2f(Renderer.getTextureFloat(picX+30, texture.getImageWidth()), Renderer.getTextureFloat(picY+picHeight+(hovered*picHeight), texture.getImageHeight()+1));  // Lower-Left
            glVertex2i(posX+30, posY+height);
            glEnd();
            
            /* Draw Right Section */
            glBegin(GL_QUADS);
            glTexCoord2f(Renderer.getTextureFloat(picX+picWidth-30, texture.getImageWidth()), Renderer.getTextureFloat(picY+(hovered*picHeight), texture.getImageHeight()+1));  // Upper-Left
            glVertex2i(posX+width-30, posY);

            glTexCoord2f(Renderer.getTextureFloat(picX+picWidth, texture.getImageWidth()), Renderer.getTextureFloat(picY+(hovered*picHeight), texture.getImageHeight()+1));  // Upper-Right
            glVertex2i(posX+width, posY);

            glTexCoord2f(Renderer.getTextureFloat(picX+picWidth, texture.getImageWidth()), Renderer.getTextureFloat(picY+picHeight+(hovered*picHeight), texture.getImageHeight()+1));  // Lower-Right
            glVertex2i(posX+width, posY+height);

            glTexCoord2f(Renderer.getTextureFloat(picX+picWidth-30, texture.getImageWidth()), Renderer.getTextureFloat(picY+picHeight+(hovered*picHeight), texture.getImageHeight()+1));  // Lower-Left
            glVertex2i(posX+width-30, posY+height);
            glEnd();
            
            if (hovered == 1) {
                if (cursorCoolDown <= 0) {
                    //int strPosX = posX+width/2-(font.getWidth(text+"_")/2);
                    //int strPosY = posY+height/2-(font.getHeight(text+"_")/2);
                    font.drawString(posX+5, posY+((height-fontSize)/2), text+"_", new Color(60,223,52));
                    if (cursorCoolDown <= -30) {
                        cursorCoolDown = 30;
                    }
                } else {
                    //int strPosX = posX+width/2-(font.getWidth(text+"  ")/2);
                    //int strPosY = posY+height/2-(font.getHeight(text+"  ")/2);
                    font.drawString(posX+5, posY+((height-fontSize)/2), text+"  ", new Color(60,223,52));
                }
            } else {
                //int strPosX = posX+width/2-(font.getWidth(text)/2);
                //int strPosY = posY+height/2-(font.getHeight(text)/2);
                font.drawString(posX+5, posY+((height-fontSize)/2), text, new Color(60,223,52));
            }
            
        }
    }
    
    @Override
    public void delete() {
        // Do nothing
    }
    
    @Override
    public void checkMouse() {
        if (Mouse.getX()>posX&&Mouse.getX()<(posX+width) && (TanksMain.screenHeight-Mouse.getY())>posY&&(TanksMain.screenHeight-Mouse.getY())<(posY+height) && visible) {
            if (Mouse.isButtonDown(0)) {
                hovered = 1;
            }
        } else {
            if (Mouse.isButtonDown(0)) {
                hovered = 0;
            }
        }
        TanksMain.guiCooldown--;
        cursorCoolDown--;
    }
    
    public void keyDown(int key) {
        if (hovered == 1 && visible) {
            if (key == Keyboard.KEY_BACK) {
                if (text.length() > 0) {
                    text = text.subSequence(0, text.length()-1).toString();
                }
            }
            if (text.length() < charLimit) {
                if (key == Keyboard.KEY_A) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"A";
                    } else {
                        text = text+"a";
                    }
                }
                if (key == Keyboard.KEY_B) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"B";
                    } else {
                        text = text+"b";
                    }
                }
                if (key == Keyboard.KEY_C) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"C";
                    } else {
                        text = text+"c";
                    }

                }
                if (key == Keyboard.KEY_D) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"D";
                    } else {
                        text = text+"d";
                    }

                }
                if (key == Keyboard.KEY_E) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"E";
                    } else {
                        text = text+"e";
                    }

                }
                if (key == Keyboard.KEY_F) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"F";
                    } else {
                        text = text+"f";
                    }

                }
                if (key == Keyboard.KEY_G) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"G";
                    } else {
                        text = text+"g";
                    }

                }
                if (key == Keyboard.KEY_H) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"H";
                    } else {
                        text = text+"h";
                    }

                }
                if (key == Keyboard.KEY_I) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"I";
                    } else {
                        text = text+"i";
                    }

                }
                if (key == Keyboard.KEY_J) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"J";
                    } else {
                        text = text+"j";
                    }

                }
                if (key == Keyboard.KEY_K) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"K";
                    } else {
                        text = text+"k";
                    }

                }
                if (key == Keyboard.KEY_L) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"L";
                    } else {
                        text = text+"l";
                    }

                }
                if (key == Keyboard.KEY_M) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"M";
                    } else {
                        text = text+"m";
                    }

                }
                if (key == Keyboard.KEY_N) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"N";
                    } else {
                        text = text+"n";
                    }

                }
                if (key == Keyboard.KEY_O) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"O";
                    } else {
                        text = text+"o";
                    }

                }
                if (key == Keyboard.KEY_P) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"P";
                    } else {
                        text = text+"p";
                    }

                }
                if (key == Keyboard.KEY_Q) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"Q";
                    } else {
                        text = text+"q";
                    }

                }
                if (key == Keyboard.KEY_R) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"R";
                    } else {
                        text = text+"r";
                    }

                }
                if (key == Keyboard.KEY_S) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"S";
                    } else {
                        text = text+"s";
                    }

                }
                if (key == Keyboard.KEY_T) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"T";
                    } else {
                        text = text+"t";
                    }

                }
                if (key == Keyboard.KEY_U) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"U";
                    } else {
                        text = text+"u";
                    }

                }
                if (key == Keyboard.KEY_V) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"V";
                    } else {
                        text = text+"v";
                    }

                }
                if (key == Keyboard.KEY_W) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"W";
                    } else {
                        text = text+"w";
                    }

                }
                if (key == Keyboard.KEY_X) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"X";
                    } else {
                        text = text+"x";
                    }

                }
                if (key == Keyboard.KEY_Y) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"Y";
                    } else {
                        text = text+"y";
                    }

                }
                if (key == Keyboard.KEY_Z) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"Z";
                    } else {
                        text = text+"z";
                    }

                }
                if (key == Keyboard.KEY_0) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+")";
                    } else {
                        text = text+"0";
                    }

                }
                if (key == Keyboard.KEY_1) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"!";
                    } else {
                        text = text+"1";
                    }

                }
                if (key == Keyboard.KEY_2) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"@";
                    } else {
                        text = text+"2";
                    }

                }
                if (key == Keyboard.KEY_3) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"#";
                    } else {
                        text = text+"3";
                    }

                }
                if (key == Keyboard.KEY_4) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"$";
                    } else {
                        text = text+"4";
                    }

                }
                if (key == Keyboard.KEY_5) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"%";
                    } else {
                        text = text+"5";
                    }

                }
                if (key == Keyboard.KEY_6) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"^";
                    } else {
                        text = text+"6";
                    }

                }
                if (key == Keyboard.KEY_7) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"&";
                    } else {
                        text = text+"7";
                    }

                }
                if (key == Keyboard.KEY_8) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"*";
                    } else {
                        text = text+"8";
                    }

                }
                if (key == Keyboard.KEY_9) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"(";
                    } else {
                        text = text+"9";
                    }

                }
                if (key == Keyboard.KEY_PERIOD || key == Keyboard.KEY_DECIMAL) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+">";
                    } else {
                        text = text+".";
                    }

                }
                if (key == Keyboard.KEY_COMMA) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"<";
                    } else {
                        text = text+",";
                    }

                }
                if (key == 0 || key == Keyboard.KEY_APOSTROPHE) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"\"";
                    } else {
                        text = text+"'";
                    }

                }
                if (key == Keyboard.KEY_SEMICOLON) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+":";
                    } else {
                        text = text+";";
                    }

                }
                if (key == Keyboard.KEY_SPACE) {
                    text = text+" ";

                }
                if (key == Keyboard.KEY_SLASH) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"?";
                    } else {
                        text = text+"/";
                    }

                }
                if (key == Keyboard.KEY_MINUS) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"_";
                    } else {
                        text = text+"-";
                    }

                }
                if (key == Keyboard.KEY_EQUALS) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"+";
                    } else {
                        text = text+"=";
                    }

                }
                if (key == Keyboard.KEY_LBRACKET) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"{";
                    } else {
                        text = text+"[";
                    }

                }
                if (key == Keyboard.KEY_RBRACKET) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"}";
                    } else {
                        text = text+"]";
                    }

                }
                if (key == Keyboard.KEY_BACKSLASH) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        text = text+"|";
                    } else {
                        text = text+"\\";
                    }

                }
            }
            TanksMain.currentScreen.actionPerformed(actionId);
        }
    }
    
}
