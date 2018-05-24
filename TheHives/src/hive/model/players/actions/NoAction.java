/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.players.actions;

import java.io.Serializable;

/**
 *
 * @author Thomas
 */
public class NoAction implements Action, Serializable
{
    public NoAction() {} // for serialization
    
    @Override
    public void accept(ActionVisitor visitor)
    {
        visitor.visit(this);
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
