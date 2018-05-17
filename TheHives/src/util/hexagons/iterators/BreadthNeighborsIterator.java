/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.hexagons.iterators;

import hive.model.board.Honeycomb;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Queue;
import java.util.Set;
import java.util.function.Predicate;
import util.hexagons.Hexagon;
import util.hexagons.HexagonSide;

/**
 *
 * @author Thomas
 */
public class BreadthNeighborsIterator<E> implements Iterator<Hexagon<E>>
{
    Queue<Hexagon<E>> queue;
    Set<Hexagon<E>> seen;
    Predicate<Neighbor<E>> predicate;
    
    public BreadthNeighborsIterator(Hexagon<E> center, Predicate<Neighbor<E>> predicate)
    {
        this.queue = new ArrayDeque<>();
        this.seen = new HashSet<>();
        this.predicate = predicate;
        
        queue.add(center);
        seen.add(center);
        this.next();
    }

    @Override
    public boolean hasNext()
    {
        return !queue.isEmpty();
    }

    @Override
    public Hexagon<E> next()
    {
        assert hasNext();
        
        Hexagon<E> h = queue.remove();
        
        for(HexagonSide side : HexagonSide.values())
        {
            Neighbor<E> neighbor = new Neighbor(h, side, h.getNeighbor(side));
            assert neighbor.hexagon != null;
            if(!seen.contains(neighbor.hexagon) && predicate.test(neighbor))
            {
                queue.add(neighbor.hexagon);
                seen.add(neighbor.hexagon);
            }
        }
        return h;
    }
}