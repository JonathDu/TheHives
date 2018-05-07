/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.hexagons;

import util.Factory;

/**
 *
 * @author Thomas
 */
public class Hexagons
{
    public static <E> void bind(Hexagon<E> u, HexagonSide side, Hexagon<E> v)
    {
        u.setNeighbor(side, v);
        v.setNeighbor(side.getOpposite(), u);
    }
    
    public static <E> void swap(Hexagon<E> u, Hexagon<E> v)
    {
        E uValue = u.getValue();
        E vValue = v.getValue();
        
        u.setValue(vValue);
        v.setValue(uValue);
    }
    
    public static <E> void duplicate(Hexagon<E> u, Hexagon<E> v)
    {
        v.setValue(u.getValue());
    }
    
    public static <E> void transfer(Hexagon<E> u, Hexagon<E> v)
    {
        v.setValue(u.getValue());
        u.setValue(null);
    }
    
    public static <E> void move(Hexagon<E> u, Hexagon<E> v, Factory<E> factory)
    {
        v.setValue(u.getValue());
        u.setValue(factory.create());
    }
}
