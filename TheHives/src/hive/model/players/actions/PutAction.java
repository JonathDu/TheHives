/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.players.actions;

import hive.model.board.Tile;
import hive.model.board.Cell;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Thomas
 */
public class PutAction implements Action, Serializable
{
    public Cell where;
    public Tile tile;
    
    public PutAction() {}
    
    public PutAction(Cell where, Tile tile)
    {
        this.where = where;
        this.tile = tile;
    }
    
    @Override
    public void accept(ActionVisitor visitor)
    {
        visitor.visit(this);
    }
    
    @Override
    public String toString()
    {
        return "(" + tile + " put at " + where + ")";
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.where);
        hash = 59 * hash + Objects.hashCode(this.tile);
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final PutAction other = (PutAction) obj;
        if (!Objects.equals(this.where, other.where))
        {
            return false;
        }
        if (!Objects.equals(this.tile, other.tile))
        {
            return false;
        }
        return true;
    }
}
