/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.hexagons;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import util.Vector2i;

/**
 *
 * @author lucas
 */
public class CircularPositionMakerTest
{

    public CircularPositionMakerTest()
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
     * Test of circular method, of class CircularPositionMaker.
     */
    @Test
    public void testCircular()
    {
        Vector2i dim = new Vector2i(10, 10);
        int max = 30;
        CircularPositionMaker maker = new CircularPositionMaker(dim);
        for (int n = -max; n < max; n++)
        {
            for (int y = 0; y < dim.y; ++y)
            {
                for (int x = 0; x < dim.x; ++x)
                {
                    Vector2i p = new Vector2i(x, y);
                    Vector2i pc = (new Vector2i(p)).add((new Vector2i(dim)).multiply(n));
                    assert maker.circular(pc).equals(p);
                }
            }
        }
    }
}

