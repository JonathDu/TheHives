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
import testutil.RandomUtil;
import testutil.TestUtil;

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
        Predicate<Integer> p;
        ArrayList<Integer> list = new ArrayList<>();
        
        for(int k = 0; k < 100; ++k)
        {
            RandomUtil.nextList(list, () -> RandomUtil.nextInt(100), 1000);
            p = (t) -> t <= 80;
            StoppingIterator<Integer> it = new StoppingIterator<>(list.iterator(), p);
            
            int idx = 0;
            while (it.hasNext())
            {
                Integer v = it.next();
                assert p.test(v);
                assert v.equals(list.get(idx++));
            }
        }
    }
}
