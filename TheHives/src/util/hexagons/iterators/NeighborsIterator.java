/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.hexagons.iterators;

import util.hexagons.Hexagon;
import util.hexagons.HexagonSide;
import java.util.Iterator;

/**
 * Iterate over all the neighbors of a hexagon (all the neighbors must exist)
 * @author Thomas
 * @param <E>
 */
public class NeighborsIterator<E> implements Iterator<Hexagon<E>>
{
    Hexagon<E> center;
    HexagonSide current;
    HexagonSide last;
    
    public NeighborsIterator(Hexagon<E> center)
    {
        this(center, HexagonSide.A);
    }
    
    public NeighborsIterator(Hexagon<E> center, HexagonSide side)
    {
        this(center, side, side.getBefore());
    }
    
    public NeighborsIterator(Hexagon<E> center, HexagonSide first, HexagonSide last)
    {
        this.center = center;
        this.current = first;
        this.last = last;
    }
    
    public HexagonSide getNextSide()
    {
        return current;
    }
    
    @Override
    public boolean hasNext()
    {
        return current != null;
    }
    
    @Override
    public Hexagon<E> next()
    {
        assert hasNext();
        Hexagon<E> res = center.getNeighbor(current);
        current = current != last ? current.getAfter() : null;
        return res;
    }
}
