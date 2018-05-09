/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model;

import hive.model.players.ActionVisitor;
import hive.model.players.MoveAction;
import hive.model.players.PutAction;

/**
 *
 * @author Thomas
 */
public class AlgorithmsDataUpdater implements ActionVisitor
{
    AlgorithmsData data;
    
    AlgorithmsDataUpdater(AlgorithmsData data)
    {
        this.data = data;
    }

    @Override
    public void visit(PutAction action)
    {
        
    }

    @Override
    public void visit(MoveAction action)
    {
        
    }
}
