/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.hexagons.iterators;

import java.util.Iterator;
import util.hexagons.Hexagon;
import util.hexagons.HexagonSide;

/**
 *
 * @author Thomas
 */
public class NeighborsValueIterator<E> implements Iterator<E>
{
    Hexagon<E> center;
    HexagonSide current;
    HexagonSide last;
    
    public NeighborsValueIterator(Hexagon<E> center)
    {
        this(center, HexagonSide.A);
    }
    
    public NeighborsValueIterator(Hexagon<E> center, HexagonSide side)
    {
        this.center = center;
        this.current = side;
        this.last = side.getBefore();
    }
    
    @Override
    public boolean hasNext()
    {
        return current != null;
    }
    
    @Override
    public E next()
    {
        assert hasNext();
        E res = center.getNeighbor(current).getValue();
        current = current != last ? current.getAfter() : null;
        return res;
    }
}
