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

/**
 *
 * @author Thomas
 */
public class Vector2fTest
{
    Vector2f v;
    Vector2f w;
    Vector2f v_copy;
    Vector2f v_opp;
    Vector2f v_add_w;
    Vector2f v_sub_w;
    Vector2f v_mult_w;
    
    public Vector2fTest()
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
        v = new Vector2f(3, -2);
        w = new Vector2f(-7, -9); 
        v_opp = new Vector2f(-3, 2);
        v_add_w = new Vector2f(-4, -11);
        v_sub_w = new Vector2f(10, 7);
        v_mult_w = new Vector2f(-21, 18);
    }
    
    @After
    public void tearDown()
    {
    }
    
    @Test
    public void test()
    {
        assert v.equals(v);
        
        Vector2f v_copy = new Vector2f(v);
        assert v.equals(v_copy);
        assert v.hashCode() == v_copy.hashCode();
        
        assert new Vector2f(v).opposite().equals(v_opp);
        assert new Vector2f(v).add(w).equals(v_add_w);
        assert new Vector2f(v).substract(w).equals(v_sub_w);
        assert new Vector2f(v).multiply(w).equals(v_mult_w);
        
        // -v = (-1) * v
        assert new Vector2f(v).opposite().equals(new Vector2f(v).multiply(-1));
        
        // v + w  = w + v
        assert new Vector2f(v).add(w).equals(new Vector2f(w).add(v));
        
        // v * w = w * v
        assert new Vector2f(v).multiply(w).equals(new Vector2f(w).multiply(v));
        
        // v - v = (0, 0)
        assert new Vector2f(v).substract(v).equals(new Vector2f(0, 0));
        
        // v + v = 2 * v
        assert new Vector2f(v).add(v).equals(new Vector2f(v).multiply(2));
        
        // v + (-v) = 0
        assert new Vector2f(v).add(new Vector2f(v).opposite()).equals(new Vector2f(0, 0));
        
        // 3 * v = (3, 3) * v
        assert new Vector2f(v).multiply(3).equals(new Vector2f(v).multiply(new Vector2f(3, 3)));
    }
}
