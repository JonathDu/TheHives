/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Matrix
 *
 * @author Thomas
 * @param <E>
 */
public class Matrix<E> implements Serializable
{
    public Object[][] data; // public for xml serialization
    
    public Matrix() {} // for serialization

    public Matrix(int sizeX, int sizeY)
    {
        assert sizeX > 0;
        assert sizeY > 0;
        this.data = new Object[sizeY][sizeX];
    }

    public Matrix(E[][] data)
    {
        assert data.length > 0;
        assert data[0].length > 0;
        this.data = data;
    }

    public Matrix(Matrix m)
    {
        this.data = new Object[m.sizeY()][m.sizeX()];
        for (int i = 0; i < data.length; i++)
        {
            this.data[i] = m.data[i].clone();
        }
    }

    public void setAll(Factory<E> factory)
    {
        for (int y = 0; y < sizeY(); y++)
        {
            for (int x = 0; x < sizeX(); x++)
            {
                data[y][x] = factory.create();
            }
        }
    }
    
    public void setAll(MatrixElementFactory<E> factory)
    {
        for (int y = 0; y < sizeY(); y++)
        {
            for (int x = 0; x < sizeX(); x++)
            {
                data[y][x] = factory.create(x, y);
            }
        }
    }

    public E getAt(int x, int y)
    {
        return (E) data[y][x];
    }

    public Matrix<E> setAt(int x, int y, E e)
    {
        assert x >= 0 && x < sizeX();
        assert y >= 0 && y < sizeY();
        data[y][x] = (Object) e;
        return this;
    }

    public E getAt(Vector2i p)
    {
        return getAt(p.x, p.y);
    }

    public Matrix<E> setAt(Vector2i p, E e)
    {
        return setAt(p.x, p.y, e);
    }

    public int sizeX()
    {
        return data[0].length;
    }

    public int sizeY()
    {
        return data.length;
    }

    public Vector2i getDimensions()
    {
        return new Vector2i(sizeX(), sizeY());
    }
    
    public Object[][] getData()
    {
        return data;
    }

    @Override
    public boolean equals(Object other)
    {
        if (other == null)
        {
            return false;
        }
        if (other == this)
        {
            return true;
        }
        if (!(other instanceof Matrix))
        {
            return false;
        }
        Matrix otherMyClass = (Matrix) other;
        for (int y = 0; y < sizeY(); y++)
        {
            for (int x = 0; x < sizeX(); x++)
            {
                if (!otherMyClass.data[y][x].equals(data[y][x]))
                {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString()
    {
        String str = "";
        for(Object[] line : data)
        {
            for(Object e : line)
                str += e.toString() + " ";
            str += "\n";
        }
        return str;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 41 * hash + Arrays.deepHashCode(this.data);
        return hash;
    }

}
