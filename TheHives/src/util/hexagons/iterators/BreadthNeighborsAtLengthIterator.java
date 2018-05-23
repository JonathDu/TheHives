/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.hexagons.iterators;

import java.util.ArrayDeque;
import util.hexagons.Hexagon;
import util.hexagons.HexagonSide;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Queue;
import java.util.Set;
import java.util.function.Predicate;

/**
 * Iterates over the graph from a hexagon in breadth search
 * and by looking at a predicate that elements in hexagon must verify
 * (the graph must be circled by elements that does not verify the predicate)
 * @author Thomas
 * @param <E>
 */
public class BreadthNeighborsAtLengthIterator<E> implements Iterator<Hexagon<E>>
{
    Queue<Hexagon<E>> queue;
    Set<Hexagon<E>> seen;
    Predicate<Neighbor<E>> predicate;
    int m;
    int n;
    
    public BreadthNeighborsAtLengthIterator(Hexagon<E> center, Predicate<Neighbor<E>> predicate, int n)
    {
        this.queue = new ArrayDeque<>();
        
        this.seen = new HashSet<>();
        this.predicate = predicate;
        this.m = 0;
        this.n = n;
        
        queue.add(center);
        seen.add(center);
        
        // remove once
        remove(1);
    }
    
    private void remove(int k)
    {
        ++m;
        int count = 0;
        for(int i = 0; i < k; ++i)
        {
            Hexagon<E> h = queue.remove();
            for(HexagonSide side : HexagonSide.values())
            {
                Neighbor<E> neighbor = new Neighbor(h, side, h.getNeighbor(side));
                assert neighbor.hexagon != null;
                if(!seen.contains(neighbor.hexagon) && predicate.test(neighbor))
                {
                    ++count;
                    queue.add(neighbor.hexagon);
                    seen.add(neighbor.hexagon);
                }
            }
        }
        if(m < n)
            remove(count);
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
