
package com.challengercity.tanks;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class StringHandler {

    private static File source = new File("strings.txt");
    private static HashMap<String,String> strings;
    
    public static String getString(String key) {
        if (strings == null) {
            strings = new HashMap<String,String>();
            try{
                InputStream in = StringHandler.class.getResourceAsStream("resources/strings.txt");
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                String line;
                while ((line = br.readLine()) != null)   {
                    if (!line.equals("") && !line.startsWith("//")) {
                        String[] lineDiv = line.split("=");
                        strings.put(lineDiv[0], lineDiv[1]);
                    }
                }
                br.close();
            } catch (Exception ex) {
                return "Error: String not found";
            }
        }
        if (strings.get(key) == null) {
            return "";
        }
        return strings.get(key);
    }
    
}
