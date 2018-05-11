/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.game.rules;

import hive.model.game.GameState;
import hive.model.insects.InsectsBehaviors;

/**
 *
 * @author Thomas
 */
public interface Rules
{
    public PutRules getPutRules();
    public InsectsBehaviors getInsectsBehaviors();
    public GameStatus getStatus(GameState state);
}
