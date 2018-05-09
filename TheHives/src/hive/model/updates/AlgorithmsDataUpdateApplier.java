/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.updates;

import hive.model.AlgorithmsData;
import hive.model.players.ActionVisitor;
import hive.model.players.MoveAction;
import hive.model.players.NoAction;
import hive.model.players.PutAction;

/**
 *
 * @author Thomas
 */
public class AlgorithmsDataUpdateApplier implements ActionVisitor
{
    AlgorithmsData data;
    
    AlgorithmsDataUpdateApplier(AlgorithmsData data)
    {
        this.data = data;
    }

    @Override
    public void visit(PutAction action)
    {
        data.tiles.get(action.tile.color).get(action.tile.type).add(action.where);
    }

    @Override
    public void visit(MoveAction action)
    {
        
    }

    @Override
    public void visit(NoAction action)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
