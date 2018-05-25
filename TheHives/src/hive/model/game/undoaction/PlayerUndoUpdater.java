/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.game.undoaction;

import hive.model.game.PlayerTurn;
import hive.model.players.Player;
import hive.model.players.actions.ActionVisitor;
import hive.model.players.actions.MoveAction;
import hive.model.players.actions.NoAction;
import hive.model.players.actions.PutAction;

/**
 *
 * @author Thomas
 */
public class PlayerUndoUpdater implements ActionVisitor
{
    PlayerTurn turn;
    
    public PlayerUndoUpdater(PlayerTurn turn)
    {
        this.turn = turn;
    }
    
    @Override
    public void visit(PutAction action)
    {
        Player current = turn.previous();
        int n = current.collection.get(action.tile.type);
        current.collection.put(action.tile.type, n + 1);
    }

    @Override
    public void visit(MoveAction action)
    {
        turn.previous();
    }

    @Override
    public void visit(NoAction action)
    {
        turn.previous();
    }
}
