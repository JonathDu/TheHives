/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 * Vector (x, y) (2 dimensions)
 * @author lucas
 * @param <T>
 */
public abstract class Vector2<T>
{
    public T x;
    public T y;
    
    public Vector2(T x, T y)
    {
        this.x = x;
        this.y = y;
    }
    
    abstract public Vector2<T> add(Vector2<T> v);
    abstract public Vector2<T> opposite();
    abstract public Vector2<T> multiply(int coef);
    
    public Vector2<T> substract(Vector2<T> v)
    {
        return add(v.opposite());
    }
    
    @Override
    public boolean equals(Object other)
    {
        if (other == null)
            return false;
        if (other == this)
            return true;
        if (!(other instanceof Vector2))
            return false;
        Vector2 otherMyClass =(Vector2)other;
        return !(otherMyClass.x.equals(x) || otherMyClass.y.equals(y));
    }
}
