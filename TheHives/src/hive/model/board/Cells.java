/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.board;

import hive.model.players.TeamColor;
import java.util.ArrayList;
import util.Iterators;
import util.hexagons.Hexagon;
import util.hexagons.HexagonSide;
import util.hexagons.iterators.BreadthIterator;
import util.hexagons.iterators.NeighborsIterator;
import util.hexagons.iterators.NeighborsValueIterator;
import util.iterators.FilteringIterator;
import util.iterators.StoppingIterator;

/**
 *
 * @author Thomas
 */
public class Cells
{
    public static boolean isSurrounded(Cell cell)
    {
        NeighborsValueIterator<TilesStack> neighbors = new NeighborsValueIterator<>(cell.hexagon);
        return Iterators.count(new StoppingIterator<TilesStack>(neighbors, stack -> !stack.isEmpty())) == 6;
    }
    
    public static boolean isCrushed(Cell cell)
    {
        return cell.index < cell.hexagon.getValue().size() - 1;
    }
    
    // groupes de voisins (size=0 vrai) (size=1 et nb <= 4 vrai) (size=2 et nb 2 et 2 faux sinon vrai) (size=3 faux)
    public static boolean isFree(Cell cell)
    {
        NeighborsIterator<TilesStack> neighbors = new NeighborsIterator<>(cell.hexagon, HexagonSide.A, HexagonSide.F);
        
        int i = 0;
        while(neighbors.hasNext())
        {
            Hexagon<TilesStack> n = neighbors.next();
            if(n.getValue().isEmpty())
                ++i;
            else
                i = 0;
            if(i == 2)
                return true;
        }
        return false;
    }
    
    public static boolean isConnexWithout(Cell cell, int nb_tiles)
    {
        if(cell.stack.size() > 0)
            return true;
        else
        {
            boolean res = false;
            TilesStack removed_stack = cell.hexagon.getValue();
            cell.hexagon.setValue(new TilesStack());
            
            NeighborsValueIterator<TilesStack> neighbors = new NeighborsValueIterator<>(cell.hexagon);
        
            int i = 0;
            while(neighbors.hasNext())
            {
                TilesStack stack = neighbors.next();
                if(stack.isEmpty())
                    ++i;
                else
                    i = 0;
                if(i == 2)
                    return true;
            }
            
            cell.hexagon.setValue(removed_stack);
            return res;
        }
    }
    
    // groupes de voisins (size=0 vrai) (size=1 vrai)
    // (size=2 count getvalue = pile vide -> parcours largeur sur pile non vide == nbTiles - 1 vrai sinon faux + remettre la pile
    public static boolean isConnex(Cell cell, int nb_tiles)
    {
        BreadthIterator<TilesStack> iterator = new BreadthIterator<>(cell.hexagon, stack -> !stack.isEmpty());
        return Iterators.count(iterator) == nb_tiles - 1;
    }
    
    public static TeamColor stackColor(TilesStack stack)
    {
        return stack.peek().color;
    }
    
    // count -> filtre -> voisins qui ont une couleur différente    == 0
    public static boolean neighborsHaveSameColor(Cell cell)
    {
        NeighborsValueIterator<TilesStack> neighbors = new NeighborsValueIterator<>(cell.hexagon);
        return Iterators.count(new StoppingIterator<TilesStack>(neighbors, stack -> stack.isEmpty() && stackColor(stack) == stackColor(cell.stack))) == 6;
    }
    
}

