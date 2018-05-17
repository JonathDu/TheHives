/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.players.decisions;

import hive.model.game.Game;
import hive.model.players.actions.Action;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Coralie
 */
public class IADecisionTest {
    
    public IADecisionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of changeQI method, of class IADecision.
     */
    /*
    @Test
    public void testChangeQI() {
        System.out.println("changeQI");
        Level qI = null;
        IADecision instance = null;
        instance.changeQI(qI);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */
    /**
     * Test of getAction method, of class IADecision.
     */
    @Test
    public void testGetActionHard1() {
        System.out.println("getActionHard1");
        Game state = null;
        IADecision instance = new IADecision(Level.HARD);
        Action expResult = null;
        Action result = instance.getAction(state);
        assertEquals(expResult, result);
    }
    
}
