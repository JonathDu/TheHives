/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.insects.behaviors;

import hive.model.board.Cell;
import hive.model.game.rules.HiveUtil;
import hive.model.board.Honeycomb;
import hive.model.board.TilesStack;
import hive.model.game.GameState;
import hive.model.insects.InsectBehavior;
import hive.model.players.actions.Action;
import hive.model.players.actions.MoveAction;
import java.util.ArrayList;
import java.util.function.Consumer;
import util.hexagons.Hexagon;
import util.hexagons.HexagonSide;
import util.hexagons.iterators.LineAtSideIterator;
import util.iterators.StoppingIterator;

/**
 *
 * @author Thomas
 */
public class GrasshopperBehavior implements InsectBehavior
{
    @Override
    public void consumeDestinations(GameState state, Cell cell, Consumer<Cell> consumer)
    {
        assert cell.level == 0;
        
        if(HiveUtil.isCrushed(cell) || !HiveUtil.isConnexWithout(state, cell))
            return;
        
        // for each side
        for(HexagonSide side : HexagonSide.values())
        {
            // start in line
            StoppingIterator<Hexagon<TilesStack>> line = new StoppingIterator<>(
                    new LineAtSideIterator<>(cell.comb, side),
                    hexagon -> !hexagon.value().isEmpty());
            
            // the first neighbor must exist to jump over it
            if(!line.hasNext())
                continue;
            line.next();
            
            // looks for the end of the line
            while(line.hasNext())
            {
                line.next();
            }
            
            consumer.accept(new Cell((Honeycomb)line.getStoppingValue()));
        }
    }

    @Override
    public boolean isFree(GameState state, Cell cell)
    {
        assert cell.level == 0;
        
        if(HiveUtil.isCrushed(cell) || !HiveUtil.isConnexWithout(state, cell))
            return false;
        
        // for each side
        for(HexagonSide side : HexagonSide.values())
        {
            // start in line
            StoppingIterator<Hexagon<TilesStack>> line = new StoppingIterator<>(
                    new LineAtSideIterator<>(cell.comb, side),
                    hexagon -> !hexagon.value().isEmpty());
            
            // the first neighbor must exist to jump over it
            if(!line.hasNext())
                continue;
            line.next();
            
            // looks for the end of the line
            while(line.hasNext())
            {
                line.next();
            }
            
            return true;
        }
        return false;
    }
    
    @Override
    public int hashCode()
    {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        return true;
    }
}
