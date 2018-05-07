/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.players;

import hive.model.insects.InsectType;

/**
 *
 * @author Thomas
 */
public class PlayerCollections
{
    public static PlayerCollection getDefault()
    {
        PlayerCollection collection = new PlayerCollection();
        
        collection.put(InsectType.QUEEN_BEE, 1);
        collection.put(InsectType.SPIDER, 2);
        collection.put(InsectType.BEETLE, 2);
        collection.put(InsectType.GRASSHOPPER, 3);
        collection.put(InsectType.SOLDIER_ANT, 3);
        collection.put(InsectType.MOSQUITO, 1);
        collection.put(InsectType.LADYBUG, 1);
        collection.put(InsectType.PILL_BUG, 1);
        
        return collection;
    }
}
