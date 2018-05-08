/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.hexagons;

import util.Vector2i;

/**
 *
 * @author lucas
 */
public class CircularPositionMaker
{
    Vector2i dim;
    
    public CircularPositionMaker(Vector2i dim)
    {
        this.dim = dim;
    }
    
    public Vector2i circular(Vector2i p)
    {
        p.x = p.x % dim.x;
        p.y = p.y % dim.y;
        if(p.x < 0)
            p.x += dim.x;
        if(p.y < 0)
            p.y += dim.y;
        
        assert p.x >= 0;
        assert p.y >= 0;
        assert p.x < dim.x;
        assert p.y < dim.y;
        
        return p;
    }
}
