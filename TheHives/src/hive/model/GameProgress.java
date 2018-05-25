/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model;

import hive.model.game.Game;
import hive.model.game.GameState;
import hive.model.game.undoaction.UndoUpdater;
import hive.model.game.doaction.DoUpdater;
import hive.model.game.redoaction.RedoUpdater;
import hive.model.game.rules.GameStatus;
import hive.model.players.actions.Action;
import hive.model.players.decisions.Decision;
import java.util.ArrayList;

/**
 *
 * @author Thomas
 */
public class GameProgress
{
    public final Game game;
    
    DoUpdater do_updater;
    UndoUpdater undo_updater;
    RedoUpdater redo_updater;
    
    public GameProgress(Game game)
    {
        this.game = game;
        
        this.do_updater = new DoUpdater(game.state);
        this.undo_updater = new UndoUpdater(game.state);
        this.redo_updater = new RedoUpdater(game.state);
    }
    
    public void doAction()
    {
        Decision decision = game.state.turn.getCurrent().decision;
        Action action = decision.getAction(game);
        action.accept(do_updater);
    }
    
    public void undoAction()
    {
        Action action = game.state.data.trace.peek();
        action.accept(undo_updater);
    }
    
    public void redoAction()
    {
        Action action = game.state.data.undos.peek();
        action.accept(redo_updater);
    }
    
    public GameStatus getStatus()
    {
        return game.rules.getStatus(game.state);
    }
    
    public boolean continues()
    {
        return getStatus() == GameStatus.CONTINUES;
    }
}
