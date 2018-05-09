/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model;

import util.iterators.CircularListIterator;
import hive.model.players.Player;
import hive.model.players.Players;

/**
 *
 * @author Thomas
 */
public class PlayerTurn extends CircularListIterator<Player>
{
    Player current;
    public PlayerTurn(Players players)
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
        current = super.previous();
        return current;
    }
}
