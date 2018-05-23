/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

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
public class Vector2iTest
{
    Vector2i v;
    Vector2i w;
    Vector2i v_copy;
    Vector2i v_opp;
    Vector2i v_add_w;
    Vector2i v_sub_w;
    Vector2i v_mult_w;
    
    public Vector2iTest()
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
        v = new Vector2i(3, -2);
        w = new Vector2i(-7, -9); 
        v_opp = new Vector2i(-3, 2);
        v_add_w = new Vector2i(-4, -11);
        v_sub_w = new Vector2i(10, 7);
        v_mult_w = new Vector2i(-21, 18);
    }
    
    @After
    public void tearDown()
    {
    }

    @Test
    public void test()
    {
        assert v.equals(v);
        
        Vector2i v_copy = new Vector2i(v);
        assert v.equals(v_copy);
        assert v.hashCode() == v_copy.hashCode();
        
        assert new Vector2i(v).opposite().equals(v_opp);
        assert new Vector2i(v).add(w).equals(v_add_w);
        assert new Vector2i(v).substract(w).equals(v_sub_w);
        assert new Vector2i(v).multiply(w).equals(v_mult_w);
        
        // -v = (-1) * v
        assert new Vector2i(v).opposite().equals(new Vector2i(v).multiply(-1));
        
        // v + w  = w + v
        assert new Vector2i(v).add(w).equals(new Vector2i(w).add(v));
        
        // v * w = w * v
        assert new Vector2i(v).multiply(w).equals(new Vector2i(w).multiply(v));
        
        // v - v = (0, 0)
        assert new Vector2i(v).substract(v).equals(new Vector2i(0, 0));
        
        // v + v = 2 * v
        assert new Vector2i(v).add(v).equals(new Vector2i(v).multiply(2));
        
        // v + (-v) = 0
        assert new Vector2i(v).add(new Vector2i(v).opposite()).equals(new Vector2i(0, 0));
        
        // 3 * v = (3, 3) * v
        assert new Vector2i(v).multiply(3).equals(new Vector2i(v).multiply(new Vector2i(3, 3)));
    }
}
