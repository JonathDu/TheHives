/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.board;

import hive.model.players.TeamColor;
import java.util.ArrayList;
import util.Iterators;
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
    
    // groupes de voisins (size=0 vrai) (size=1 vrai)
    // (size=2 count getvalue = pile vide -> parcours largeur sur pile non vide == nbTiles - 1 vrai sinon faux + remettre la pile
    public static boolean isConnex(Cell cell, int nbTiles)
    {
        if(cell.stack.size() > 1)
            return true;
        ArrayList<ArrayList<Cell>> groups = getNeighborsGroups(cell);
        int size = groups.size();
        if(size <= 1)
            return true;
        else if(size >= 2)
            return true; //TODO
        return false;
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

