/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.iterators;

import java.util.ArrayList;
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
public class StoppingIteratorTest
{

    public StoppingIteratorTest()
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
     * Test of next method, of class StoppingIterator.
     */
    @Test
    public void testNext()
    {
        Predicate<Integer> p = (t) -> t <= 5;

        ArrayList<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(2);
        list.add(8);
        list.add(1);
        list.add(41);

        StoppingIterator<Integer> it = new StoppingIterator<>(list.iterator(), p);

        while (it.hasNext())
        {
            //System.out.println(f.next());
            assert p.test(it.next());
        }
        
        assert it.getStoppingValue() == 8;

        assert true;
    }
}
