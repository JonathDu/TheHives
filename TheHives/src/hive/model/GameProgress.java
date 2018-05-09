/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model;

import hive.model.board.Board;
import hive.model.players.Action;
import hive.model.players.ActionType;
import hive.model.players.Decision;
import hive.model.players.Players;

/**
 *
 * @author Thomas
 */
public class GameProgress
{
    PlayerTurn turn;
    GameState state;
    ActionApplier applier;
    ActionDisapplier disapplier;
    AlgorithmsDataUpdater updater;
    ActionsTrace trace;
    
    
    public GameProgress(Board board, Players players, ActionsTrace trace)
    {
        this.turn = new PlayerTurn(players);
        this.state = new GameState(board, players, turn.next(), AlgorithmsData.getFrom(board));
        this.applier = new ActionApplier();
        this.disapplier = new ActionDisapplier();
        //this.updater = new AlgorithmsDataUpdater();
        this.trace = trace;
    }
    
    // TODO
    void playAction(ActionType type)
    {
        Decision decision = state.current.decisions.get(type);
        Action action = decision.getAction(state);
        
        action.accept(applier);
        action.accept(updater);
        
        trace.push(action);
    }
}
