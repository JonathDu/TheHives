/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.controller.doaction;

import hive.model.board.Cell;
import hive.model.players.actions.MoveAction;

/**
 *
 * @author Thomas
 */
public class MoveActionSelection implements ActionSelection
{
    public Cell source;
    public Cell destination;
    
    public MoveActionSelection()
    {
        source = null;
        destination = null;
    }
    
    @Override
    public MoveAction produceAction()
    {
        return new MoveAction(source, destination);
    }

    @Override
    public void accept(ActionSelectionVisitor visitor)
    {
        visitor.visit(this);
    }
}
