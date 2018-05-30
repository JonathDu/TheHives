/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.game;

import hive.model.board.Board;
import hive.model.board.Cell;
import hive.model.board.Tile;
import hive.model.board.TilesStack;
import hive.model.game.rules.HiveUtil;
import hive.model.insects.InsectType;
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

/**
 *
 * @author Thomas
 */
public class CrushedTest
{
    Supplier<Tile> default_tile_supplier = () -> new Tile(InsectType.BEETLE, TeamColor.BLACK);
    Board board = BoardUtil.getEmpty(4, 4);
    int max_size = 5;
    
    public CrushedTest()
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
        for(int size = 1; size <= max_size; ++size)
        {
            BoardUtil.setStack(board.getCenter().value(), size, default_tile_supplier);
            System.out.println(board);
            
            for(int idx = 0; idx < size - 1; ++idx)
            {
                assert HiveUtil.isCrushed(new Cell(board.getCenter(), idx));
            }
            assert !HiveUtil.isCrushed(new Cell(board.getCenter(), size - 1));
        }
    }
}
