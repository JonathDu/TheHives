/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Collection;
import java.util.Iterator;

/**
 *
 * @author Thomas
 */
public class Iterators
{
    public static <E> Collection<E> fill(Collection<E> collection, Iterator<E> iterator)
    {
        while(iterator.hasNext())
            collection.add(iterator.next());
        return collection;
    }
    
    public static <E> int count(Iterator<E> iterator)
    {
        int i = 0;
        while(iterator.hasNext())
        {
            ++i;
            iterator.next();
        }
        return i;
    }
}
