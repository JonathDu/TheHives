/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.players;

/**
 *
 * @author Thomas
 */
public class Player
{
    public TeamColor color;
    public PlayerDecisions decisions;
    public PlayerCollection collection;
    
    public Player(TeamColor color, PlayerDecisions decisions, PlayerCollection collection)
    {
        this.color = color;
        this.decisions = decisions;
        this.collection = collection;
    }
}
