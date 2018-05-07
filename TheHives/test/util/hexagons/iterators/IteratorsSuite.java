/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.hexagons.iterators;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author lucas
 */
@RunWith(Suite.class)
@Suite.SuiteClasses(
{
    util.hexagons.iterators.BreadthIteratorTest.class, util.hexagons.iterators.LineAtSideIteratorTest.class, util.hexagons.iterators.BorderIteratorTest.class, util.hexagons.iterators.NeighborsIteratorTest.class
})
public class IteratorsSuite
{

    @BeforeClass
    public static void setUpClass() throws Exception
    {
    }

    @AfterClass
    public static void tearDownClass() throws Exception
    {
    }

    @Before
    public void setUp() throws Exception
    {
    }

    @After
    public void tearDown() throws Exception
    {
    }
    
}
