/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.hexagons.iterators;

import util.hexagons.Hexagon;
import util.hexagons.HexagonSide;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;
import java.util.function.Predicate;

/**
 * Iterates over the graph from a hexagon in breadth search
 * and by looking at a predicate that elements in hexagon must verify
 * (the graph must be circled by elements that does not verify the predicate)
 * @author Thomas
 * @param <E>
 */
public class BreadthIterator<E> implements Iterator<E>
{
    Stack<Hexagon<E>> stack;
    HashMap<Hexagon<E>, Boolean> seen;
    Predicate<E> predicate;
    
    public BreadthIterator(Hexagon<E> center, Predicate<E> predicate)
    {
        this.stack = new Stack<>();
        stack.push(center);
        
        this.seen = new HashMap<>();
        this.predicate = predicate;
    }

    @Override
    public boolean hasNext()
    {
        return !stack.empty();
    }

    @Override
    public E next()
    {
        assert hasNext();
        
        Hexagon<E> h = stack.pop();
        seen.put(h, Boolean.TRUE);
        
        for(HexagonSide side : HexagonSide.values())
        {
            Hexagon<E> n = h.getNeighbor(side);
            assert n != null;
            if(seen.get(n) == null && predicate.test(n.getValue()))
            {
                stack.push(n);
                seen.put(n, Boolean.TRUE);
            }
        }
        return h.getValue();
    }
}
