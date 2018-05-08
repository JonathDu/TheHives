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
    }
    
    @After
    public void tearDown()
    {
    }

    @Test
    public void testCreateCircular()
    {
        Integer[][] tab = 
        {
            {1,2,3,4},
            {5,6,7,8},
            {9,10,11,12},
            {13,14,15,16},
            {17,18,19,20}
        };
        Matrix<Integer> m = new Matrix<>(tab);
        
        HiveNeighborsShifter getter = new HiveNeighborsShifter();
        CircularHexagonsGraph<Integer> c = new CircularHexagonsGraph(m, getter);
        
        for(int y = 0; y < m.sizeY(); y++)
        {
            for(int x = 0; x < m.sizeX(); x++)
            {
                assertEquals(c.getHexagon(new Vector2i(x,y)).getValue() ,m.getAt(x, y));
            }
        }
        assert c.getHexagon(new Vector2i(28,50)).getValue() == 1;    
    }
    
}

