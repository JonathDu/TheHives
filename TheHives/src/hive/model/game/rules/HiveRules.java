/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.game.rules;

import hive.model.board.Cell;
import hive.model.board.Cells;
import hive.model.board.Cell;
import hive.model.game.GameState;
import hive.model.insects.InsectType;
import hive.model.insects.InsectsBehaviors;
import hive.model.insects.behaviors.BeetleBehavior;
import hive.model.insects.behaviors.GrasshopperBehavior;
import hive.model.insects.behaviors.LadybugBehavior;
import hive.model.insects.behaviors.MosquitoBehavior;
import hive.model.insects.behaviors.PillBugBehavior;
import hive.model.insects.behaviors.QueenBeeBehavior;
import hive.model.insects.behaviors.SoldierAntBehavior;
import hive.model.insects.behaviors.SpiderBehavior;
import hive.model.players.Player;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author Thomas
 */
public class HiveRules implements Rules
{
    HivePutRules put_rules;
    InsectsBehaviors behaviors;
    
    public HiveRules()
    {
        this.put_rules = new HivePutRules();
        this.behaviors = new InsectsBehaviors();
        
        behaviors.put(InsectType.QUEEN_BEE, new QueenBeeBehavior());
        behaviors.put(InsectType.SPIDER, new SpiderBehavior());
        behaviors.put(InsectType.BEETLE, new BeetleBehavior());
        behaviors.put(InsectType.GRASSHOPPER, new GrasshopperBehavior());
        behaviors.put(InsectType.SOLDIER_ANT, new SoldierAntBehavior());
        behaviors.put(InsectType.MOSQUITO, new MosquitoBehavior());
        behaviors.put(InsectType.LADYBUG, new LadybugBehavior());
        behaviors.put(InsectType.PILL_BUG, new PillBugBehavior());
    }
    
    @Override
    public HivePutRules getPutRules()
    {
        return put_rules;
    }
    
    @Override
    public InsectsBehaviors getInsectsBehaviors()
    {
        return behaviors;
    }
    
    @Override
    public GameStatus getStatus(GameState state)
    {
        if(queenIsSurrounded(state, state.turn.getCurrent()))
            return GameStatus.OPPONENT_WINS;
        else if(queenIsSurrounded(state, state.turn.getOpponent()))
            return GameStatus.CURRENT_WINS;
        else
            return GameStatus.CONTINUES;
    }
    
    private boolean queenIsSurrounded(GameState state, Player player)
    {
        HashSet<Cell> queen_cells = state.data.tiles.get(player.color).get(InsectType.QUEEN_BEE);
        
        // Queen already put
        if(!queen_cells.isEmpty())
        {
            assert queen_cells.size() == 1;
            return Cells.isSurrounded(queen_cells.iterator().next());
        }
        else
            return false;
    }
}
