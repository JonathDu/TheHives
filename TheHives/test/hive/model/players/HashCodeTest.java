/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.players;

import hive.model.players.decisions.Decision;
import hive.model.players.decisions.HumanDecision;
import hive.model.players.decisions.IA.Level;
import hive.model.players.decisions.IADecision;
import hive.model.players.decisions.RandomDecision;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import testutil.RandomUtil;

/**
 *
 * @author Thomas
 */
public class HashCodeTest
{
    ArrayList<Decision> d1, d2;
    public HashCodeTest()
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
        d1 = new ArrayList<>();
        d2 = new ArrayList<>();
    }
    
    @After
    public void tearDown()
    {
    }
    
    @Test
    public void testDecision()
    {
        add(new HumanDecision());
        add(new RandomDecision());
        add(new IADecision(Level.EASY));
        add(new IADecision(Level.MEDIUM));
        add(new IADecision(Level.HARD));
        
        assert d1.equals(d2);
        for(int i = 0; i < d1.size(); ++i)
        {
            for(int j = 0; j < d2.size(); ++j)
            {
                if(i != j)
                {
                    assert !d1.get(i).equals(d2.get(j));
                }
                else
                {
                    assert d1.get(i).equals(d2.get(j));
                    assert d1.get(i).hashCode() == d2.get(j).hashCode();
                }
            }
        }
    }
    
    void add(Decision decision)
    {
        d1.add(decision);
        d2.add(decision);
    }
}
