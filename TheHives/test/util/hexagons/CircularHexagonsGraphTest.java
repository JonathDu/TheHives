/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.hexagons;

import hive.model.board.HiveNeighborsShifter;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import util.Matrix;
import util.Vector2i;

/**
 *
 * @author lucas
 */
public class CircularHexagonsGraphTest
{
    private Matrix<Integer> m;  
    private CircularHexagonsGraph<Integer, Hexagon<Integer>> c;
    
    public CircularHexagonsGraphTest()
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
        Integer[][] tab = 
        {
            {1,2,3,4},
            {5,6,7,8},
            {9,10,11,12},
            {13,14,15,16}
        };
        HiveNeighborsShifter getter = new HiveNeighborsShifter();
        
        m = new Matrix<>(tab);
        c = new CircularHexagonsGraph(m, getter, (x, y) -> new Hexagon());
    }
    
    @After
    public void tearDown()
    {
    }

    @Test
    public void testCreateCircular()
    {   
        for(int y = 0; y < m.sizeY(); y++)
        {
            for(int x = 0; x < m.sizeX(); x++)
            {
                assertEquals(c.getHexagon(new Vector2i(x,y)).value() ,m.getAt(x, y));
            }
        }
    }
    
    @Test
    public void testToString()
    {
        System.out.println(c);
        assert true;
    }
    
}

