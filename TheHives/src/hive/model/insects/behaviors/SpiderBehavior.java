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
import java.util.ArrayList;
import util.hexagons.iterators.BreadthNeighborsAtLengthIterator;
import util.hexagons.iterators.PathAtLengthIterator;

/**
 *
 * @author Thomas
 */
public class SpiderBehavior implements InsectBehavior
{
    @Override
    public ArrayList<Cell> getPossibleDestinations(GameState state, Cell cell)
    {
        assert cell.level == 0;
        
        ArrayList<Cell> list = new ArrayList<>();
        
        if(HiveUtil.isCrushed(cell) || !HiveUtil.isConnexWithout(state, cell))
            return list;
        
        Tile tmp = cell.comb.value().pop();
        
        PathAtLengthIterator<TilesStack> iterator = new PathAtLengthIterator<>(
                cell.comb,
                neighbor -> HiveUtil.hasWallNextToAtSide(new Cell((Honeycomb)neighbor.origin, 0), neighbor.from) && HiveUtil.isFreeAtSide(new Cell((Honeycomb)neighbor.origin, 0), neighbor.from),
                3);
        
        while(iterator.hasNext())
            list.add(new Cell((Honeycomb)iterator.next()));
        
        cell.comb.value().push(tmp);
        
        return list;
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
