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
public class NeighborsIterator<E> implements Iterator<Neighbor<E>>
{
    Hexagon<E> center;
    boolean clockwise;
    HexagonSide current;
    HexagonSide last;
    
    public NeighborsIterator(Hexagon<E> center)
    {
        this(center, true);
    }
    
    public NeighborsIterator(Hexagon<E> center, boolean clockwise)
    {
        this(center, clockwise, clockwise ? HexagonSide.A : HexagonSide.F);
    }
    
    public NeighborsIterator(Hexagon<E> center, boolean clockwise, HexagonSide side)
    {
        this(center, clockwise, side, clockwise ? side.getBefore() : side.getAfter());
    }
    
    public NeighborsIterator(Hexagon<E> center, boolean clockwise, HexagonSide first, HexagonSide last)
    {
        this.center = center;
        this.clockwise = clockwise;
        this.current = first;
        this.last = last;
    }
    
    public HexagonSide getVisitedSide()
    {
        return clockwise ? current.getBefore() : current.getAfter();
    }
    
    @Override
    public boolean hasNext()
    {
        return current != null;
    }
    
    @Override
    public Neighbor<E> next()
    {
        assert hasNext();
        Neighbor<E> res = new Neighbor<>(center.getNeighbor(current), current);
        current = current != last ? (clockwise ? current.getAfter() : current.getBefore()) : null;
        return res;
    }
}
