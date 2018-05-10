/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.updates.doAction;

import hive.model.game.GameState;
import hive.model.players.actions.ActionVisitor;
import hive.model.players.actions.MoveAction;
import hive.model.players.actions.NoAction;
import hive.model.players.actions.PutAction;

/**
 *
 * @author Thomas
 */
public class ActionApplier implements ActionVisitor
{
    BoardDoUpdater board_do;
    PlayerDoUpdater player_do;
    AlgorithmsDataDoUpdater algo_do;
    TraceDoUpdater trace_do;

    public ActionApplier(GameState state)
    {
        this.board_do = new BoardDoUpdater(state.board);
        this.player_do = new PlayerDoUpdater(state.turn);
        this.algo_do = new AlgorithmsDataDoUpdater(state.data);
        this.trace_do = new TraceDoUpdater(state.trace);
    }
    
    @Override
    public void visit(PutAction action)
    {
        action.accept(board_do);
        action.accept(player_do);
        action.accept(algo_do);
        action.accept(trace_do);
    }

    @Override
    public void visit(MoveAction action)
    {
        action.accept(board_do);
        action.accept(player_do);
        action.accept(algo_do);
        action.accept(trace_do);
    }

    @Override
    public void visit(NoAction action)
    {
        action.accept(board_do);
        action.accept(player_do);
        action.accept(algo_do);
        action.accept(trace_do);
    }
}
