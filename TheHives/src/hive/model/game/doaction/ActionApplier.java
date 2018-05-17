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
public class ActionApplier implements ActionVisitor
{
    PlayerDoUpdater player_do;
    PrecalculatedDataDoUpdater algo_do;
    BoardDoUpdater board_do;

    public ActionApplier(GameState state)
    {
        this.player_do = new PlayerDoUpdater(state.turn);
        this.algo_do = new PrecalculatedDataDoUpdater(state.data);
        this.board_do = new BoardDoUpdater(state.board);
    }
    
    @Override
    public void visit(PutAction action)
    {
        action.accept(player_do);
        action.accept(algo_do);
        action.accept(board_do);
    }

    @Override
    public void visit(MoveAction action)
    {
        action.accept(player_do);
        action.accept(algo_do);
        action.accept(board_do);
    }

    @Override
    public void visit(NoAction action)
    {
        action.accept(player_do);
        action.accept(algo_do);
        action.accept(board_do);
    }
}
