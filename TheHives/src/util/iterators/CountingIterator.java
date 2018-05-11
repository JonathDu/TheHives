/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.iterators;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author Thomas
 * @param <E>
 */
public class CountingIterator<E> implements Iterator<E>
{
    StoppingIterator<E> stopping;
    AtomicInteger n;
    
    public CountingIterator(Iterator<E> iterator, int n)
    {
        this.n = new AtomicInteger(n);
        this.stopping = new StoppingIterator<>(iterator, e -> this.n.decrementAndGet() >= 0);
    }

    @Override
    public boolean hasNext()
    {
        return stopping.hasNext();
    }

    @Override
    public E next()
    {
        assert hasNext();
        return stopping.next();
    }
}
