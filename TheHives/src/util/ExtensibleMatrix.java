/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.ArrayList;

/**
 *
 * @author Thomas
 * @param <E>
 */
public class ExtensibleMatrix<E>
{
    ArrayList<ArrayList<E>> data;
    Cloner<E> cloner;
    
    public ExtensibleMatrix(Cloner<E> cloner)
    {
        this.cloner = cloner;
    }
    
    public int sizeX()
    {
        return data.get(0).size();
    }
    
    public int sizeY()
    {
        return data.size();
    }
    
    public void pushBackX(E e)
    {
        for(ArrayList<E> x_line : data)
        {
            x_line.add(cloner.clone(e));
        }
    }
    
    public void pushBackY(E e)
    {
        ArrayList<E> x_line = new ArrayList<>(sizeX());
        for(E at_x : x_line)
        {
            at_x = cloner.clone(e);
        }
        data.add(x_line);
    }
    
    public void popBackX()
    {
        int last_idx = sizeY() - 1;
        for(ArrayList<E> x_line : data)
        {
            x_line.remove(last_idx);
        }
    }
    
    public void popBackY()
    {
        data.remove(sizeY() - 1);
    }
    
    public void pushFrontX(E e)
    {
        for(ArrayList<E> x_line : data)
        {
            x_line.add(0, cloner.clone(e));
        }
    }
    
    public void pushFrontY(E e)
    {
        ArrayList<E> x_line = new ArrayList<>(sizeX());
        for(E at_x : x_line)
        {
            at_x = cloner.clone(e);
        }
        data.add(0, x_line);
    }
    
    public void popFrontX()
    {
        for(ArrayList<E> x_line : data)
        {
            x_line.remove(0);
        }
    }
    
    public void popFrontY()
    {
        data.remove(0);
    }
    
    public E getAt(int x, int y)
    {
        return data.get(x).get(y);
    }
    
    public E setAt(int x, int y, E e)
    {
        return data.get(x).set(y, e);
    }
}
