/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.board;

import util.Vector2i;
import util.hexagons.Hexagon;

/**
 * 
 * @author Thomas
 */
public class Honeycomb extends Hexagon<TilesStack>
{
    public Vector2i pos;
    
    public Honeycomb(Vector2i pos)
    {
        super();
        this.pos = pos;
    }
    
    public Honeycomb(Vector2i pos, TilesStack stack)
    {
        super(stack);
        this.pos = pos;
    }
    
    public TilesStack stack()
    {
        return getValue();
    }
    
    @Override
    public String toString()
    {
        return "" + pos;
    }
}
