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
import hive.model.players.Players;

/**
 *
 * @author Thomas
 */
public class GameProgress
{
    GameState state;
    PlayerTurn turn;
    ActionVisitor applier;
    ActionsTrace trace;
    
    
    public GameProgress(Board board, Players players, ActionsTrace trace)
    {
        this.turn = new PlayerTurn(state.players);
        this.state = new GameState(board, players, turn.next());
        this.applier = new ActionApplier();
        this.trace = trace;
    }
    
    // TODO
    void playAction(ActionType type)
    {
        Decision decision = state.current.decisions.get(type);
        Action action = decision.getAction(state);
        action.accept(applier);
        trace.push(action);
    }
}
