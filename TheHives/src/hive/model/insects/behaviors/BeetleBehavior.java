/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.insects.behaviors;

import hive.model.board.Cell;
import hive.model.board.Cells;
import hive.model.board.Tile;
import hive.model.board.TilesStack;
import hive.model.game.Game;
import util.hexagons.Hexagon;
import hive.model.insects.InsectBehavior;
import java.util.ArrayList;
import java.util.function.Predicate;
import util.hexagons.iterators.NeighborsIterator;
import util.hexagons.iterators.NeighborsValueIterator;
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
        /*
        ArrayList<Cell> list = new ArrayList<>();
        
        if(Cells.isConnex(cell, game.state.data.))
        
        NeighborsIterator<TilesStack> neighIter = new NeighborsIterator<>(cell.hexagon);

        while (neighIter.hasNext()) //parcours des voisins du scarabe
        {
            Hexagon<TilesStack> currentHexagon = neighIter.next();
            if (!currentHexagon.getValue().empty()) 
            {
                list.add(new Cell(currentHexagon)); //le scarabée peut "grimper" sur cette cellule, ne prend pas en compte la hauteur de l'esclade
            } else
            {
                Tile scarabe = currentHexagon.getValue().pop();
                NeighborsIterator neighbors = new NeighborsIterator<>(currentHexagon);
                FilteringIterator<Hexagon<TilesStack>> fiter = new FilteringIterator<Hexagon<TilesStack>>(neighbors, n -> !n.getValue().isEmpty());

                while (fiter.hasNext()) //itere sur les voisins non vide
                {
                    list.add(new Cell(fiter.next()));
                }
                currentHexagon.getValue().add(scarabe);
            }
        }
        return list;*/
        return null;
    }
}
