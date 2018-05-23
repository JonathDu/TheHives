/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.players;

import hive.model.players.decisions.Decision;
import java.io.Serializable;

/**
 *
 * @author Thomas
 */
public class Player implements Serializable
{
    public TeamColor color;
    public Decision decision;
    public PlayerCollection collection;
    
    public Player() {} // for serialization
    
    public Player(TeamColor color, Decision decision, PlayerCollection collection)
    {
        this.color = color;
        this.decision = decision;
        this.collection = collection;
    }
}
