/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.game.rules;

import hive.model.game.GameState;

/**
 *
 * @author Thomas
 */
public interface Rules extends PutRules, MoveRules
{
    public GameStatus getStatus(GameState state);
}
