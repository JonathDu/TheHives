/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.hexagons;

/**
 * 
 * @author Thomas
 * @param <E>
 */
public class Hexagon<E>
{
    E value;
    Hexagon<E> a, b, c, d, e, f;
    
    public Hexagon()
    {
        
    }
    
    public Hexagon(E e)
    {
        this.value = e;
    }

    public E getValue()
    {
        return value;
    }
    
    public void setValue(E value)
    {
        this.value = value;
    }

    public Hexagon<E> getNeighbor(HexagonSide side)
    {
        switch(side)
        {
        case A: return a;
        case B: return b;
        case C: return c;
        case D: return d;
        case E: return e;
        case F: return f;
        }
        return null;
    }
    
    public void setNeighbor(HexagonSide side, Hexagon<E> h)
    {
        switch(side)
        {
        case A: a = h; break;
        case B: b = h; break;
        case C: c = h; break;
        case D: d = h; break;
        case E: e = h; break;
        case F: f = h; break;
        }
    }
}