/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.game;

import hive.model.insects.InsectsBehaviors;

/**
 *
 * @author Thomas
 */
public interface Rules
{
    public InsectsBehaviors getInsectsBehaviors();
    public GameStatus getStatus(GameState state);
    public int getMaxQueenTurn();
}
