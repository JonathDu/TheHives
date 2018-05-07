/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model;

import hive.model.board.Board;
import hive.model.players.Action;
import hive.model.players.ActionVisitor;
import hive.model.players.ActionType;
import hive.model.players.Decision;
import hive.model.players.Player;
import hive.model.players.Players;

/**
 *
 * @author Thomas
 */
public class GameProgress
{
    Board board;
    Players players;
    PlayerTurn turn;
    Player current;
    ActionVisitor applier;
    
    public GameProgress(Board board, Players players)
    {
        this.board = board;
        this.players = players;
        this.turn = new PlayerTurn(players);
        this.current = turn.next();
        this.applier = new ActionApplier();
    }
    
    // TODO
    void playAction(ActionType type)
    {
        Decision decision = current.decisions.get(type);
        Action action = decision.getAction(board);
        action.accept(applier);
    }
}
