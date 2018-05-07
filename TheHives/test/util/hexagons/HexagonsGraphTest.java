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
public class HexagonsGraphTest
{
    
    public HexagonsGraphTest()
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
     * Test of getCenter method, of class HexagonsGraph.
     */
    @Test
    public void testGetCenter()
    {
        System.out.println("getCenter");
        HexagonsGraph instance = new HexagonsGraph();
        Hexagon expResult = null;
        Hexagon result = instance.getCenter();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCenter method, of class HexagonsGraph.
     */
    @Test
    public void testSetCenter()
    {
        System.out.println("setCenter");
        HexagonsGraph instance = new HexagonsGraph();
        instance.setCenter(null);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isEmpty method, of class HexagonsGraph.
     */
    @Test
    public void testIsEmpty()
    {
        System.out.println("isEmpty");
        HexagonsGraph instance = new HexagonsGraph();
        boolean expResult = false;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
