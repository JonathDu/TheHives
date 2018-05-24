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
import util.hexagons.iterators.Neighbor;
import util.hexagons.iterators.NeighborsIterator;

/**
 *
 * @author Thomas
 */
public class BeetleBehavior implements InsectBehavior
{
    @Override
    public void consumeDestinations(GameState state, Cell cell, Consumer<Cell> consumer)
    {
        if(HiveUtil.isCrushed(cell) || !HiveUtil.isConnexWithout(state, cell))
            return;
        
        NeighborsIterator neighbors = new NeighborsIterator(cell.comb);
        
        // for each neighbor
        while (neighbors.hasNext())
        {
            Neighbor<TilesStack> neighbor = neighbors.next();
            // if the beetle is below (or same level)
            if (cell.comb.value().size() <= neighbor.hexagon.value().size()) 
            {
                // the beetle can climb over it
                consumer.accept(new Cell((Honeycomb)neighbor.hexagon));
            }
           // otherwise the beetle is above
            else
            {
                // if the beetle is free to slide or go down
                if (HiveUtil.isFreeAtSide(cell, neighbor.from))
                {
                    // if the beetle is on the floor
                    if(cell.level == 0)
                    {
                        // if the beetle can slides next to a wall
                        if(HiveUtil.hasWallNextToAtSide(cell, neighbor.from) && HiveUtil.isFreeAtSide(cell, neighbor.from))
                            consumer.accept(new Cell((Honeycomb)neighbor.hexagon));
                    }
                    // otherwise it will stay connex anyway
                    else
                        consumer.accept(new Cell((Honeycomb)neighbor.hexagon));
                }
                // otherwise the beetle can't move
            }
        }
    }

    @Override
    public boolean isFree(GameState state, Cell cell)
    {
        if(HiveUtil.isCrushed(cell) || !HiveUtil.isConnexWithout(state, cell))
            return false;
        
        NeighborsIterator neighbors = new NeighborsIterator(cell.comb);
        
        // for each neighbor
        while (neighbors.hasNext())
        {
            Neighbor<TilesStack> neighbor = neighbors.next();
            // if the beetle is below (or same level)
            if (cell.comb.value().size() <= neighbor.hexagon.value().size()) 
            {
                // the beetle can climb over it
                return true;
            }
           // otherwise the beetle is above
            else
            {
                // if the beetle is free to slide or go down
                if (HiveUtil.isFreeAtSide(cell, neighbor.from))
                {
                    // if the beetle is on the floor
                    if(cell.level == 0)
                    {
                        // if the beetle can slides next to a wall
                        if(HiveUtil.hasWallNextToAtSide(cell, neighbor.from) && HiveUtil.isFreeAtSide(cell, neighbor.from))
                            return true;
                    }
                    // otherwise it will stay connex anyway
                    else
                        return true;
                }
                // otherwise the beetle can't move
            }
        }
        return false;
    }
    
    @Override
    public int hashCode()
    {
        int hash = 2;
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
