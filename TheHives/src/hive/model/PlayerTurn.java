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
    public PlayerTurn(Players players)
    {
        super(players);
    }
}
