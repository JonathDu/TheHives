/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Collection;
import java.util.Iterator;
import java.util.function.Predicate;

/**
 *
 * @author Thomas
 */
public class Iterators
{
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
    
    public static <E> boolean searchN(Iterator<E> iterator, Predicate<E> predicate, int n)
    {
        int i = 0;
        while(iterator.hasNext())
        {
            E e = iterator.next();
            if(predicate.test(e))
                ++i;
            if(i == n)
                return true;
        }
        return false;
    }
}