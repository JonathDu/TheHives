/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.game.utildata;

import hive.model.board.Honeycomb;
import hive.model.board.TilesStack;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;
import util.hexagons.iterators.NeighborsIterator;

/**
 * It "draws" a circle of influence around a tile
 * (a way to precalculate the neighbors and indirectly potential placements for put actions)
 * @author Thomas
 */
public class TilesInfluence extends HashMap<Honeycomb, AtomicInteger>
{
    private void addDeltaOccurences(Honeycomb comb, int delta)
    {
        assert get(comb) == null || (get(comb) != null && get(comb).get() > 0);
        
        AtomicInteger n = get(comb);
        if(n == null)
            put(comb, new AtomicInteger(delta));
        else
        {
            if(n.addAndGet(delta) == 0)
                remove(comb);
        }
        
        assert get(comb).get() > 0;
    }
    
    private void addDeltaInfluence(Honeycomb comb, int delta)
    {
        NeighborsIterator<TilesStack> neighbors = new NeighborsIterator<>(comb);
        while(neighbors.hasNext())
            addDeltaOccurences((Honeycomb)neighbors.next().hexagon, delta);
    }
    
    // specialization
    public void addInfluence(Honeycomb comb)
    {
        addDeltaInfluence(comb, 1);
    }
    
    // specialization
    public void removeInfluence(Honeycomb comb)
    {
        addDeltaInfluence(comb, -1);
    }
}
