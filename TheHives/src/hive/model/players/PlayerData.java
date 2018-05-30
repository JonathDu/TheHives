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
public class PlayerData
{
    public String name;
    public int score;
    
    public PlayerData() // for serialization
    {
        
    }
    
    public PlayerData(String name)
    {
        this(name, 0);
    }
    
    public PlayerData(String name, int score)
    {
        this.name = name;
        this.score = score;
    }
}
