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
public enum TeamColor
{
    WHITE,
    BLACK;
    
    @Override
    public String toString()
    {
        return this == WHITE ? "W" : "B";
    }
}
