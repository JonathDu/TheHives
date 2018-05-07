/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.players;

import hive.model.board.Hexagon;
import hive.model.board.Tile;

/**
 *
 * @author Thomas
 */
public class PutAction implements Action
{
    public Hexagon where;
    public Tile tile;
    
    @Override
    public void accept(ActionVisitor visitor)
    {
        visitor.visit(this);
    }
}
