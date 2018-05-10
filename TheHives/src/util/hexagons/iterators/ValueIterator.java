/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.hexagons.iterators;

import java.util.Iterator;
import util.hexagons.Hexagon;

/**
 *
 * @author Thomas
 * @param <E>
 */
public class ValueIterator<E> implements Iterator<E>
{
    Iterator<Hexagon<E>> iterator;
    
    public ValueIterator(Iterator<Hexagon<E>> iterator)
    {
        this.iterator = iterator;
    }
    
    @Override
    public boolean hasNext()
    {
        return iterator.hasNext();
    }

    @Override
    public E next()
    {
        assert hasNext();
        return iterator.next().getValue();
    }
    
}
