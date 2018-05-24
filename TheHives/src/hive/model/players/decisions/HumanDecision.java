/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.players.decisions;

import hive.model.game.Game;
import hive.model.players.actions.Action;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Thomas
 */
public class HumanDecision implements Decision, Serializable
{
    public Action action;
    
    public HumanDecision() {} // for serialization
    
    @Override
    public Action getAction(Game game)
    {
        return action;
    }
    
    public void setAction(Action action)
    {
        this.action = action;
    }
    
    @Override
    public int hashCode()
    {
        int hash = 7;
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
        return true;
    }
}
