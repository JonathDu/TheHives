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
import java.util.Objects;

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

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.board);
        hash = 67 * hash + Objects.hashCode(this.players);
        hash = 67 * hash + Objects.hashCode(this.turn);
        hash = 67 * hash + Objects.hashCode(this.data);
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
        final GameState other = (GameState) obj;
        if (!Objects.equals(this.board, other.board))
        {
            return false;
        }
        if (!Objects.equals(this.players, other.players))
        {
            return false;
        }
        if (!Objects.equals(this.turn, other.turn))
        {
            return false;
        }
        if (!Objects.equals(this.data, other.data))
        {
            return false;
        }
        return true;
    }
}
