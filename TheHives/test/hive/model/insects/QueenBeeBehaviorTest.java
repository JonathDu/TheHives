/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.insects;

import hive.model.board.Board;
import hive.model.board.Cell;
import hive.model.board.Honeycomb;
import hive.model.board.Tile;
import hive.model.game.rules.HiveUtil;
import hive.model.insects.behaviors.BeetleBehavior;
import hive.model.insects.behaviors.QueenBeeBehavior;
import hive.model.players.TeamColor;
import java.util.ArrayList;
import java.util.function.Supplier;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import testutil.BoardUtil;
import util.hexagons.HexagonSide;

/**
 *
 * @author Thomas
 */
public class QueenBeeBehaviorTest
{
    QueenBeeBehavior behavior = new QueenBeeBehavior();
    Supplier<Tile> default_tile_supplier = () -> new Tile(InsectType.BEETLE, TeamColor.BLACK);
    ArrayList<Cell> destinations = new ArrayList<>(6);
    Board board = BoardUtil.getEmpty(4, 4);
    int max_size = 5;
    
    public QueenBeeBehaviorTest()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }
    
    @Before
    public void setUp()
    {
    }
    
    @After
    public void tearDown()
    {
    }
    
    // test if the beetle can go down
    @Test
    public void test()
    {
        board.getCenter().value().push(new Tile(InsectType.QUEEN_BEE, TeamColor.WHITE));
        for(HexagonSide side : HexagonSide.values())
        {
            testNotFree(board.getCenter(), side);
            testSlide(board.getCenter(), side);
            testNoWall(board.getCenter(), side);
        }
    }
    
    public void testNotFree(Honeycomb center, HexagonSide side)
    {
        assert center.value().size() == 1;
        for(int size_left = center.value().size(); size_left <= center.value().size() + max_size; ++size_left)
        {
            for(int size_right = center.value().size(); size_right <= center.value().size() + max_size; ++size_right)
            {
                BoardUtil.setStack(center.getNeighbor(side.getBefore()).value(), size_left, default_tile_supplier);
                BoardUtil.setStack(center.getNeighbor(side.getAfter()).value(), size_right, default_tile_supplier);
                System.out.println(board);

                destinations.clear();
                behavior.consumeDestinations(HiveUtil.topCell(center), dest -> destinations.add(dest));

                assert !destinations.contains(new Cell((Honeycomb)center.getNeighbor(side)));
            }
        }
        center.getNeighbor(side.getBefore()).value().clear();
        center.getNeighbor(side.getAfter()).value().clear();
    }
    
    // queen can slide on the floor when there is a wall
    public void testSlide(Honeycomb center, HexagonSide side)
    {
        assert center.value().size() == 1;
        for(int size_left = center.value().size(); size_left <= center.value().size() + max_size; ++size_left)
        {
            BoardUtil.setStack(center.getNeighbor(side.getBefore()).value(), size_left, default_tile_supplier);
            System.out.println(board);

            destinations.clear();
            behavior.consumeDestinations(HiveUtil.topCell(center), dest -> destinations.add(dest));

            assert destinations.contains(new Cell((Honeycomb)center.getNeighbor(side)));
        }
        center.getNeighbor(side.getBefore()).value().clear();
        
        for(int size_right = center.value().size(); size_right <= center.value().size() + max_size; ++size_right)
        {
            BoardUtil.setStack(center.getNeighbor(side.getAfter()).value(), size_right, default_tile_supplier);
            System.out.println(board);

            destinations.clear();
            behavior.consumeDestinations(HiveUtil.topCell(center), dest -> destinations.add(dest));

            assert destinations.contains(new Cell((Honeycomb)center.getNeighbor(side)));
        }
        center.getNeighbor(side.getAfter()).value().clear();
    }
    
    // queen must not move even if there is a stack of tiles in 2 hexagons in front of it (needs wall to slide)
    public void testNoWall(Honeycomb center, HexagonSide side)
    {
        assert center.value().size() == 1;
        for(int size = 0; size <= max_size; ++size)
        {
            BoardUtil.setStack(center.getNeighbor(side).getNeighbor(side).value(), size, default_tile_supplier);
            System.out.println(board);
                
            destinations.clear();
            behavior.consumeDestinations(HiveUtil.topCell(center), dest -> destinations.add(dest));
            
            assert !destinations.contains(new Cell((Honeycomb)center.getNeighbor(side)));
        }
        center.getNeighbor(side).getNeighbor(side).value().clear();
    }
}
