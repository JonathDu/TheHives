/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.game;

import hive.model.players.Player;
import hive.model.players.Players;
import hive.model.players.TeamColor;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Thomas
 */
public class PlayerTurnTest
{
    Players players;
    PlayerTurn start;
    
    public PlayerTurnTest()
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
        players = DefaultGame.getPlayers(null, null);
        start = new PlayerTurn(players);
    }
    
    @After
    public void tearDown()
    {
    }

    @Test
    public void test()
    {
        PlayerTurn turn = new PlayerTurn(players);
        assert turn.current == players.get(0);
        assert turn.opponent == players.get(1);
        assert turn.equals(start);
        
        assert turn.hashCode() == start.hashCode();
        
        assert turn.next() == players.get(1);
        assert turn.current == players.get(1);
        assert turn.opponent == players.get(0);
        assert !turn.equals(start);
        
        assert turn.next() == players.get(0);
        assert turn.equals(start);
        
        assert turn.previous() == players.get(1);
        assert !turn.equals(start);
        
        assert turn.previous() == players.get(0);
        assert turn.equals(start);
    }
}
