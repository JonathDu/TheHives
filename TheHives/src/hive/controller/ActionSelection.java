/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.controller;

import hive.model.players.actions.Action;

/**
 *
 * @author Thomas
 */
public interface ActionSelection
{
    Action produceAction();
    
    void accept(ActionSelectionVisitor visitor);
}
