/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.game.utildata;

import hive.model.board.Cell;
import hive.model.game.ActionsTrace;
import hive.model.players.actions.Action;
import java.util.ArrayList;
import java.util.Objects;

/**
 * All the data useful to gain some algorithmic performance over memory storage
 * @author Thomas
 */
public class UtilData
{
    public PositionsPerInsectPerTeam tiles; // to get cells of a specific tile (team color + insect type) in constant time
    public int nb_tiles; // to register easily how many tiles there are on the board
    public int nb_combs; // to register easily how many combs (hexagons) there are on the board
    public Action last_undo; // to register the action that has just been undone
    public ActionsTrace trace; // to register all the actions done before
    public TilesInfluencePerTeam influences; // to register for each team where you can put (indirectly)
    public ArrayList<Cell> placements; // to register possible placements (for put action) only once, as it does not depend of the tile
    public boolean placements_initialized;
    public NbGroupsPerComb nbgroups; // to register how many consecutive non empty neighbors a comb have (useful to check connexity but the use is not implemented yet)
    
    public UtilData() {} // for serialization
    
    public UtilData(PositionsPerInsectPerTeam tiles, int nb_tiles, int nb_combs, ActionsTrace trace, TilesInfluencePerTeam influences, NbGroupsPerComb nbgroups)
    {
        this.tiles = tiles;
        this.nb_tiles = nb_tiles;
        this.nb_combs = nb_combs;
        this.last_undo = null;
        this.trace = trace;
        this.influences = influences;
        this.placements = new ArrayList<>(100);
        this.placements_initialized = false;
        this.nbgroups = nbgroups;
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 43 * hash + Objects.hashCode(this.tiles);
        hash = 43 * hash + this.nb_tiles;
        hash = 43 * hash + this.nb_combs;
        hash = 43 * hash + Objects.hashCode(this.last_undo);
        hash = 43 * hash + Objects.hashCode(this.trace);
        hash = 43 * hash + Objects.hashCode(this.influences);
        hash = 43 * hash + Objects.hashCode(this.placements);
        hash = 43 * hash + Objects.hashCode(this.nbgroups);
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final UtilData other = (UtilData) obj;
        if (!Objects.equals(this.tiles, other.tiles))
        {
            return false;
        }
        if (this.nb_tiles != other.nb_tiles)
        {
            return false;
        }
        if (this.nb_combs != other.nb_combs)
        {
            return false;
        }
        if (!Objects.equals(this.last_undo, other.last_undo))
        {
            return false;
        }
        if (!Objects.equals(this.trace, other.trace))
        {
            return false;
        }
        if (!Objects.equals(this.influences, other.influences))
        {
            return false;
        }
        if (!Objects.equals(this.placements, other.placements))
        {
            return false;
        }
        if (!Objects.equals(this.nbgroups, other.nbgroups))
        {
            return false;
        }
        return true;
    }
}
