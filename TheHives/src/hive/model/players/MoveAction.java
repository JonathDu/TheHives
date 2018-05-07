/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.players;

import hive.model.board.Tile;
import java.util.ListIterator;

/**
 *
 * @author Thomas
 */
public class MoveAction implements Action
{
    public ListIterator<Tile> source;
    public ListIterator<Tile> destination;
    
    @Override
    public void accept(ActionVisitor visitor)
    {
        visitor.visit(this);
    }
}
