/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model;

import hive.model.game.Game;
import hive.model.game.GameState;
import hive.model.game.undoaction.ActionDisapplier;
import hive.model.game.doaction.ActionApplier;
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
    
    ActionApplier applier;
    ActionDisapplier disapplier;
    
    public GameProgress(Game game)
    {
        this.game = game;
        
        this.applier = new ActionApplier(game.state);
        this.disapplier = new ActionDisapplier(game.state);
    }
    
    public void doAction()
    {
        Decision decision = game.state.turn.getCurrent().decision;
        Action action = decision.getAction(game);
        action.accept(applier);
    }
    
    public void undoAction()
    {
        Action action = game.state.data.trace.peek();
        action.accept(disapplier);
    }
}
