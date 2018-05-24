/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.game.utildata;

import hive.model.board.Honeycomb;
import hive.model.board.TilesStack;
import hive.model.game.rules.HiveUtil;
import java.util.HashMap;
import java.util.Iterator;
import util.hexagons.iterators.Neighbor;
import util.hexagons.iterators.NeighborsIterator;

/**
 * To know how many groups of neighbors there are around each comb
 * (groups of neighbors are consecutives neighbors that have a non-empty stack)
 * @author Thomas
 */
public class NbGroupsPerComb extends HashMap<Honeycomb, Integer>
{
    public void addComb(Honeycomb comb)
    {
        put(comb, HiveUtil.nbGroups(comb));
        refreshNeighbors(comb);
    }
    
    public void removeComb(Honeycomb comb)
    {
        remove(comb);
        refreshNeighbors(comb);
    }
    
    private void refreshNeighbors(Honeycomb comb)
    {
        Iterator<Neighbor<TilesStack>> neighbors = new NeighborsIterator<>(comb);
        
        while(neighbors.hasNext())
        {
            Neighbor<TilesStack> neighbor = neighbors.next();
            if(!neighbor.hexagon.value().isEmpty())
                put((Honeycomb)neighbor.hexagon, HiveUtil.nbGroups((Honeycomb)neighbor.hexagon));
        }
    }
}
