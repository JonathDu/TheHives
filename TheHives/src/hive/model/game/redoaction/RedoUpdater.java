/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.game.redoaction;

import hive.model.game.GameState;
import hive.model.game.doaction.BoardDoUpdater;
import hive.model.game.doaction.PlayerDoUpdater;
import hive.model.game.doaction.UtilDataAfterDoUpdater;
import hive.model.game.doaction.UtilDataBeforeDoUpdater;
import hive.model.players.actions.ActionVisitor;
import hive.model.players.actions.MoveAction;
import hive.model.players.actions.NoAction;
import hive.model.players.actions.PutAction;

/**
 *
 * @author Thomas
 */
public class RedoUpdater implements ActionVisitor
{
    PlayerDoUpdater player_do;
    UtilDataBeforeDoUpdater data_before_do;
    BoardDoUpdater board_do;
    UtilDataAfterDoUpdater data_after_do;
    TracesRedoUpdater traces_redo;
    
    public RedoUpdater(GameState state)
    {
        this.player_do = new PlayerDoUpdater(state.turn);
        this.data_before_do = new UtilDataBeforeDoUpdater(state.data);
        this.board_do = new BoardDoUpdater(state.board);
        this.data_after_do = new UtilDataAfterDoUpdater(state.data);
        this.traces_redo = new TracesRedoUpdater(state.data);
    }
    
    @Override
    public void visit(PutAction action)
    {
        action.accept(player_do);
        action.accept(data_before_do);
        action.accept(board_do);
        action.accept(data_after_do);
        action.accept(traces_redo);
    }

    @Override
    public void visit(MoveAction action)
    {
        action.accept(player_do);
        action.accept(data_before_do);
        action.accept(board_do);
        action.accept(data_after_do);
        action.accept(traces_redo);
    }

    @Override
    public void visit(NoAction action)
    {
        action.accept(player_do);
        action.accept(data_before_do);
        action.accept(board_do);
        action.accept(data_after_do);
        action.accept(traces_redo);
    }
}
