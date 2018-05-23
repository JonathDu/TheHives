/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.game;

import hive.model.game.rules.Rules;
import java.io.Serializable;

/**
 *
 * @author Thomas
 */
public class Game implements Serializable
{
    public GameState state;
    public Rules rules;
    
    public Game() {} // for serialization
    
    public Game(GameState state, Rules rules)
    {
        this.state = state;
        this.rules = rules;
    }
}
