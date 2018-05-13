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
public class HexagonTest
{
    
    public HexagonTest()
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
     * Test of value method, of class Hexagon.
     */
    @Test
    public void testGetSetNeighbor()
    {
        Hexagon<Integer> h = new Hexagon<>(0);
        Hexagon<Integer> ha = new Hexagon<>(1);
        Hexagon<Integer> hb = new Hexagon<>(2);
        Hexagon<Integer> hc = new Hexagon<>(3);
        Hexagon<Integer> hd = new Hexagon<>(4);
        Hexagon<Integer> he = new Hexagon<>(5);
        Hexagon<Integer> hf = new Hexagon<>(6);
        
        h.setNeighbor(HexagonSide.A, ha);
        h.setNeighbor(HexagonSide.B, hb);
        h.setNeighbor(HexagonSide.C, hc);
        h.setNeighbor(HexagonSide.D, hd);
        h.setNeighbor(HexagonSide.E, he);
        h.setNeighbor(HexagonSide.F, hf);
        
        assertEquals(ha, h.getNeighbor(HexagonSide.A)); 
        assertEquals(hb, h.getNeighbor(HexagonSide.B)); 
        assertEquals(hc, h.getNeighbor(HexagonSide.C)); 
        assertEquals(hd, h.getNeighbor(HexagonSide.D)); 
        assertEquals(he, h.getNeighbor(HexagonSide.E)); 
        assertEquals(hf, h.getNeighbor(HexagonSide.F));
    }
    
}
