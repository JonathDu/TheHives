/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.players.decisions;

import hive.model.GameState;
import hive.model.players.Action;
import hive.model.players.Decision;

/**
 *
 * @author Thomas
 */
public class HumanDecision implements Decision
{
    Action action;
    
    @Override
    public Action getAction(GameState state)
    {
        return action;
    }
    
    public void setAction(Action action)
    {
        this.action = action;
    }
}
