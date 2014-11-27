
package com.challengercity.tanks;

/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public enum TileEnum {

    PLAINS(100),
    FOREST(70),
    HILLS(75),
    MOUNTAINS(30),
    DESERT(20),
    OCEAN(0);
    
    private final int buildingCap;
    
    TileEnum(int buildingCap) {
        this.buildingCap = buildingCap;
    }
    
}
