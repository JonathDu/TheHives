/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model;

import hive.model.board.Board;
import hive.model.board.TilesStack;
import hive.model.insects.InsectType;
import hive.model.insects.InsectsBehaviors;
import hive.model.insects.behaviors.BeetleBehavior;
import hive.model.insects.behaviors.GrasshopperBehavior;
import hive.model.insects.behaviors.QueenBeeBehavior;
import hive.model.insects.behaviors.SoldierAntBehavior;
import hive.model.insects.behaviors.SpiderBehavior;
import util.Matrix;

/**
 *
 * @author Thomas
 */
public class DefaultGame
{
    public static InsectsBehaviors getInsectBehaviors(InsectType type)
    {
        InsectsBehaviors behaviors = new InsectsBehaviors();
        
        behaviors.put(InsectType.QUEEN_BEE, new QueenBeeBehavior());
        behaviors.put(InsectType.QUEEN_BEE, new SpiderBehavior());
        behaviors.put(InsectType.QUEEN_BEE, new BeetleBehavior());
        behaviors.put(InsectType.QUEEN_BEE, new GrasshopperBehavior());
        behaviors.put(InsectType.QUEEN_BEE, new SoldierAntBehavior());
        
        return behaviors;
    }
    
    public static Board getBoard()
    {
        Matrix<TilesStack> m = new Matrix<>(14, 14);
        m.setAll(() -> new TilesStack());
        return new Board(m);
    }
}
