/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.board;

import hive.model.players.TeamColor;
import util.Iterators;
import util.hexagons.iterators.BreadthIterator;
import util.hexagons.iterators.InfiniteNeighborsIterator;
import util.hexagons.iterators.NeighborsIterator;
import util.hexagons.iterators.ValueIterator;
import util.iterators.CountingIterator;
import util.iterators.FilteringIterator;
import util.iterators.StoppingIterator;

/**
 *
 * @author Thomas
 */
public class Cells
{
    // check if the tile is surrounded by 6 cells (at level 0)
    public static boolean isSurrounded(Cell cell)
    {
        ValueIterator<TilesStack> neighbors = new ValueIterator<>(new NeighborsIterator(cell.comb));
        return Iterators.count(new StoppingIterator<TilesStack>(neighbors, stack -> !stack.isEmpty())) == 6;
    }
    
    // check if the tile is below an other one
    public static boolean isCrushed(Cell cell)
    {
        return cell.index < cell.comb.getValue().size() - 1;
    }
    
    // check if the tile is free to move outside the cell
    public static boolean isFree(Cell cell)
    {
        InfiniteNeighborsIterator neighbors = new InfiniteNeighborsIterator(cell.comb);
        ValueIterator<TilesStack> counting = new ValueIterator(new CountingIterator(neighbors, 7));
        
        // turn 7 times to detect 2 adjacents empty neighbors
        int nb_adja = 0;
        while(counting.hasNext())
        {
            TilesStack stack = counting.next();
            if(stack.isEmpty())
                ++nb_adja;
            else
                nb_adja = 0;
            if(nb_adja == 2)
                return true;
        }
        return false;
    }
    
    // check the connexity when the cell is removed
    public static boolean isConnexWithout(Cell cell, int nb_combs)
    {
        if(cell.comb.stack().size() > 1)
            return true;
        
        // neighbors iterator to turn twice around
        InfiniteNeighborsIterator neighbors = new InfiniteNeighborsIterator(cell.comb);
        CountingIterator<Honeycomb> counting = new CountingIterator(neighbors, 12);

        // turn twice around the neighbors gives the number or connex groups
        boolean visiting_group = false;
        int nb_groups = 0;
        Honeycomb to_see = null;
        while(counting.hasNext())
        {
            Honeycomb n = counting.next();
            TilesStack stack = n.getValue();
            if(stack.isEmpty())
            {
                if(visiting_group)
                    visiting_group = false;
            }
            else
            {
                to_see = n;
                if(!visiting_group)
                {
                    visiting_group = true;
                    ++nb_groups;
                }
            }
        }
        
        System.out.println("nb = " + nb_groups);
        // dividing by 2 gives the real number of connex groups
        nb_groups /= 2;
        // would mean it has no neighbors which is impossible
        assert nb_groups != 0;
        // if there is one group connex, it is connex anyway
        if(nb_groups == 1)
            return true;
        
        // otherwise we initialized a neighbor to see
        assert to_see != null;

        // from this neighbor we are supposed to be able to go all over the graph even by removing the tile
        Tile tile = cell.comb.stack().pop();
        cell.comb.setValue(new TilesStack());

        boolean res = isConnex(to_see, nb_combs);

        cell.comb.stack().push(tile);

        return res;
    }
    
    // check connexity starting at cell by breath first search : connex if we counts all the tiles
    public static boolean isConnex(Honeycomb comb, int nb_combs)
    {
        System.out.println("connex ?");
        BreadthIterator<TilesStack> iterator = new BreadthIterator<>(comb, stack -> !stack.isEmpty());
        return Iterators.count(iterator) == nb_combs - 1;
    }
    
    // color of a stack is the color of the tile at the top
    public static TeamColor stackColor(TilesStack stack)
    {
        assert !stack.isEmpty();
        return stack.peek().color;
    }
    
    // check if neighbors of an hexagon have the same color given in parameter
    public static boolean neighborsHaveSameColor(Honeycomb comb, TeamColor color)
    {
        ValueIterator<TilesStack> neighbors = new ValueIterator<>(new NeighborsIterator<>(comb));
        FilteringIterator<TilesStack> existing_neighbors = new FilteringIterator<>(neighbors, stack -> !stack.isEmpty());
        FilteringIterator<TilesStack> other_color_neighbors = new FilteringIterator<>(existing_neighbors, stack -> color != stackColor(stack));
        return Iterators.count(other_color_neighbors) == 0;
    }
    
    public static boolean hasNeighbors(Honeycomb comb)
    {
        FilteringIterator neighbors = new FilteringIterator(new NeighborsIterator(comb), hexagon -> !((Honeycomb)hexagon).stack().empty());
        return Iterators.count(neighbors) > 0;
    }
}

