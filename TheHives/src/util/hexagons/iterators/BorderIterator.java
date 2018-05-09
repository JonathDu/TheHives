/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.hexagons.iterators;

import hive.model.board.Hexagon;
import java.util.Iterator;

/**
 *
 * @author Thomas
 * @param <E>
 */
public class BorderIterator<E> implements Iterator<E>
{
    Hexagon hexagon;
    
    public BorderIterator(Hexagon hexagon)
    {
        this.hexagon = hexagon;
    }
    @Override
    public boolean hasNext()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public E next()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
