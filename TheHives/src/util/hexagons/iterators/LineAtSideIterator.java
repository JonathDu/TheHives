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
 * Iterates in a straight line from a hexagon in the side direction
 * @author Thomas
 * @param <E>
 */
public class LineAtSideIterator<E> implements Iterator<Hexagon<E>>
{
    Hexagon<E> current;
    HexagonSide side;
    
    public LineAtSideIterator(Hexagon<E> first, HexagonSide side)
    {
        this.current = first.getNeighbor(side);
        this.side = side;
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
        Hexagon<E> res = current;
        current = current.getNeighbor(side);
        return res;
    }
}
