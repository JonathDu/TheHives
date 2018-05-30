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
import hive.model.insects.behaviors.info.CombData;
import hive.model.insects.behaviors.info.CombInfo;
import hive.model.insects.behaviors.info.Info;
import java.util.function.Consumer;
import util.hexagons.iterators.Neighbor;
import util.hexagons.iterators.NeighborsIterator;

/**
 *
 * @author Thomas
 */
public class QueenBeeBehavior implements InsectBehavior
{
    @Override
    public void consumeDestinations(GameState state, Cell cell, Consumer<Cell> consumer)
    {
        assert cell.level == 0;
        
        if(HiveUtil.isCrushed(cell) || !HiveUtil.isConnexWithout(state, cell))
            return;
        
        consumeDestinations(cell, consumer);
    }

    public void consumeDestinations(Cell cell, Consumer<Cell> consumer)
    {
        NeighborsIterator<TilesStack> neighbors = new NeighborsIterator<>(cell.comb);
        
        // for each neighbor
        while (neighbors.hasNext())
        {
            Neighbor<TilesStack> neighbor = neighbors.next();
            
            // the queen only slides
            if(!neighbor.hexagon.value().isEmpty())
                continue;
            
            // the queen can slide but the queen has to stay connected with other tiles
            if(HiveUtil.hasWallNextToAtSide(cell, neighbor.from) && HiveUtil.isFreeAtSide(cell, neighbor.from))
                consumer.accept(new Cell((Honeycomb)neighbor.hexagon));
        }
    }
    
    @Override
    public Info consumeDestinations(GameState state, Cell cell, Consumer<Cell> consumer, Consumer<CombData> info_giver)
    {
        assert cell.level == 0;
        
        if(HiveUtil.isCrushed(cell))
        {
            Info info = Info.IS_CRUSHED;
            if(!HiveUtil.isConnexWithout(state, cell))
                info.add(Info.IS_NOT_CONNEX_WITHOUT);
            return info;
        }
        
        NeighborsIterator<TilesStack> neighbors = new NeighborsIterator<>(cell.comb);
        
        // for each neighbor
        while (neighbors.hasNext())
        {   
            CombInfo info = CombInfo.NONE;
            
            Neighbor<TilesStack> neighbor = neighbors.next();
            
            // the queen only slides
            if(!neighbor.hexagon.value().isEmpty())
            {
                info_giver.accept(new CombData((Honeycomb)neighbor.hexagon, CombInfo.CANT_CLIMB));
                continue;
            }
            
            // the queen can slide but the queen has to stay connected with other tiles
            if(HiveUtil.isFreeAtSide(cell, neighbor.from))
            {
                info.add(CombInfo.IS_FREE);
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
            info_giver.accept(new CombData((Honeycomb)neighbor.hexagon, info));
        }
        
        return Info.OK;
    }

    @Override
    public boolean isFree(GameState state, Cell cell)
    {
        assert cell.level == 0;
        
        if(HiveUtil.isCrushed(cell) || !HiveUtil.isConnexWithout(state, cell))
            return false;
        
        NeighborsIterator<TilesStack> neighbors = new NeighborsIterator<>(cell.comb);
        
        // for each neighbor
        while (neighbors.hasNext())
        {
            Neighbor<TilesStack> neighbor = neighbors.next();
            
            // the queen only slides
            if(!neighbor.hexagon.value().isEmpty())
                continue;
            
            // the queen can slide but the queen has to stay connected with other tiles
            if(HiveUtil.hasWallNextToAtSide(cell, neighbor.from) && HiveUtil.isFreeAtSide(cell, neighbor.from))
                return true;
        }
        return false;
    }
    
    @Override
    public int hashCode()
    {
        int hash = 13;
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
