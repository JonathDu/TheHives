/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.insects.behaviors;

import hive.model.board.Cell;
import hive.model.game.rules.HiveUtil;
import hive.model.board.Honeycomb;
import hive.model.board.Tile;
import hive.model.board.TilesStack;
import hive.model.game.GameState;
import hive.model.insects.InsectBehavior;
import hive.model.insects.behaviors.info.CombData;
import hive.model.insects.behaviors.info.Info;
import java.util.function.Consumer;
import util.hexagons.iterators.PathAtLengthIterator;

/**
 *
 * @author Thomas
 */
public class SpiderBehavior implements InsectBehavior
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
        Tile tmp = cell.comb.value().pop();
        
        PathAtLengthIterator<TilesStack> iterator = new PathAtLengthIterator<>(
                cell.comb,
                neighbor -> HiveUtil.hasWallNextToAtSide(new Cell((Honeycomb)neighbor.origin, 0), neighbor.from) && HiveUtil.isFreeAtSide(new Cell((Honeycomb)neighbor.origin, 0), neighbor.from),
                3);
        
        while(iterator.hasNext())
            consumer.accept(new Cell((Honeycomb)iterator.next()));
        
        cell.comb.value().push(tmp);
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
        
        Tile tmp = cell.comb.value().pop();
        
        PathAtLengthIterator<TilesStack> iterator = new PathAtLengthIterator<>(
                cell.comb,
                neighbor -> HiveUtil.hasWallNextToAtSide(new Cell((Honeycomb)neighbor.origin, 0), neighbor.from) && HiveUtil.isFreeAtSide(new Cell((Honeycomb)neighbor.origin, 0), neighbor.from),
                3);
        
        while(iterator.hasNext())
            consumer.accept(new Cell((Honeycomb)iterator.next()));
        
        cell.comb.value().push(tmp);
        
        return Info.OK;
    }

    @Override
    public boolean isFree(GameState state, Cell cell)
    {
        assert cell.level == 0;
                
        if(HiveUtil.isCrushed(cell) || !HiveUtil.isConnexWithout(state, cell))
            return false;
        
        Tile tmp = cell.comb.value().pop();
        
        PathAtLengthIterator<TilesStack> iterator = new PathAtLengthIterator<>(
                cell.comb,
                neighbor -> HiveUtil.hasWallNextToAtSide(new Cell((Honeycomb)neighbor.origin, 0), neighbor.from) && HiveUtil.isFreeAtSide(new Cell((Honeycomb)neighbor.origin, 0), neighbor.from),
                3);
        
        while(iterator.hasNext())
        {
            cell.comb.value().push(tmp);
            return true;
        }
        
        cell.comb.value().push(tmp);
        
        return false;
    }
    
    @Override
    public int hashCode()
    {
        int hash = 19;
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
