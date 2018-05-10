/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author Thomas
 */
public class Vector2i extends Vector2<Integer>
{
    public Vector2i()
    {
        this(0, 0);
    }
    
    public Vector2i(int x, int y)
    {
        this(new Integer(x), new Integer(y));
    }
    
    public Vector2i(Integer x, Integer y)
    {
        super(x, y);
    }
    
    public Vector2i(Vector2i v)
    {
        super(v.x, v.y);
    }
    
    @Override
    public Vector2i add(Vector2<Integer> v)
    {
        x += v.x;
        y += v.y;
        return this;
    }
    
    @Override
    public Vector2i opposite()
    {
        x = -x;
        y = -y;
        return this;
    }    
    
    @Override
    public Vector2i multiply(int coef)
    {
        this.x *= coef;
        this.y *= coef;
        return this;
    }
    
    public Vector2i multiply(Vector2<Integer> coefs)
    {
        this.x *= coefs.x;
        this.y *= coefs.y;
        return this;
    }
    
    @Override
    public String toString()
    {
        return "(" + x + ", " + y + ")";
    }
}
