/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.updates;

import hive.model.game.AlgorithmsData;
import hive.model.players.actions.ActionVisitor;
import hive.model.players.actions.MoveAction;
import hive.model.players.actions.NoAction;
import hive.model.players.actions.PutAction;

/**
 *
 * @author Thomas
 */
public class AlgorithmsDataDoUpdater implements ActionVisitor
{
    AlgorithmsData data;
    
    AlgorithmsDataDoUpdater(AlgorithmsData data)
    {
        this.data = data;
    }

    @Override
    public void visit(PutAction action)
    {
        data.tiles.get(action.tile.color).get(action.tile.type).add(action.where);
        data.nbTiles += 1;
    }

    @Override
    public void visit(MoveAction action)
    {
        
    }

    @Override
    public void visit(NoAction action)
    {
        
    }
}
