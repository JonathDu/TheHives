/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.players.decisions;

import hive.model.game.Game;
import hive.model.players.actions.Action;

/**
 *
 * @author Thomas
 */
public class SimulatedDecision implements Decision
{
    Action action;
    
    @Override
    public Action getAction(Game game)
    {
        return action;
    }
    
    public void setAction(Action action)
    {
        this.action = action;
    }
}
