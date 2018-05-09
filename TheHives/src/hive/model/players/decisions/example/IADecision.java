/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.players.decisions.example;

import hive.model.players.Decision;

/**
 *
 * @author Thomas
 */
public interface IADecision extends Decision
{
    public void accept(IADecisionVisitor visitor);
}
