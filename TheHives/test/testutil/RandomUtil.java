/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testutil;

import hive.model.board.Board;
import hive.model.board.Cell;
import hive.model.board.Honeycomb;
import hive.model.board.Tile;
import hive.model.board.TilesStack;
import hive.model.insects.InsectType;
import hive.model.players.TeamColor;
import hive.model.players.actions.MoveAction;
import hive.model.players.actions.PutAction;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import util.Vector2i;

/**
 *
 * @author Thomas
 */
public class RandomUtil
{
    static Random rand = new Random();
    
    public static int nextInt(int n)
    {
        return rand.nextInt();
    }
    
    public static int nextInt(int a, int b)
    {
        assert a <= b;
        return a + rand.nextInt(b - a + 1);
    }
    
    public static Vector2i nextPosition(Vector2i dim)
    {
        return new Vector2i(nextInt(dim.x), nextInt(dim.y));
    }
    
    public static <E> E nextInList(List<E> list)
    {
        return list.get(nextInt(list.size()));
    }
    
    public static InsectType nextInsect()
    {
        return nextInList(InsectType.implemented_insects);
    }
    
    public static TeamColor nextColor()
    {
        return nextInt(2) == 0 ? TeamColor.WHITE : TeamColor.BLACK;
    }
    
    public static Tile nextTile()
    {
        return new Tile(nextInsect(), nextColor());
    }
    
    public static void nextStack(TilesStack stack, Supplier<Integer> size_supplier, Supplier<Tile> tile_supplier)
    {
        stack.clear();
        int size = size_supplier.get();
        for(int i = 0; i < size; ++i)
            stack.add(tile_supplier.get());
    }
    
    public static Honeycomb nextComb(Board board)
    {
        return board.hexagons.getAt(nextPosition(board.matrix.getDimensions()));
    }
    
    public static Cell nextCell(Supplier<Honeycomb> comb_supplier, Supplier<Integer> level_supplier)
    {
        return new Cell(comb_supplier.get(), level_supplier.get());
    }
    
    public static Cell nextTopCell(Supplier<Honeycomb> comb_supplier)
    {
        Honeycomb comb = comb_supplier.get();
        return new Cell(comb, comb.value().size() - 1);
    }
    
     public static Cell nextAboveCell(Supplier<Honeycomb> comb_supplier)
    {
        Honeycomb comb = comb_supplier.get();
        return new Cell(comb);
    }
    
    public static PutAction nextPutAction(Supplier<Honeycomb> comb_supplier, Supplier<Tile> tile_supplier)
    {
        Honeycomb comb = comb_supplier.get();
        assert comb.value().size() == 0;
        return new PutAction(new Cell(comb), tile_supplier.get());
    }
    
    public static MoveAction nextMoveAction(Cell source, Supplier<Cell> cell_supplier)
    {
        Cell dest = cell_supplier.get();
        assert source.comb != dest.comb;
        return new MoveAction(source, dest);
    }
    
    public static void nextList(List<Integer> list, Supplier<Integer> supplier, int n)
    {
        for(int i = 0; i < n; ++i)
        {
            list.add(supplier.get());
        }
    }
}
