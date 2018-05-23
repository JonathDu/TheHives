/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.game.undoaction;

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
    PlayerUndoUpdater player_undo;
    UtilDataBeforeUndoUpdater data_before_undo;
    BoardUndoUpdater board_undo;
    UtilDataAfterUndoUpdater data_after_undo;
    
    public ActionDisapplier(GameState state)
    {
        this.player_undo = new PlayerUndoUpdater(state.turn);
        this.data_before_undo = new UtilDataBeforeUndoUpdater(state.data);
        this.board_undo = new BoardUndoUpdater(state.board);
        this.data_after_undo = new UtilDataAfterUndoUpdater(state.data);
    }

    @Override
    public void visit(PutAction action)
    {
        action.accept(player_undo);
        action.accept(data_before_undo);
        action.accept(board_undo);
        action.accept(data_after_undo);
    }

    @Override
    public void visit(MoveAction action)
    {
        action.accept(player_undo);
        action.accept(data_before_undo);
        action.accept(board_undo);
        action.accept(data_after_undo);
    }

    @Override
    public void visit(NoAction action)
    {
        action.accept(player_undo);
        action.accept(data_before_undo);
        action.accept(board_undo);
        action.accept(data_after_undo);
    }
}
