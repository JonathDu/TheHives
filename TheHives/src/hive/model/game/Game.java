/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.game;

/**
 *
 * @author Thomas
 */
public class Game
{
    public GameState state;
    public Rules rules;
    
    public Game(GameState state, Rules rules)
    {
        this.state = state;
        this.rules = rules;
    }
}
