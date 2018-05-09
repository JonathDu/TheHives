/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.game;

import hive.model.board.Board;
import hive.model.board.PositionsPerTeamInsect;

/**
 *
 * @author Thomas
 */
public class AlgorithmsData
{
    public PositionsPerTeamInsect tiles; // to get tiles of a specific insect in constant time
    
    public AlgorithmsData(PositionsPerTeamInsect tiles)
    {
        this.tiles = tiles;
    }
    
    public static AlgorithmsData getFrom(Board board)
    {
        AlgorithmsData data = null;
        
        return data;
    }
}
