/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.iterators;

import java.util.List;
import java.util.ListIterator;

/**
 * Circular list iterator (moves forward and backward, and i.next(last) = first (circular)) : never stops exept when the collection is empty
 * @author Thomas
 * @param <E>
 */
public class CircularListIterator<E> implements ListIterator
{
    List<E> l;
    ListIterator<E> current;
    
    public CircularListIterator(List l)
    {
        this.l = l;
        this.current = l.listIterator();
    }
    
    @Override
    public boolean hasNext()
    {
        return l.listIterator().hasNext();
    }
    
    @Override
    public E next()
    {
        assert hasNext();
        if(!current.hasNext())
           current = l.listIterator();
        return current.next();
    }
    
    @Override
    public boolean hasPrevious()
    {
        return l.listIterator().hasPrevious();
    }
    
    @Override
    public E previous()
    {
        assert hasPrevious();
        if(!current.hasPrevious())
            current = l.listIterator(l.size() - 1);
        return current.previous();
    }

    @Override
    public int nextIndex()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int previousIndex()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void set(Object e)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(Object e)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}