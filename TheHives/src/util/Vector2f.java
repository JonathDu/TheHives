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
public class Vector2f extends Vector2<Float>
{
    public Vector2f()
    {
        this(0, 0);
    }
    
    public Vector2f(float x, float y)
    {
        this(new Float(x), new Float(y));
    }
    
    public Vector2f(Float x, Float y)
    {
        super(x, y);
    }
    
    public Vector2f(Vector2f v)
    {
        super(v.x, v.y);
    }
    
    @Override
    public Vector2f add(Vector2<Float> v)
    {
        x += v.x;
        y += v.y;
        return this;
    }
    
    @Override
    public Vector2f opposite()
    {
        x = -x;
        y = -y;
        return this;
    }
    
    @Override
    public Vector2f multiply(int coef)
    {
        this.x *= coef;
        this.y *= coef;
        return this;
    }
}
