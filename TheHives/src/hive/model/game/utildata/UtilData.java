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
    public NbGroupsPerComb nbgroups;
    
    public UtilData(PositionsPerInsectPerTeam tiles, int nb_tiles, int nb_combs, ActionsTrace trace, TilesInfluencePerTeam influences, NbGroupsPerComb nbgroups)
    {
        this.tiles = tiles;
        this.nb_tiles = nb_tiles;
        this.nb_combs = nb_combs;
        this.last_undo = null;
        this.trace = trace;
        this.influences = influences;
        this.placements = null;
        this.nbgroups = nbgroups;
    }
}
