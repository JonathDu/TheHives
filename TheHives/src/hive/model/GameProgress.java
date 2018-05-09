/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model;

import hive.model.updates.ActionDisapplier;
import hive.model.updates.ActionApplier;
import hive.model.players.Action;
import hive.model.players.Decision;

/**
 *
 * @author Thomas
 */
public class GameProgress
{
    GameState state;
    
    ActionApplier applier;
    ActionDisapplier disapplier;
    
    
    public GameProgress(GameState state)
    {
        this.state = state;
        
        this.applier = new ActionApplier(state);
        this.disapplier = new ActionDisapplier(state);
    }
    
    void doAction()
    {
        Decision decision = state.turn.getCurrent().decision;
        Action action = decision.getAction(state);
        action.accept(applier);
    }
    
    void undoAction()
    {
        // unapply stack TODO
        // Action action = new Action();
        // action.accept(disapplier);
    }
}
