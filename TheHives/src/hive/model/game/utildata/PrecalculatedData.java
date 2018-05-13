/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.game.utildata;

import hive.model.board.Cell;
import java.util.ArrayList;

/**
 *
 * @author Thomas
 */
public class PrecalculatedData
{
    // for game progress (do / undo)
    public PositionsPerTeamInsect tiles; // to get cells of a specific tile (team color + insect type) in constant time
    public int nb_tiles; // to register easily how many tiles there are on the board
    public int nb_combs; // to register easily how many combs (hexagons) there are on the board
    
    // for hive rules
    public ArrayList<Cell> placements; // to calculate only once possible placements (hive rules call) as it does not depend of the tile
    
    public PrecalculatedData(PositionsPerTeamInsect tiles, int nb_tiles, int nb_combs)
    {
        this.tiles = tiles;
        this.nb_tiles = nb_tiles;
        this.nb_combs = nb_combs;
        
        this.placements = null;
    }
}
