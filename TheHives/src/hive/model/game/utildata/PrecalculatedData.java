/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.game.utildata;

import hive.model.board.Board;

/**
 *
 * @author Thomas
 */
public class PrecalculatedData
{
    public PositionsPerTeamInsect tiles; // to get tiles of a specific insect in constant time
    public int nb_tiles;
    
    public PrecalculatedData(PositionsPerTeamInsect tiles, int nb_tiles)
    {
        this.tiles = tiles;
        this.nb_tiles = nb_tiles;
    }
    
    public static PrecalculatedData getFrom(Board board)
    {
        PrecalculatedData data = null;
        
        return data;
    }
}
