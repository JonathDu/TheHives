/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.players.actions;

import hive.model.board.Tile;
import hive.model.board.Cell;
import java.io.Serializable;

/**
 *
 * @author Thomas
 */
public class PutAction implements Action, Serializable
{
    public Cell where;
    public Tile tile;
    
    public PutAction() {}
    
    public PutAction(Cell where, Tile tile)
    {
        this.where = where;
        this.tile = tile;
    }
    
    @Override
    public void accept(ActionVisitor visitor)
    {
        visitor.visit(this);
    }
    
    @Override
    public String toString()
    {
        return "(" + tile + " put at " + where + ")";
    }
}
