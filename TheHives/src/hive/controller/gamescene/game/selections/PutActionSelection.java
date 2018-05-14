/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.controller.gamescene.game.selections;

import hive.controller.gamescene.game.selectors.GameSelector;
import hive.model.board.Cell;
import hive.model.board.Tile;
import hive.model.players.actions.PutAction;

/**
 *
 * @author Thomas
 */
public class PutActionSelection implements ActionSelection
{
    public enum State
    {
        BEGIN,
        TILE_SELECTED,
        CELL_SELECTED,
        END;
    }
    
    public Cell where;
    public Tile tile;
    
    public State state;
    
    
    public PutActionSelection()
    {
        this.state = State.BEGIN;
    }
    
    @Override
    public PutAction produceAction()
    {
        return new PutAction(where, tile);
    }
    
    @Override
    public void accept(GameSelector visitor)
    {
        visitor.visit(this);
    }
}
