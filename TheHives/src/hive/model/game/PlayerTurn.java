/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.game;

import util.iterators.CircularListIterator;
import hive.model.players.Player;
import hive.model.players.Players;
import java.io.Serializable;
import java.util.ListIterator;
import java.util.Objects;

/**
 *
 * @author Thomas
 */

public class PlayerTurn implements ListIterator<Player>, Serializable
{
    public Player current;
    public Player opponent;
    
    public PlayerTurn() {} // for serialization
    
    public PlayerTurn(Players players)
    {
        current = players.get(0);
        opponent = players.get(1);
    }
    
    public Player getCurrent()
    {
        return current;
    }
    
    public Player getOpponent()
    {
        return opponent;
    }
    
    @Override
    public boolean hasNext()
    {
        return true;
    }
    
    @Override
    public Player next()
    {
        assert hasNext();
        swapPlayers();
        return current;
    }
    
    @Override
    public boolean hasPrevious()
    {
        return true;
    }
    
    @Override
    public Player previous()
    {
        assert hasPrevious();
        swapPlayers();
        return current;
    }
    
    private void swapPlayers()
    {
        Player tmp = current;
        current = opponent;
        opponent = tmp;
    }

    @Override
    public int nextIndex()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int previousIndex()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void set(Player e)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(Player e)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.current);
        hash = 13 * hash + Objects.hashCode(this.opponent);
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
        final PlayerTurn other = (PlayerTurn) obj;
        if (!Objects.equals(this.current, other.current))
        {
            return false;
        }
        if (!Objects.equals(this.opponent, other.opponent))
        {
            return false;
        }
        return true;
    }
}

class PlayerTurnMultiplePlayers extends CircularListIterator<Player>
{
    Player current;
    
    public PlayerTurnMultiplePlayers(Players players)
    {
        super(players);
        assert hasNext();
        current = super.next();
    }
    
    public Player getCurrent()
    {
        return current;
    }
    
    @Override
    public Player next()
    {
        current = super.next();
        return current;
    }
    
    @Override
    public Player previous()
    {
        super.previous();
        super.previous();
        current = super.next();
        return current;
    }
}
