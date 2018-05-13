/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.controller.doaction;

import hive.model.players.actions.Action;

/**
 *
 * @author Thomas
 */
public interface ActionSelection
{
    public Action produceAction();
    
    public void accept(ActionSelectionVisitor visitor);
}
