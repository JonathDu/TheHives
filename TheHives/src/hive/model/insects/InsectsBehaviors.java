/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.insects;

import hive.model.insects.behaviors.BeetleBehavior;
import hive.model.insects.behaviors.GrasshopperBehavior;
import hive.model.insects.behaviors.LadybugBehavior;
import hive.model.insects.behaviors.MosquitoBehavior;
import hive.model.insects.behaviors.PillBugBehavior;
import hive.model.insects.behaviors.QueenBeeBehavior;
import hive.model.insects.behaviors.SoldierAntBehavior;
import hive.model.insects.behaviors.SpiderBehavior;
import java.util.ArrayList;
import java.util.EnumMap;

/**
 *
 * @author Thomas
 */
public class InsectsBehaviors extends EnumMap<InsectType, InsectBehavior>
{
    public static final EnumMap<InsectType, InsectBehavior> behaviors;
    
    public InsectsBehaviors(ArrayList<InsectType> insects)
    {
        super(InsectType.class);
        for(InsectType type : insects)
        {
            put(type, behaviors.get(type));
        }
    }
    
    static
    {
        behaviors = new EnumMap<>(InsectType.class);
        
        behaviors.put(InsectType.QUEEN_BEE, new QueenBeeBehavior());
        behaviors.put(InsectType.SPIDER, new SpiderBehavior());
        behaviors.put(InsectType.BEETLE, new BeetleBehavior());
        behaviors.put(InsectType.GRASSHOPPER, new GrasshopperBehavior());
        behaviors.put(InsectType.SOLDIER_ANT, new SoldierAntBehavior());
        behaviors.put(InsectType.MOSQUITO, new MosquitoBehavior());
        behaviors.put(InsectType.LADYBUG, new LadybugBehavior());
        behaviors.put(InsectType.PILL_BUG, new PillBugBehavior());
    }
    
}
