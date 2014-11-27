
package com.challengercity.tanks;

/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class GUIPassBox extends GUITextBox {

    public GUIPassBox(int x, int y, int width, int height, String defaultText, int fontSize, int charLimit, int actionId) {
        super(x, y, width, height, defaultText, fontSize, charLimit, actionId);
    }

    @Override
    public void draw() {
        String realText = text;
        text = "";
        for (int i = 0; i < realText.length(); i++) {
            text = text+"*";
        }
        super.draw();
        text = realText;
    }

}
