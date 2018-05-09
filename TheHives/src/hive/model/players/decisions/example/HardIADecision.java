/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.players.decisions.example;

import hive.model.GameState;
import hive.model.players.Action;

/**
 *
 * @author Thomas
 */
public class HardIADecision implements IADecision {

    @Override
    public Action getAction(GameState state)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void accept(IADecisionVisitor visitor)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}