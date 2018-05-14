/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.controller.gamescene.game.selections;

import hive.controller.gamescene.game.selectors.GameSelector;
import hive.model.board.Cell;
import hive.model.players.actions.MoveAction;

/**
 *
 * @author Thomas
 */
public class MoveActionSelection implements ActionSelection
{
    public enum State
    {
        BEGIN,
        SOURCE_SELECTED,
        DESTINATION_SELECTED,
        END;
    }
    
    public Cell source;
    public Cell destination;
    
    public State state;
    
    
    public MoveActionSelection()
    {
        this.state = State.BEGIN;
    }
    
    @Override
    public MoveAction produceAction()
    {
        return new MoveAction(source, destination);
    }
    
    @Override
    public void accept(GameSelector visitor)
    {
        visitor.visit(this);
    }
}
