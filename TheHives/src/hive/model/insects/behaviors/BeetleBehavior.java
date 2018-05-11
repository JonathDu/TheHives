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
import util.hexagons.iterators.ValueIterator;
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

        while (neighbors.hasNext())
        {
            Honeycomb neighbor = (Honeycomb)neighbors.next();
            if (!neighbor.getValue().empty()) 
            {
                list.add(new Cell(neighbor)); //le scarabée peut "grimper" sur cette cellule, ne prend pas en compte la hauteur de l'escalade ou de la descente
            }
            else
            {
                FilteringIterator neighbor_neighbors = new FilteringIterator(
                        new NeighborsIterator<>((Hexagon)neighbor),
                        hexagon -> !((Honeycomb)hexagon).stack().isEmpty());
                
                // s'il y a au moins deux voisins (il a forcément au moins un voisin, celui qu'on étudie)
                if(Iterators.count(neighbor_neighbors) > 1)
                    list.add(new Cell(neighbor));
            }
        }
        return list;
    }
}
