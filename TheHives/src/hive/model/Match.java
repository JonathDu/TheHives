/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model;

import hive.model.game.Game;
import hive.model.players.PlayersData;

/**
 *
 * @author Thomas
 */
public class Match
{
    public Game game;
    public PlayersData data;
    
    public Match() // for serialization
    {
        
    }
    
    public Match(Game game, PlayersData data)
    {
        this.game = game;
        this.data = data;
    }
}
