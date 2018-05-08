/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model;

import hive.model.board.Board;
import hive.model.players.Player;
import hive.model.players.Players;

/**
 *
 * @author Thomas
 */
public class GameState
{
    public Board board;
    public Players players;
    public Player current;
    
    public GameState(Board board, Players players, Player current)
    {
        this.board = board;
        this.players = players;
        this.current = current;
    }
}
