/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.game;

import hive.model.board.Board;
import hive.model.board.TilePositions;
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
    public PlayerTurn turn;
    
    public ActionsTrace trace;
    public AlgorithmsData data;
    
    public GameState(Board board, Players players, PlayerTurn turn, ActionsTrace trace, AlgorithmsData data)
    {
        this.board = board;
        this.players = players;
        this.turn = turn;
        
        this.trace = trace;
        this.data = data;
    }
}
