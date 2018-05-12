/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.insects.behaviors;

import hive.model.board.Cell;
import hive.model.board.Cells;
import static hive.model.board.Cells.cellAtSameLevel;
import hive.model.board.Honeycomb;
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
public class QueenBeeBehavior implements InsectBehavior
{
    @Override
    public ArrayList<Cell> getPossibleDestinations(Game game, Cell cell)
    {
        assert cell.index == 0;
        
        ArrayList<Cell> list = new ArrayList<>();
        
        if(Cells.isCrushed(cell) || !Cells.isFree(cell.comb, s -> s.isEmpty()) || !Cells.isConnexWithout(cell, game.state.data.nb_combs))
            return list;
        
        Predicate<Hexagon> is_empty_and_free =
        hexagon ->
        {
            Honeycomb comb = (Honeycomb)hexagon;
            return comb.stack().isEmpty() && Cells.isFree(comb, s -> s.isEmpty());
        };
        
        FilteringIterator neighbors = new FilteringIterator(
                new NeighborsIterator(cell.comb), is_empty_and_free);
                

        while (neighbors.hasNext())
        {
            Honeycomb neighbor = (Honeycomb)neighbors.next();
            FilteringIterator neighbor_neighbors = new FilteringIterator(
                    new NeighborsIterator<>((Hexagon)neighbor),
                    hexagon -> !((Honeycomb)hexagon).stack().isEmpty());

            // it must have at least two neighbors (we already count the cell itself)
            if(Iterators.count(neighbor_neighbors) >= 2)
                list.add(new Cell(neighbor));
        }
        return list;
    }
}
