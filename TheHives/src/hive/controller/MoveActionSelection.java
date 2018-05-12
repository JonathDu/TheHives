/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.controller;

import hive.model.board.Cell;
import hive.model.players.actions.MoveAction;

/**
 *
 * @author Thomas
 */
public class MoveActionSelection
{
    enum State
    {
        WAITS_SOURCE,
        SOURCE_SELECTED,
        DESTINATION_SELECTED;
    }
    
    public State state;
    public Cell source;
    public Cell destination;
    
    public MoveActionSelection()
    {
        state = State.WAITS_SOURCE;
        source = null;
        destination = null;
    }
    
    MoveAction produceAction()
    {
        assert source != null;
        assert destination != null;
        return new MoveAction(source, destination);
    }
}
