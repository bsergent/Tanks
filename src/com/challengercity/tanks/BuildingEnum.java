
package com.challengercity.tanks;

/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public enum BuildingEnum {
    
    BARRACKS,
    TOWER,
    WALL,
    LUMBERCAMP,
    MININGCAMP,
    DOCK,
    CASTLE,
    BLACKSMITH,
    HOUSE,
    MARKET,
    LIBRARY,
    FARM,
    STABLE, 
    ARCHERYRANGE,
    REFINERY,
    LUMBERMILL;
    
    private final String name;
    private final String desc;
    private final int cost;
    
    BuildingEnum() {
        name = "";
        desc = "";
        cost = 0;
    }
    
}
