/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.insects.behaviors;

import hive.model.board.Cell;
import hive.model.game.rules.HiveFunctions;
import hive.model.board.Honeycomb;
import hive.model.board.TilesStack;
import hive.model.game.GameState;
import hive.model.insects.InsectBehavior;
import java.util.ArrayList;
import util.hexagons.iterators.Neighbor;
import util.hexagons.iterators.NeighborsIterator;

/**
 *
 * @author Thomas
 */
public class QueenBeeBehavior implements InsectBehavior
{
    @Override
    public ArrayList<Cell> getPossibleDestinations(GameState state, Cell cell)
    {
        assert cell.level == 0;
        
        ArrayList<Cell> list = new ArrayList<>();
        if(HiveFunctions.isCrushed(cell) || !HiveFunctions.isConnexWithout(cell, state.data.nb_combs))
            return list;
        
        NeighborsIterator<TilesStack> neighbors = new NeighborsIterator<>(cell.comb);
        
        // for each neighbor
        while (neighbors.hasNext())
        {
            Neighbor<TilesStack> neighbor = neighbors.next();
            
            // the queen only slides
            if(!neighbor.hexagon.value().isEmpty())
                continue;
            
            // the queen can slide but the queen has to stay connected with other tiles
            if(HiveFunctions.hasWallNextToAtSide(cell, neighbor.from) && HiveFunctions.isFreeAtSide(cell, neighbor.from))
                list.add(new Cell((Honeycomb)neighbor.hexagon));
        }
        return list;
    }
}
