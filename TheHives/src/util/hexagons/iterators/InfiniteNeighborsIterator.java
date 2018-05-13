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
 * @param <E>
 */
public class InfiniteNeighborsIterator<E> implements Iterator<Hexagon<E>>
{
    Hexagon<E> center;
    boolean clockwise;
    HexagonSide current;
    
    public InfiniteNeighborsIterator(Hexagon<E> center)
    {
        this(center, true);
    }
    
    public InfiniteNeighborsIterator(Hexagon<E> center, boolean clockwise)
    {
        this(center, clockwise, clockwise ? HexagonSide.A : HexagonSide.F);
    }
    
    public InfiniteNeighborsIterator(Hexagon<E> center, boolean clockwise, HexagonSide first)
    {
        this.center = center;
        this.clockwise = clockwise;
        this.current = first;
    }
    
    public HexagonSide getNextSide()
    {
        return current;
    }
    
    @Override
    public boolean hasNext()
    {
        return true;
    }
    
    @Override
    public Hexagon<E> next()
    {
        assert hasNext();
        Hexagon<E> res = center.getNeighbor(current);
        current = clockwise ? current.getAfter() : current.getBefore();
        return res;
    }
}