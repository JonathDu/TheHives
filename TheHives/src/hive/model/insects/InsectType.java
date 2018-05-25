/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.insects;

import java.util.ArrayList;

/**
 *
 * @author Thomas
 */
public enum InsectType
{
    QUEEN_BEE,
    // x1(Yellow-Gold)
    SPIDER,
    // x2 (Brown)
    BEETLE,
    // x2 (Purple)
    GRASSHOPPER,
    // x3 (Green)
    SOLDIER_ANT,
    // x3 (Blue)

    MOSQUITO,
    // x1 (Gray)
    LADYBUG,
    // x1 (Red)
    PILL_BUG;
    // x1 (Cyan)// x1 (Cyan)// x1 (Cyan)// x1 (Cyan)
    
    public static final ArrayList<InsectType> implemented_insects;
    public static final ArrayList<InsectType> default_insects;
    public static final ArrayList<InsectType> extension_insects;
    
    static
    {
        implemented_insects = new ArrayList<>(5);
        implemented_insects.add(QUEEN_BEE);
        implemented_insects.add(SPIDER);
        implemented_insects.add(BEETLE);
        implemented_insects.add(GRASSHOPPER);
        implemented_insects.add(SOLDIER_ANT);
        
        default_insects = new ArrayList<>(5);
        default_insects.add(QUEEN_BEE);
        default_insects.add(SPIDER);
        default_insects.add(BEETLE);
        default_insects.add(GRASSHOPPER);
        default_insects.add(SOLDIER_ANT);
        
        extension_insects = new ArrayList<>(8);
        extension_insects.add(QUEEN_BEE);
        extension_insects.add(SPIDER);
        extension_insects.add(BEETLE);
        extension_insects.add(GRASSHOPPER);
        extension_insects.add(SOLDIER_ANT);
        extension_insects.add(MOSQUITO);
        extension_insects.add(LADYBUG);
        extension_insects.add(PILL_BUG);
    }
    
    @Override
    public String toString()
    {
        switch(this)
        {
        case QUEEN_BEE:
            return "Q";
        case SPIDER:
            return "S";
        case BEETLE:
            return "B";
        case GRASSHOPPER:
            return "G";
        case SOLDIER_ANT:
            return "A";
        case MOSQUITO:
            return "M";
        case LADYBUG:
            return "L";
        case PILL_BUG:
            return "P";
        }
        assert false;
        return "";
    }
    
    public static InsectType toInsectType(String insect)
    {
        switch(insect)
        {
            case "Q":
                return QUEEN_BEE;
            case "S":
                return SPIDER;
            case "B":
                return BEETLE;
            case "G":
                return GRASSHOPPER;
            case "A":
                return SOLDIER_ANT;
            case "M":
                return MOSQUITO;
            case "L":
                return LADYBUG;
            case "P":
                return PILL_BUG;
        }
        return null;
    }
}
