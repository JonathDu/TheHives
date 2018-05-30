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
import hive.model.insects.behaviors.GrasshopperBehavior;
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
public class GrasshopperBehaviorTest
{
    GrasshopperBehavior behavior = new GrasshopperBehavior();
    Supplier<Tile> default_tile_supplier = () -> new Tile(InsectType.BEETLE, TeamColor.BLACK);
    ArrayList<Cell> destinations = new ArrayList<>(6);
    Board board = BoardUtil.getEmpty(10, 10);
    int max_size = 2;
    int max_length = 5;
    int max_length_after = 1;
    
    public GrasshopperBehaviorTest()
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
        board.getCenter().value().push(new Tile(InsectType.GRASSHOPPER, TeamColor.WHITE));
        for(HexagonSide side : HexagonSide.values())
        {
            testCantJump(board.getCenter(), side);
            testJump(board.getCenter(), side);
        }
    }

    private void testCantJump(Honeycomb center, HexagonSide side)
    {
        System.out.println(board);
            
        destinations.clear();
        behavior.consumeDestinations(HiveUtil.topCell(center), dest -> destinations.add(dest));

        assert destinations.isEmpty();
            
        Honeycomb neighbor = (Honeycomb)center.getNeighbor(side);
        for(int length_after = 1; length_after <= max_length_after; ++length_after)
        {
            testCantJumpAfterRec(center, side, neighbor, (Honeycomb)neighbor.getNeighbor(side), length_after);
        }
        
        Honeycomb after = neighbor;
        for(int lgth = 1; lgth <= max_length_after; ++lgth)
        {
            after = (Honeycomb)after.getNeighbor(side);
            after.value().clear();
        }
    }

    private void testJump(Honeycomb center, HexagonSide side)
    {
        for(int length = 1; length <= max_length; ++length)
        {
            testJumpRec(center, side, (Honeycomb)center.getNeighbor(side), length);
            
            Honeycomb current = center;
            for(int lgth = 1; lgth <= length; ++lgth)
            {
                current = (Honeycomb)current.getNeighbor(side);
                current.value().clear();
            }
        }
    }
    
    private void testJumpRec(Honeycomb center, HexagonSide side, Honeycomb current, int length)
    {
        if(length == 0)
        {
            System.out.println(board);
            
            destinations.clear();
            behavior.consumeDestinations(HiveUtil.topCell(center), dest -> destinations.add(dest));
            
            assert destinations.size() == 1;
            assert destinations.contains(new Cell(current));
            
            for(int length_after = 1; length_after <= max_length_after; ++length_after)
            {
                testJumpAfterRec(center, side, current, (Honeycomb)current.getNeighbor(side), length_after);
            }
            
            Honeycomb after = current;
            for(int lgth = 1; lgth <= max_length_after; ++lgth)
            {
                after = (Honeycomb)after.getNeighbor(side);
                after.value().clear();
            }
        }
        else
        {
            for(int size = 1; size <= max_size; ++size)
            {
                BoardUtil.setStack(current.value(), size, default_tile_supplier);
                testJumpRec(center, side, (Honeycomb)current.getNeighbor(side), length - 1);
            }
        }
        
    }
    
    private void testJumpAfterRec(Honeycomb center, HexagonSide side, Honeycomb last, Honeycomb current, int length_after)
    {
        if(length_after == 0)
        {
            System.out.println(board);
            
            destinations.clear();
            behavior.consumeDestinations(HiveUtil.topCell(center), dest -> destinations.add(dest));
            
            assert destinations.size() == 1;
            assert destinations.contains(new Cell(last));
            assert !destinations.contains(new Cell(current));
        }
        else
        {
            for(int size = 1; size <= max_size; ++size)
            {
                BoardUtil.setStack(current.value(), size, default_tile_supplier);
                testJumpAfterRec(center, side, last, (Honeycomb)current.getNeighbor(side), length_after - 1);
            }
        }
    }
    
    private void testCantJumpAfterRec(Honeycomb center, HexagonSide side, Honeycomb last, Honeycomb current, int length_after)
    {
        if(length_after == 0)
        {
            System.out.println(board);
            
            destinations.clear();
            behavior.consumeDestinations(HiveUtil.topCell(center), dest -> destinations.add(dest));
            
            assert destinations.isEmpty();
        }
        else
        {
            for(int size = 1; size <= max_size; ++size)
            {
                BoardUtil.setStack(current.value(), size, default_tile_supplier);
                testCantJumpAfterRec(center, side, last, (Honeycomb)current.getNeighbor(side), length_after - 1);
            }
        }
    }
}
