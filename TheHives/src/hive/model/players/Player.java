/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.players;

import hive.model.players.decisions.Decision;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Thomas
 */
public class Player implements Serializable
{
    public TeamColor color;
    public Decision decision;
    public PlayerCollection collection;
    
    public Player() {} // for serialization
    
    public Player(TeamColor color, Decision decision, PlayerCollection collection)
    {
        this.color = color;
        this.decision = decision;
        this.collection = collection;
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 73 * hash + Objects.hashCode(this.color);
        hash = 73 * hash + Objects.hashCode(this.decision);
        hash = 73 * hash + Objects.hashCode(this.collection);
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
        final Player other = (Player) obj;
        if (this.color != other.color)
        {
            return false;
        }
        if (!Objects.equals(this.decision, other.decision))
        {
            return false;
        }
        if (!Objects.equals(this.collection, other.collection))
        {
            return false;
        }
        return true;
    }
}
