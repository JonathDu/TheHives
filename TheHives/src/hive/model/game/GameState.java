/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.game;

import hive.model.game.utildata.UtilData;
import hive.model.board.Board;
import hive.model.players.Player;
import hive.model.players.Players;
import java.io.Serializable;

/**
 *
 * @author Thomas
 */
public class GameState implements Serializable
{
    public Board board;
    public Players players;
    public PlayerTurn turn;
    
    public UtilData data;
    
    public GameState() {} // for serialization
    
    public GameState(Board board, Players players, PlayerTurn turn, UtilData data)
    {
        this.board = board;
        this.players = players;
        this.turn = turn;
        
        this.data = data;
    }
}
