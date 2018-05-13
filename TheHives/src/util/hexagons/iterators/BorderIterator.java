/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.hexagons.iterators;

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
public class BorderIterator<E> implements Iterator<Hexagon<E>>
{
    Queue<Hexagon<E>> queue;
    Set<Hexagon<E>> seen;
    Predicate<Hexagon<E>> predicate;
    
    public BorderIterator(Hexagon<E> center, Predicate<Hexagon<E>> predicate)
    {
        this.queue = new ArrayDeque<>();
        queue.add(center);
        
        this.seen = new HashSet<>();
        this.predicate = predicate;
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
        seen.add(h);
        
        for(HexagonSide side : HexagonSide.values())
        {
            Hexagon<E> neighbor = h.getNeighbor(side);
            assert neighbor != null;
            if(!seen.contains(neighbor) && predicate.test(neighbor))
            {
                queue.add(neighbor);
                seen.add(neighbor);
            }
        }
        return h;
    }
}