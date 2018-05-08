/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.hexagons.iterators;

import hive.model.board.HiveNeighborsShifter;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import util.Matrix;
import util.Vector2i;
import util.hexagons.CircularHexagonsGraph;
import util.hexagons.HexagonSide;

/**
 *
 * @author lucas
 */
public class LineAtSideIteratorTest
{
    
    public LineAtSideIteratorTest()
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
     * Test of next method, of class LineAtSideIterator.
     */
    @Test
    public void testNext()
    {
        Integer[][] tab = 
        {
            {1,2,3,4},
            {5,6,7,8},
            {9,10,11,12},
            {13,14,15,16}
        };
        Matrix<Integer> m = new Matrix<>(tab);
        
        HiveNeighborsShifter getter = new HiveNeighborsShifter();
        CircularHexagonsGraph<Integer> c = new CircularHexagonsGraph(m, getter);

        System.out.println(c);
        
        LineAtSideIterator<Integer> lineIterator = new LineAtSideIterator(c.getHexagon(new Vector2i(0,0)), HexagonSide.B);

        int i = 0;
        while (lineIterator.hasNext() && i++ < 50)
        {
            System.out.println(lineIterator.next().getValue());
            //assert p.test(f.next());
        }

        assert true;
    }
    
}
