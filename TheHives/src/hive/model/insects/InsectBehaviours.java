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
public class InsectBehaviours
{
    static InsectBehaviour get(InsectType type)
    {
        switch(type)
        {
        case QUEEN_BEE:
            return null;
        case SPIDER:
            return null;
        case BEETLE:
            return null;
        case GRASSHOPPER:
            return null;
        case SOLDIER_ANT:
            return null;

        case MOSQUITO:
            return null;
        case LADYBUG:
            return null;
        case PILL_BUG:
            return null;
        }
        return null;
    }
}
