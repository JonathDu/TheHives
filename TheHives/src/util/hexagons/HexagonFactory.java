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
 * @param <E>
 */
public class HexagonFactory<E> implements Factory<Hexagon<E>>
{
    Factory<E> factory;
    
    public HexagonFactory(Factory<E> factory)
    {
        this.factory = factory;
    }
    
    @Override
    public Hexagon<E> create()
    {
        return new Hexagon<>(factory.create());
    }
}
