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
import hive.model.insects.behaviors.SoldierAntBehavior;
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
public class SoldierAntBehaviorTest
{
    SoldierAntBehavior behavior = new SoldierAntBehavior();
    Supplier<Tile> default_tile_supplier = () -> new Tile(InsectType.BEETLE, TeamColor.BLACK);
    ArrayList<Cell> destinations = new ArrayList<>(30);
    Board board = BoardUtil.getEmpty(8, 8);
    int max_radius = 5;
    
    public SoldierAntBehaviorTest()
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
    
    @Test
    public void test()
    {
        board.getCenter().value().push(new Tile(InsectType.SOLDIER_ANT, TeamColor.WHITE));
        testEmpty(board.getCenter());
        testNotFree(board.getCenter());
        for(HexagonSide side : HexagonSide.values())
        {
            testInCircle(board.getCenter(), side);
            testNextToCircle(board.getCenter(), side);
            testNextToCircleWithHole(board.getCenter(), side);
        }
    }
    
    private void testEmpty(Honeycomb center)
    {
        System.out.println(board);

        destinations.clear();
        behavior.consumeDestinations(HiveUtil.topCell(center), dest -> destinations.add(dest));
        
        assert destinations.isEmpty();
    }
    
    private void testNotFree(Honeycomb center)
    {
        int n;
        n = 0;
        for(HexagonSide side : HexagonSide.values())
        {
            if(n++ % 2 != 0)
                BoardUtil.setStack(center.getNeighbor(side).value(), 1, default_tile_supplier);
        }
        
        System.out.println(board);

        destinations.clear();
        behavior.consumeDestinations(HiveUtil.topCell(center), dest -> destinations.add(dest));

        assert destinations.isEmpty();
        
        n = 0;
        for(HexagonSide side : HexagonSide.values())
        {
            if(n++ % 2 != 0)
                center.getNeighbor(side).value().clear();
        }
        
        n = 0;
        for(HexagonSide side : HexagonSide.values())
        {
            if(n++ % 2 == 0)
                BoardUtil.setStack(center.getNeighbor(side).value(), 1, default_tile_supplier);
        }
        
        System.out.println(board);

        destinations.clear();
        behavior.consumeDestinations(HiveUtil.topCell(center), dest -> destinations.add(dest));

        assert destinations.isEmpty();
        
        n = 0;
        for(HexagonSide side : HexagonSide.values())
        {
             if(n++ % 2 != 0)
            {
                BoardUtil.setStack(center.getNeighbor(side).value(), 1, default_tile_supplier);
                
                System.out.println(board);

                destinations.clear();
                behavior.consumeDestinations(HiveUtil.topCell(center), dest -> destinations.add(dest));

                assert destinations.isEmpty();
            }
        }
        
        for(HexagonSide side : HexagonSide.values())
        {
            center.getNeighbor(side).value().clear();
        }
    }

    private void testInCircle(Honeycomb center, HexagonSide side)
    {
        Honeycomb empty = (Honeycomb)center.getNeighbor(side);
        for(HexagonSide next_to : HexagonSide.values())
        {
            if(next_to == side.getOpposite())
                continue;
            BoardUtil.setStack(empty.getNeighbor(next_to).value(), 1, default_tile_supplier);
        }
        System.out.println(board);

        destinations.clear();
        behavior.consumeDestinations(HiveUtil.topCell(center), dest -> destinations.add(dest));

        assert !destinations.contains(new Cell(center));
        assert !destinations.contains(new Cell((Honeycomb)center.getNeighbor(side)));
        assert destinations.size() == 11;
        for(Cell dest : destinations)
        {
            assert dest.level == 0;
            assert HiveUtil.hasNeighbors(dest.comb);
        }
        
        for(HexagonSide next_to : HexagonSide.values())
        {
            if(next_to == side.getOpposite())
                continue;
            empty.getNeighbor(next_to).value().clear();
        }
    }

    private void testNextToCircle(Honeycomb center, HexagonSide side)
    {
        Honeycomb origin = (Honeycomb)center.getNeighbor(side).getNeighbor(side);
        for(HexagonSide next_to : HexagonSide.values())
        {
            BoardUtil.setStack(origin.getNeighbor(next_to).value(), 1, default_tile_supplier);
        }
        System.out.println(board);

        destinations.clear();
        behavior.consumeDestinations(HiveUtil.topCell(center), dest -> destinations.add(dest));

        assert !destinations.contains(center);
        assert !destinations.contains(origin);
        assert destinations.size() == 11;
        for(Cell dest : destinations)
        {
            assert dest.level == 0;
            assert HiveUtil.hasNeighbors(dest.comb);
        }
        
        for(HexagonSide next_to : HexagonSide.values())
        {
            origin.getNeighbor(next_to).value().clear();
        }
    }
    
    private void testNextToCircleWithHole(Honeycomb center, HexagonSide side)
    {
        Honeycomb origin = (Honeycomb)center.getNeighbor(side).getNeighbor(side);
        for(HexagonSide next_to : HexagonSide.values())
        {
            if(next_to != side)
                BoardUtil.setStack(origin.getNeighbor(next_to).value(), 1, default_tile_supplier);
        }
        System.out.println(board);

        destinations.clear();
        behavior.consumeDestinations(HiveUtil.topCell(center), dest -> destinations.add(dest));

        assert !destinations.contains(center);
        assert !destinations.contains(origin);
        assert destinations.size() == 11;
        for(Cell dest : destinations)
        {
            assert dest.level == 0;
            assert HiveUtil.hasNeighbors(dest.comb);
        }
        
        for(HexagonSide next_to : HexagonSide.values())
        {
            if(next_to != side)
                origin.getNeighbor(next_to).value().clear();
        }
    }
}
