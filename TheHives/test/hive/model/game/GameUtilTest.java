/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.game;

import hive.model.GameProgress;
import hive.model.game.rules.GameStatus;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Thomas
 */
public class GameUtilTest
{
    
    public GameUtilTest()
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
    public void test()
    {
        int K = 30, n = 100;
        for(int k = 0; k < K; ++k)
        {
            Game game = GameUtil.getRandomGame(n);
            assert game.rules.getStatus(game.state) == GameStatus.CONTINUES ? game.state.data.trace.size() == n : game.state.data.trace.size() <= n;
        }
    }
}
