/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.updates.undoAction;

import hive.model.game.GameState;
import hive.model.players.actions.ActionVisitor;
import hive.model.players.actions.MoveAction;
import hive.model.players.actions.NoAction;
import hive.model.players.actions.PutAction;

/**
 *
 * @author Thomas
 */
public class ActionDisapplier implements ActionVisitor
{
    BoardUndoUpdater board_undo;
    PlayerUndoUpdater player_undo;
    AlgorithmsDataUndoUpdater algo_undo;
    TraceUndoUpdater trace_undo;
    
    public ActionDisapplier(GameState state)
    {
        this.board_undo = new BoardUndoUpdater(state.board);
        this.player_undo = new PlayerUndoUpdater(state.turn);
        this.algo_undo = new AlgorithmsDataUndoUpdater(state.data);
        this.trace_undo = new TraceUndoUpdater(state.trace);
    }

    @Override
    public void visit(PutAction action)
    {
        action.accept(board_undo);
        action.accept(player_undo);
        action.accept(algo_undo);
        action.accept(trace_undo);
    }

    @Override
    public void visit(MoveAction action)
    {
        action.accept(board_undo);
        action.accept(player_undo);
        action.accept(algo_undo);
        action.accept(trace_undo);
    }

    @Override
    public void visit(NoAction action)
    {
        action.accept(board_undo);
        action.accept(player_undo);
        action.accept(algo_undo);
        action.accept(trace_undo);
    }
}
