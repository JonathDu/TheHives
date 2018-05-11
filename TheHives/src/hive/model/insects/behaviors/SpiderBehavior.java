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
import util.hexagons.Hexagon;
import hive.model.insects.InsectBehavior;
import java.util.ArrayList;
import java.util.function.Predicate;
import util.Iterators;
import util.hexagons.iterators.NeighborsIterator;
import util.iterators.FilteringIterator;

/**
 *
 * @author Thomas
 */
public class SpiderBehavior implements InsectBehavior
{

    @Override
    public ArrayList<Cell> getPossibleDestinations(Game game, Cell cell)
    {
        ArrayList<Cell> list = new ArrayList<>();
        
        if(Cells.isCrushed(cell) || !Cells.isFree(cell) || !Cells.isConnexWithout(cell, game.state.data.nb_tiles))
            return list;
        
        NeighborsIterator<TilesStack> neighbors = new NeighborsIterator<>(cell.comb);

        while (neighbors.hasNext()) //parcours des voisins du scarabe
        {
            Honeycomb neighbor = (Honeycomb) neighbors.next();
            if (!neighbor.getValue().empty()) 
            {
                list.add(new Cell(neighbor)); //le scarabée peut "grimper" sur cette cellule, ne prend pas en compte la hauteur de l'escalade ou de la descente
            }
            else
            {
                Predicate<Honeycomb> predicate = ((hexagon -> !hexagon.stack().isEmpty()));
                FilteringIterator<Honeycomb> neighbor_neighbors = new FilteringIterator<>(new NeighborsIterator<>((Hexagon)neighbor), predicate);
                
                // s'il y a au moins deux voisins (il a forcément au moins un voisin, celui qu'on étudie)
                if(Iterators.count(neighbor_neighbors) > 1)
                    list.add(new Cell(neighbor));
            }
        }
        return list;
    }
    
}
