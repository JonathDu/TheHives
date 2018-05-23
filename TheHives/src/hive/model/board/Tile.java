/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.board;

import hive.model.players.TeamColor;
import hive.model.insects.InsectType;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Thomas
 */
public class Tile implements Serializable
{
    public InsectType type;
    public TeamColor color;
    
    public Tile() {} // for serialization
    
    public Tile(InsectType type, TeamColor color)
    {
        this.type = type;
        this.color = color;
    }
    
    @Override
    public String toString()
    {
        return "" + type + " " + color;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.type);
        hash = 17 * hash + Objects.hashCode(this.color);
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
        final Tile other = (Tile) obj;
        if (this.type != other.type)
        {
            return false;
        }
        if (this.color != other.color)
        {
            return false;
        }
        return true;
    }
}
