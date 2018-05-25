/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.game.doaction;

import hive.model.game.GameState;
import hive.model.players.actions.ActionVisitor;
import hive.model.players.actions.MoveAction;
import hive.model.players.actions.NoAction;
import hive.model.players.actions.PutAction;

/**
 *
 * @author Thomas
 */
public class DoUpdater implements ActionVisitor
{
    PlayerDoUpdater player_do;
    UtilDataBeforeDoUpdater data_before_do;
    BoardDoUpdater board_do;
    UtilDataAfterDoUpdater data_after_do;
    TracesDoUpdater traces_do;

    public DoUpdater(GameState state)
    {
        this.player_do = new PlayerDoUpdater(state.turn);
        this.data_before_do = new UtilDataBeforeDoUpdater(state.data);
        this.board_do = new BoardDoUpdater(state.board);
        this.data_after_do = new UtilDataAfterDoUpdater(state.data);
        this.traces_do = new TracesDoUpdater(state.data);
    }
    
    @Override
    public void visit(PutAction action)
    {
        action.accept(player_do);
        action.accept(data_before_do);
        action.accept(board_do);
        action.accept(data_after_do);
        action.accept(traces_do);
    }

    @Override
    public void visit(MoveAction action)
    {
        action.accept(player_do);
        action.accept(data_before_do);
        action.accept(board_do);
        action.accept(data_after_do);
        action.accept(traces_do);
    }

    @Override
    public void visit(NoAction action)
    {
        action.accept(player_do);
        action.accept(data_before_do);
        action.accept(board_do);
        action.accept(data_after_do);
        action.accept(traces_do);
    }
}
