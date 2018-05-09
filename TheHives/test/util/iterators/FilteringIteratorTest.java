/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.iterators;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Predicate;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lucas
 */
public class FilteringIteratorTest
{
    
    public FilteringIteratorTest()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }
    
    @Before
    public void setUp()
    {
    }
    
    @After
    public void tearDown()
    {
    }

    /**
     * Test of next method, of class FilteringIterator.
     */
    @Test
    public void testNext()
    {
        Predicate<Integer> p = (t) -> t <= 5;
        
        ArrayList<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(8);
        list.add(2);
        list.add(1);
        list.add(41);
        
        FilteringIterator<Integer> f = new FilteringIterator<>(list.iterator(), p);
        
        while(f.hasNext())
        {
            //System.out.println(f.next());
            assert p.test(f.next());
        }
        
        assert true;
    }
}
