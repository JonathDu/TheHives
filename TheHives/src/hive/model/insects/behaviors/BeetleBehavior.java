/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.insects.behaviors;

import hive.model.board.Cell;
import hive.model.board.Cells;
import hive.model.board.Honeycomb;
import hive.model.board.Tile;
import hive.model.board.TilesStack;
import hive.model.game.Game;
import hive.model.insects.InsectBehavior;
import java.util.ArrayList;
import java.util.function.Predicate;
import util.Iterators;
import util.hexagons.Hexagon;
import util.hexagons.iterators.NeighborsIterator;
import util.iterators.FilteringIterator;

/**
 *
 * @author Thomas
 */
public class BeetleBehavior implements InsectBehavior
{

    @Override
    public ArrayList<Cell> getPossibleDestinations(Game game, Cell cell)
    {
        
        ArrayList<Cell> list = new ArrayList<>();
        
        if(Cells.isCrushed(cell) || !Cells.isConnexWithout(cell, game.state.data.nb_combs))
            return list;
        
        NeighborsIterator neighbors = new NeighborsIterator(cell.comb);
        
        // for each neighbor
        while (neighbors.hasNext())
        {
            Honeycomb neighbor = (Honeycomb)neighbors.next().hexagon;
            // if it is higher than the beetle
            if (neighbor.getValue().size() > cell.comb.getValue().size()) 
            {
                // the beetle can climb over it
                list.add(new Cell(neighbor));
            }
            else
            {
                // otherwise the beetle must be free to move
                Predicate<Hexagon> is_free_under =
                hexagon ->
                {
                    Honeycomb comb = (Honeycomb)hexagon;
                    return comb.stack().isEmpty() && Cells.isFree(cell, s -> s.size() <= cell.index);
                };
                FilteringIterator neighbor_neighbors = new FilteringIterator(
                        new NeighborsIterator<>((Hexagon)neighbor),
                        hexagon -> !((Honeycomb)hexagon).stack().isEmpty());
                
                
                // the neight must have at least two neighbors (we already count the cell itself)
                // otherwise it would not be connex
                if(Iterators.count(neighbor_neighbors) >= 2)
                    list.add(new Cell(neighbor));
            }
        }
        return list;
    }
}
