/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.iterators;

import java.util.Iterator;
import java.util.function.Predicate;

/**
 * Iterates by ignoring elements that does not verify the predicate
 * @author Thomas
 * @param <E>
 */
public class FilteringIterator<E> implements Iterator<E>
{
    Iterator<E> iterator;
    Predicate<E> predicate;
    E value;
    boolean found;
    
    public FilteringIterator(Iterator<E> iterator, Predicate<E> predicate)
    {
        this.iterator = iterator;
        this.predicate = predicate;
        
        found = findNext();
    }
    
    @Override
    public boolean hasNext()
    {
        return found;
    }
    
    @Override
    public E next()
    {
        assert hasNext();
        E res = value;
        found = findNext();
        return res;
    }
    
    private boolean findNext()
    {
        do
        {
            if(iterator.hasNext())
                value = iterator.next();
            else
                return false;
        } while(!predicate.test(value));
        return true;
    }
}
