/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.hexagons.iterators;

import java.util.ArrayDeque;
import util.hexagons.Hexagon;
import util.hexagons.HexagonSide;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Queue;
import java.util.function.Predicate;

/**
 * Iterates over the graph from a hexagon in breadth search
 * and by looking at a predicate that elements in hexagon must verify
 * (the graph must be circled by elements that does not verify the predicate)
 * @author Thomas
 * @param <E>
 */
public class BreadthAtLengthIterator<E> implements Iterator<Hexagon<E>>
{
    Queue<Hexagon<E>> queue;
    HashMap<Hexagon<E>, Boolean> seen;
    Predicate<Hexagon<E>> predicate;
    
    public BreadthAtLengthIterator(Hexagon<E> center, Predicate<Hexagon<E>> predicate, int n)
    {
        this.queue = new ArrayDeque<>();
        queue.add(center);
        
        this.seen = new HashMap<>();
        this.predicate = predicate;
        
        // n - 1 times, as the queue contains the n-th hexagons
        for(int i = 0; i < n - 1; ++i)
        {
            Hexagon<E> h = queue.remove();
            seen.put(h, Boolean.TRUE);
        
            for(HexagonSide side : HexagonSide.values())
            {
                Hexagon<E> neighbor = h.getNeighbor(side);
                assert neighbor != null;
                if(seen.get(neighbor) == null && predicate.test(neighbor))
                {
                    queue.add(neighbor);
                    seen.put(neighbor, Boolean.TRUE);
                }
            }
            
            if(queue.isEmpty())
                break;
        }
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
        return h;
    }
}
