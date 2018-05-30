/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testutil;

import hive.model.board.Board;
import hive.model.board.Honeycomb;
import hive.model.board.Tile;
import hive.model.board.TilesStack;
import hive.model.insects.InsectType;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;
import util.Matrix;
import util.hexagons.HexagonSide;
import util.hexagons.iterators.NeighborsIterator;

/**
 *
 * @author Thomas
 */
public class BoardUtil
{
    static Random rand = new Random();
    
    public static Board getEmpty(int sizeX, int sizeY)
    {
        Matrix<TilesStack> m = new Matrix<>(sizeX, sizeY);
        m.setAll((x, y) -> new TilesStack());
        return new Board(m);
    }
    
    public static void reset(Board board)
    {
        for(int y = 0; y < board.dim.y; ++y)
        {
            for(int x = 0; x < board.dim.x; ++x)
            {
                board.hexagons.getAt(x, y).value().clear();
            }
        }
    }
    
    public static void setStack(TilesStack stack, int size, Supplier<Tile> supplier)
    {
        stack.clear();
        for(int k = 0; k < size; ++k)
        {
            stack.add(supplier.get());
        }
    }
    
    public static void setPath(Honeycomb first, List<HexagonSide> path, Consumer<Honeycomb> consumer)
    {
        Honeycomb current = first;
        for(HexagonSide side : path)
        {
            current = (Honeycomb)current.getNeighbor(side);
            consumer.accept((Honeycomb)current);
        }
    }
}
