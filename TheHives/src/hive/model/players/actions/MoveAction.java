/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.players.actions;

import hive.model.board.TilePosition;

/**
 *
 * @author Thomas
 */
public class MoveAction implements Action
{
    public TilePosition source;
    public TilePosition destination;
    
    @Override
    public void accept(ActionVisitor visitor)
    {
        visitor.visit(this);
    }
}
