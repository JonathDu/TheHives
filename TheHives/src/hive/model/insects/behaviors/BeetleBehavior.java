/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.insects.behaviors;

import hive.model.insects.behaviors.info.CombData;
import hive.model.board.Cell;
import hive.model.game.rules.HiveUtil;
import hive.model.board.Honeycomb;
import hive.model.board.TilesStack;
import hive.model.game.GameState;
import hive.model.insects.InsectBehavior;
import hive.model.insects.behaviors.info.CombInfo;
import hive.model.insects.behaviors.info.Info;
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
                        if(HiveUtil.hasWallNextToAtSide(cell, neighbor.from))
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

    // consumes on valid and invali
    @Override
    public Info consumeDestinations(GameState state, Cell cell, Consumer<Cell> consumer, Consumer<CombData> info_giver)
    {
        if(HiveUtil.isCrushed(cell))
        {
            Info info = Info.IS_CRUSHED;
            if(!HiveUtil.isConnexWithout(state, cell))
                info.add(Info.IS_NOT_CONNEX_WITHOUT);
            return info;
        }
        
        NeighborsIterator neighbors = new NeighborsIterator(cell.comb);
        
        // for each neighbor
        while (neighbors.hasNext())
        {
            CombInfo info = CombInfo.NONE;
            
            Neighbor<TilesStack> neighbor = neighbors.next();
            // if the beetle is below (or same level)
            if (cell.comb.value().size() <= neighbor.hexagon.value().size()) 
            {
                // the beetle can climb over it
                consumer.accept(new Cell((Honeycomb)neighbor.hexagon));
                info.add(CombInfo.CAN_CLIMB);
            }
           // otherwise the beetle is above
            else
            {
                // if the beetle is free to slide or go down
                if (HiveUtil.isFreeAtSide(cell, neighbor.from))
                {
                    info.add(CombInfo.IS_FREE);
                    // if the beetle is on the floor
                    if(cell.level == 0)
                    {
                        // if the beetle can slides next to a wall
                        if(HiveUtil.hasWallNextToAtSide(cell, neighbor.from))
                        {
                            info.add(CombInfo.HAS_WALL);
                            consumer.accept(new Cell((Honeycomb)neighbor.hexagon));
                        }
                        else
                        {
                            if(HiveUtil.hasNeighbors((Honeycomb)neighbor.hexagon))
                                info.add(CombInfo.HAS_NO_WALL);
                            else
                                continue;
                        }
                    }
                    // otherwise it will stay connex anyway
                    else
                    {
                        
                        if(cell.level > neighbor.hexagon.value().size() - 1)
                            info.add(CombInfo.IS_ABOVE);
                        else
                            info.add(CombInfo.IS_ON_TILES);
                        consumer.accept(new Cell((Honeycomb)neighbor.hexagon));
                    }
                }
                // otherwise the beetle can't move
                else
                    info.add(CombInfo.IS_NOT_FREE);
            }
            info_giver.accept(new CombData((Honeycomb)neighbor.hexagon, info));
        }
        return Info.OK;
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
                        if(HiveUtil.hasWallNextToAtSide(cell, neighbor.from))
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
