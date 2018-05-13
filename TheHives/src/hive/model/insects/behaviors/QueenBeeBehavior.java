/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.insects.behaviors;

import hive.model.board.Cell;
import hive.model.board.Cells;
import hive.model.board.Honeycomb;
import hive.model.board.TilesStack;
import hive.model.game.Game;
import hive.model.insects.InsectBehavior;
import java.util.ArrayList;
import util.Iterators;
import util.hexagons.iterators.Neighbor;
import util.hexagons.iterators.NeighborsIterator;

/**
 *
 * @author Thomas
 */
public class QueenBeeBehavior implements InsectBehavior
{
    @Override
    public ArrayList<Cell> getPossibleDestinations(Game game, Cell cell)
    {
        assert cell.level == 0;
        
        ArrayList<Cell> list = new ArrayList<>();
        
        if(Cells.isCrushed(cell) || !Cells.isConnexWithout(cell, game.state.data.nb_combs))
            return list;
        
        NeighborsIterator<TilesStack> neighbors = new NeighborsIterator<>(cell.comb);
        
        // for each neighbor
        while (neighbors.hasNext())
        {
            Neighbor<TilesStack> neighbor = neighbors.next();
            
            // the queen only slides
            if(!neighbor.hexagon.value().isEmpty())
                continue;
            
            // the queen must be free to slide
            if(!Cells.isFreeAtSide(cell, neighbor.from))
                continue;
            
            // the queen can slide but the queen has to stay connected with other tiles
            NeighborsIterator<TilesStack> around_neighbor = new NeighborsIterator<>(neighbor.hexagon);

            // if we have found 2 neighbors, it will stay connex anyway (we already count the queen in it)
            if(Iterators.searchN(around_neighbor, n -> !n.hexagon.value().isEmpty(), 2))
                list.add(new Cell((Honeycomb)neighbor.hexagon));
        }
        return list;
    }
}
