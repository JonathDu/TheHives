/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.board;

import java.util.Objects;
import util.hexagons.Hexagon;

/**
 *
 * @author Thomas
 */
public class Cell
{
    public Honeycomb comb;
    public int level;
    
    public Cell(Honeycomb comb)
    {
        this.comb = comb;
        this.level = comb.value().size();
    }
    
    public Cell(Honeycomb comb, int level)
    {
        this.comb = comb;
        this.level = level;
    }
    
    public Tile getTile()
    {
        return comb.value().get(level);
    }
    
    @Override
    public String toString()
    {
        return "( " + comb + " : " + comb.value() + " at " + level + " )";
    }
    
    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.comb);
        hash = 59 * hash + this.level;
        return hash;
    }
    
    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Cell other = (Cell) obj;
        if (!Objects.equals(this.comb, other.comb))
            return false;
        if (this.level != other.level)
            return false;
        return true;
    }
    
}
