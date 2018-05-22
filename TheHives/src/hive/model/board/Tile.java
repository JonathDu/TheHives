/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.board;

import hive.model.players.TeamColor;
import hive.model.insects.InsectType;
import java.io.Serializable;

/**
 *
 * @author Thomas
 */
public class Tile implements Serializable
{
    public InsectType type;
    public TeamColor color;
    
    public Tile() {} // for serialization
    
    public Tile(InsectType type, TeamColor color)
    {
        this.type = type;
        this.color = color;
    }
    
    @Override
    public String toString()
    {
        return "" + type + " " + color;
    }
}
