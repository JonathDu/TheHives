/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.controller.gamescene.game;

import hive.model.board.Cell;
import hive.model.board.Tile;
import hive.model.players.actions.Action;
import hive.model.players.actions.MoveAction;
import hive.model.players.actions.PutAction;
import java.util.ArrayList;

/**
 *
 * @author Thomas
 */
public class ActionBuilder
{

    public enum State
    {
        BEGIN,
        TILE_SELECTED,
        PLACEMENT_SELECTED, // end put
        SOURCE_SELECTED,
        DESTINATION_SELECTED, // end move
    }

    public Tile tile;
    public Cell source;
    public ArrayList<Cell> possibleDestinations;
    public Cell placement_or_destination;
    private State state;

    public ActionBuilder()
    {
        state = State.BEGIN;
    }

    public State getState()
    {
        return state;
    }

    public void setTile(Tile tile)
    {
        assert state == State.BEGIN || state == State.TILE_SELECTED; 
        this.tile = tile;
        state = State.TILE_SELECTED;
    }

    public void setPlacement(Cell cell)
    {
        assert state == State.TILE_SELECTED;
        this.placement_or_destination = cell;
        state = State.PLACEMENT_SELECTED;
    }

    public void setSource(Cell cell)
    {
        assert state == State.BEGIN;
        this.source = cell;
        state = State.SOURCE_SELECTED;
    }
    
    public void setPossibleDestinations(ArrayList<Cell> destinations)
    {
        possibleDestinations = destinations;
    }

    public void setDestination(Cell cell)
    {
        assert state == State.SOURCE_SELECTED;
        this.placement_or_destination = cell;
        state = State.DESTINATION_SELECTED;
    }

    public Action produce()
    {
        Action action = null;
        switch (state)
        {
            case PLACEMENT_SELECTED:
                assert tile != null;
                assert placement_or_destination != null;
                action = new PutAction(placement_or_destination, tile);
                break;
            case DESTINATION_SELECTED:
                assert source != null;
                assert placement_or_destination != null;
                action = new MoveAction(source, placement_or_destination);
                break;
        }
        state = State.BEGIN;
        assert action == null;
        return action;
    }
    
    public void setBegin()
    {
        state = State.BEGIN;
    }
}
