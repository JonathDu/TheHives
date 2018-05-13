/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.game.utildata;

/**
 *
 * @author Thomas
 */
public class PrecalculatedData
{
    public PositionsPerTeamInsect tiles; // to get tiles of a specific insect in constant time
    public int nb_tiles;
    public int nb_combs;
    
    public PrecalculatedData(PositionsPerTeamInsect tiles, int nb_tiles, int nb_combs)
    {
        this.tiles = tiles;
        this.nb_tiles = nb_tiles;
        this.nb_combs = nb_combs;
    }
}
