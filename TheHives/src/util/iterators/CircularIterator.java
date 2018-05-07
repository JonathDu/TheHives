/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.iterators;

import java.util.Collection;
import java.util.Iterator;

/**
 * Circular iterator (moves forward only, and i.next(last) = first (circular)) : never stops exept when the collection is empty
 * @author Thomas
 * @param <E>
 */
public class CircularIterator<E> implements Iterator
{
    Collection<E> c;
    Iterator<E> current;
    
    public CircularIterator(Collection c)
    {
        this.c = c;
        this.current = c.iterator();
    }
    
    @Override
    public boolean hasNext()
    {
        return c.iterator().hasNext();
    }
    
    @Override
    public E next()
    {
        assert hasNext();
        if(!current.hasNext())
           current = c.iterator();
        return current.next();
    }
}
