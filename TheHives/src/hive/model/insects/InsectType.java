/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.insects;

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
    // x1 (Cyan)
    
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
}
