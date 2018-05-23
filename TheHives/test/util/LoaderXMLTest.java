/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import hive.model.game.DefaultGame;
import hive.model.game.Game;
import hive.model.players.decisions.HumanDecision;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Thomas
 */
public class LoaderXMLTest
{
    ArrayList<Integer> list;
    
    public LoaderXMLTest()
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
        list = new ArrayList<>();
        list.add(3);
        list.add(7);
        list.add(9);
    }
    
    @After
    public void tearDown()
    {
    }

    /**
     * Test of loadInFile method, of class LoaderXML.
     */
    @Test
    public void test() throws IOException
    {
        LoaderXML<ArrayList<Integer>> loader = new LoaderXML();
        
        loader.loadInFile(list, "LoaderXMLTest.xml");
        ArrayList<Integer> res = loader.loadFromFile("LoaderXMLTest.xml");
        
        assert res.equals(list);
    }

    
}
