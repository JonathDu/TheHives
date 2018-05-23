/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.game.rules;

import hive.model.board.Cell;
import hive.model.board.Honeycomb;
import hive.model.board.Tile;
import hive.model.board.TilesStack;
import hive.model.game.GameState;
import hive.model.players.TeamColor;
import java.util.function.Predicate;
import util.Iterators;
import util.hexagons.Hexagon;
import util.hexagons.HexagonSide;
import util.hexagons.HexagonUtil;
import util.hexagons.iterators.BreadthIterator;
import util.hexagons.iterators.InfiniteNeighborsIterator;
import util.hexagons.iterators.Neighbor;
import util.hexagons.iterators.NeighborsIterator;
import util.iterators.CountingIterator;
import util.iterators.FilteringIterator;
import util.iterators.StoppingIterator;

/**
 *
 * @author Thomas
 */
public class HiveUtil
{
    // check if the tile is surrounded by 6 cells (at level 0)
    public static boolean isSurrounded(Cell cell)
    {
        return Iterators.count(new StoppingIterator<>(new NeighborsIterator<>(cell.comb), n -> !n.hexagon.value().isEmpty())) == 6;
    }
    
    // check if the tile is below an other one
    public static boolean isCrushed(Cell cell)
    {
        return cell.level < cell.comb.value().size() - 1;
    }
    
    // check if the tile is free to move outside (next to him or under)
    public static boolean isFree(Honeycomb comb, int level)
    {
        InfiniteNeighborsIterator neighbors = new InfiniteNeighborsIterator(comb);
        CountingIterator<TilesStack> counting = new CountingIterator(neighbors, 7);
        
        // turn 7 times to detect 2 adjacents empty neighbors
        int nb_adja = 0;
        while(counting.hasNext())
        {
            TilesStack stack = counting.next();
            // crateria to be free
            if(stack.size() <= level)
                ++nb_adja;
            else
                nb_adja = 0;
            if(nb_adja == 2)
                return true;
        }
        return false;
    }
    
    public static Cell neighborCell(Cell cell, HexagonSide side)
    {
        return new Cell((Honeycomb)cell.comb.getNeighbor(side), cell.level);
    }
    
    // check if the cell has at least two open spaces
    public static boolean isFreeAtSide(Cell cell, HexagonSide side)
    {
        return cell.comb.getNeighbor(side).value().size() <= cell.level &&
                (cell.comb.getNeighbor(side.getBefore()).value().size() <= cell.level ||
                cell.comb.getNeighbor(side.getAfter()).value().size() <= cell.level);
    }
    
    // check if the cell has a wall next to it
    public static boolean hasWallNextToAtSide(Cell cell, HexagonSide side)
    {
        return cell.comb.getNeighbor(side.getBefore()).value().size() > cell.level
                || cell.comb.getNeighbor(side.getAfter()).value().size() > cell.level;
    }
    
    // check the connexity when the cell is removed
    public static boolean isConnexWithout(GameState state, Cell cell)
    {
        assert !cell.comb.value().isEmpty();
        
        if(cell.comb.value().size() > 1)
            return true;
        
        // TODO : get from state.data.nbgroups
        int nb_groups = state.data.nbgroups.get(cell.comb);
        
        // when it equals to 1, the tile can be removed (when 0 the graph contains one tile,
        // and it never happens because the two first players already put 2 tiles)
        if(nb_groups <= 1)
            return true;
        
        // we search a neighbor
        Honeycomb to_see = null;
        NeighborsIterator neighbors = new NeighborsIterator<>(cell.comb);
        while(neighbors.hasNext())
        {
            Neighbor<TilesStack> neighbor = neighbors.next();
            if(!neighbor.hexagon.value().isEmpty())
            {
                to_see = (Honeycomb)neighbor.hexagon;
                break;
            }
        }
        assert to_see != null;

        // from this neighbor we are supposed to be able to go all over the graph even by removing the tile
        // (which means we can count (nb_combs - 1) combs from it
        Tile tmp = cell.comb.value().pop();
        
        boolean res = (countFrom(to_see) == state.data.nb_combs - 1);

        cell.comb.value().push(tmp);

        return res;
    }
    
    // check connexity starting at cell by breath first search : connex if we counts all the tiles
    private static int countFrom(Honeycomb comb)
    {
        BreadthIterator<TilesStack> iterator = new BreadthIterator<>(comb, hexagon -> !hexagon.value().isEmpty());
        return Iterators.count(iterator);
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
        NeighborsIterator<TilesStack> neighbors = new NeighborsIterator(comb);
        FilteringIterator<Neighbor<TilesStack>> existing_neighbors = new FilteringIterator<>(neighbors, n -> !n.hexagon.value().isEmpty());
        FilteringIterator<Neighbor<TilesStack>> other_color_neighbors = new FilteringIterator<>(existing_neighbors, n -> color != stackColor(n.hexagon.value()));
        return Iterators.count(other_color_neighbors) == 0;
    }
    
    public static boolean hasNeighbors(Honeycomb comb)
    {
        FilteringIterator<Neighbor<TilesStack>> neighbors = new FilteringIterator<>(new NeighborsIterator<TilesStack>(comb), neighbor -> !neighbor.hexagon.value().empty());
        return Iterators.count(neighbors) > 0;
    }
    
    public static int nbTurns(GameState state)
    {
        return state.data.trace.size() / state.players.size() + 1;
    }
    
    public static int nbGroups(Honeycomb comb)
    {
        return HexagonUtil.nbGroups(comb, stack -> !stack.isEmpty());
    }
}

