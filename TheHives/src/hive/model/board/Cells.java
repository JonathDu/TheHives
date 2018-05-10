/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.board;

import hive.model.players.TeamColor;
import java.util.ArrayList;
import util.Iterators;
import util.hexagons.iterators.BreadthIterator;
import util.hexagons.iterators.NeighborsValueIterator;
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
        ArrayList<ArrayList<Cell>> groups = getNeighborsGroups(cell);
        int size = groups.size();
        if(size == 0)
            return true;
        else if(size == 1)
            return groups.get(0).size() <= 4;
        else if(size ==  2)
            return !(groups.get(0).size() == 2 && groups.get(1).size() == 2);
        assert size == 3;
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
            
            ArrayList<ArrayList<Cell>> groups = getNeighborsGroups(cell);
            int size = groups.size();
            if(size <= 1)
                res = true;
            else if(size >= 2)
            {
                res = isConnex(groups.get(0).get(0), nb_tiles);
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
    
    public static ArrayList<ArrayList<Cell>> getNeighborsGroups(Cell cell)
    {
        ArrayList<ArrayList<Cell>> groups = null;
        assert groups.size() <= 3;
        return null;
    }
}

