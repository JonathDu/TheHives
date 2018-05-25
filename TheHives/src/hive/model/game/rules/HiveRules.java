/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.game.rules;

import hive.model.board.Cell;
import hive.model.board.Tile;
import hive.model.game.GameState;
import hive.model.insects.InsectType;
import java.io.Serializable;
import java.util.function.Consumer;

/**
 *
 * @author Thomas
 */
public class HiveRules implements Rules, Serializable
{
    public HivePutRules put_rules;
    public HiveMoveRules move_rules;
    
    public HiveRules()
    {
        this.put_rules = new HivePutRules();
        this.move_rules = new HiveMoveRules();
    }
    
    public void consumePlacements(GameState state, InsectType type, Consumer<Cell> consumer)
    {
        if(queenMustBePut(state))
        {
            if(type == InsectType.QUEEN_BEE)
            {
                if (state.turn.getCurrent().collection.get(type) > 0)
                    consumePlacementsConstantTime(state, consumer);
            }
        }
        else
        {
            if (state.turn.getCurrent().collection.get(type) > 0)
                consumePlacementsConstantTime(state, consumer);
        }
    }
    
    @Override
    public void consumePlacements(GameState state, Tile tile, Consumer<Cell> consumer)
    {
        consumePlacements(state, tile.type, consumer);
    }
    
    @Override
    public void consumeDestinations(GameState state, Cell cell, Consumer<Cell> consumer)
    {
        if(!queenIsPut(state))
            return;
        if(queenMustBePut(state))
        {
            if(cell.getTile().type == InsectType.QUEEN_BEE)
                move_rules.consumeDestinations(state, cell, consumer);
        }
        else
            move_rules.consumeDestinations(state, cell, consumer);
    }
    
    @Override
    public boolean isFree(GameState state, Cell cell)
    {
        return move_rules.isFree(state, cell);
    }
    
    @Override
    public GameStatus getStatus(GameState state)
    {
        boolean current_wins = HiveUtil.queenIsSurrounded(state, state.turn.getOpponent());
        boolean opponent_wins = HiveUtil.queenIsSurrounded(state, state.turn.getCurrent());
        
        if((current_wins && opponent_wins) || HiveUtil.nobodyCanPlay(state))
            return GameStatus.DRAW;
        else if(current_wins)
            return GameStatus.CURRENT_WINS;
        else if(opponent_wins)
            return GameStatus.OPPONENT_WINS;
        else
            return GameStatus.CONTINUES;
    }
    
    private void consumePlacementsConstantTime(GameState state, Consumer<Cell> consumer)
    {
        if(!state.data.placements_initialized)
        {
            // placements are not initialized
            state.data.placements_initialized = true;
            state.data.placements.clear();
            put_rules.consumePlacements(state, cell -> state.data.placements.add(cell));
        }
        for(Cell cell : state.data.placements)
            consumer.accept(cell);
    }
    
    public boolean queenMustBePut(GameState state)
    {
        return HiveUtil.nbTurns(state) == getMaxQueenTurn() && !queenIsPut(state);
    }
    
    public boolean queenIsPut(GameState state)
    {
        return !state.data.tiles.get(state.turn.getCurrent().color).get(InsectType.QUEEN_BEE).isEmpty();
    }
    
    public int getMaxQueenTurn()
    {
        return 4;
    }
}
