/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.Serializable;
import java.util.Objects;

/**
 * Vector (x, y) (2 dimensions)
 * @author lucas
 * @param <T>
 */
public abstract class Vector2<T> implements Serializable
{
    public T x;
    public T y;
    
    public Vector2() {} // for serialization
    
    public Vector2(T x, T y)
    {
        this.x = x;
        this.y = y;
    }
    
    public abstract Vector2<T> opposite();
    public abstract Vector2<T> add(Vector2<T> v);
    public abstract Vector2<T> substract(Vector2<T> v);
    public abstract Vector2<T> multiply(Vector2<T> v);
    public abstract Vector2<T> multiply(int coef);
    
    
    @Override
    public String toString()
    {
        return "(" + x + ", " + y + ")";
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
        return otherMyClass.x.equals(x) && otherMyClass.y.equals(y);
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.x);
        hash = 89 * hash + Objects.hashCode(this.y);
        return hash;
    }
}
