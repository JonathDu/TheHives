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

/**
 *
 * @author lucas
 */
public class HexagonsTest
{
    private Hexagon<Integer> h1;
    private Hexagon<Integer> h2;
    
    public HexagonsTest()
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
        h1 = new Hexagon<>(1);
        h2 = new Hexagon<>(2);
    }
    
    @After
    public void tearDown()
    {
    }

    @Test
    public void testBind()
    {
        Hexagons.bind(h1, HexagonSide.D, h2);
        
        assertEquals(h1.getNeighbor(HexagonSide.D), h2);
        assertEquals(h2.getNeighbor(HexagonSide.A), h1);
    }

    @Test
    public void testSwap()
    {
        Hexagons.swap(h1, h2);
        
        assert h1.getValue() == 2;
        assert h2.getValue() == 1;
    }

    @Test
    public void testTransfer()
    {
        Hexagons.transfer(h1, h2);
        
        assert h1 == null;
        assert h2.getValue() == 1;
    }

    @Test
    public void testMove()
    {
        System.out.println("/!!!\\ TODO /!!!\\ : move");
        //Hexagons.move(null);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
