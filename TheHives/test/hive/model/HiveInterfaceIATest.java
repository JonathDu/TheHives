/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model;

import hive.model.board.Cell;
import hive.model.board.Honeycomb;
import hive.model.board.Tile;
import hive.model.game.DefaultGame;
import hive.model.game.Game;
import hive.model.insects.InsectType;
import hive.model.players.Player;
import hive.model.players.PlayerCollection;
import hive.model.players.actions.Action;
import hive.model.players.actions.PutAction;
import hive.model.players.decisions.Decision;
import hive.model.players.decisions.HumanDecision;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import util.Vector2i;

/**
 *
 * @author lucas
 */
public class HiveInterfaceIATest
{

    Game game;
    GameProgress progress;
    HiveInterfaceIA inter;

    public HiveInterfaceIATest()
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
        game = DefaultGame.get(new HumanDecision(), new HumanDecision());
        progress = new GameProgress(game);
        inter = new HiveInterfaceIA();
    }

    @After
    public void tearDown()
    {
    }

    Action createPutActionCurrentPlayer(Game game, InsectType type, Vector2i pos)
    {
        Tile tile = new Tile(type, game.state.turn.getCurrent().color);
        Cell cell = new Cell(game.state.board.getHexagon(pos));
        return new PutAction(cell, tile);
    }

    @Test
    public void testWinCurrentOpponent()
    {
        ((HumanDecision) game.state.turn.getCurrent().decision).setAction(createPutActionCurrentPlayer(game, InsectType.QUEEN_BEE, new Vector2i(1, 1))); // Abeille
        progress.doAction();

        ((HumanDecision) game.state.turn.getCurrent().decision).setAction(createPutActionCurrentPlayer(game, InsectType.BEETLE, new Vector2i(1, 0))); // A
        progress.doAction();

        ((HumanDecision) game.state.turn.getCurrent().decision).setAction(createPutActionCurrentPlayer(game, InsectType.BEETLE, new Vector2i(2, 1))); // B
        progress.doAction();

        ((HumanDecision) game.state.turn.getCurrent().decision).setAction(createPutActionCurrentPlayer(game, InsectType.BEETLE, new Vector2i(2, 2))); // C
        progress.doAction();

        ((HumanDecision) game.state.turn.getCurrent().decision).setAction(createPutActionCurrentPlayer(game, InsectType.BEETLE, new Vector2i(1, 2))); // D
        progress.doAction();

        ((HumanDecision) game.state.turn.getCurrent().decision).setAction(createPutActionCurrentPlayer(game, InsectType.BEETLE, new Vector2i(0, 2))); // E
        progress.doAction();

        System.out.println("Current : " + inter.winCurrent(game));
        System.out.println("Opponent : " + inter.winOpponent(game));

        ((HumanDecision) game.state.turn.getCurrent().decision).setAction(createPutActionCurrentPlayer(game, InsectType.BEETLE, new Vector2i(0, 1))); // F
        progress.doAction();

        System.out.println(game.state.board);

        System.out.println("Current : " + inter.winCurrent(game));
        System.out.println("Opponent : " + inter.winOpponent(game));

        assert inter.winCurrent(game);


        for (int y = 0; y < game.state.board.getData().sizeY(); y++)
        {
            for (int x = 0; x < game.state.board.getData().sizeY(); x++)
            {
                if (!game.state.board.getHexagon(new Vector2i(x, y)).value().isEmpty())
                {
                    System.out.print(game.state.board.getHexagon(new Vector2i(x, y)).value().peek() + "  ,  ");
                }
                else
                {
                    System.out.print("     , ");
                }
            }
            System.out.println("");
        }

        ((HumanDecision) game.state.turn.getCurrent().decision).setAction(createPutActionCurrentPlayer(game, InsectType.BEETLE, new Vector2i(0, 0)));
        progress.doAction();
        
        System.out.println("Current : " + inter.winCurrent(game));
        System.out.println("Opponent : " + inter.winOpponent(game));

        assert inter.winOpponent(game);
    }

    /*@Test
    public void testQueenFreeNeighbour()
    {
        ((HumanDecision) game.state.turn.getCurrent().decision).setAction(createPutActionCurrentPlayer(game, InsectType.QUEEN_BEE, new Vector2i(1, 1))); // Abeille W
        progress.doAction();

        assert inter.queenFreeNeighbour(game.state.turn.getOpponent(), game) == 6;

        ((HumanDecision) game.state.turn.getCurrent().decision).setAction(createPutActionCurrentPlayer(game, InsectType.BEETLE, new Vector2i(1, 0))); // A B
        progress.doAction();

        assert inter.queenFreeNeighbour(game.state.turn.getCurrent(), game) == 5;

        ((HumanDecision) game.state.turn.getCurrent().decision).setAction(createPutActionCurrentPlayer(game, InsectType.BEETLE, new Vector2i(2, 1))); // B W
        progress.doAction();

        assert inter.queenFreeNeighbour(game.state.turn.getOpponent(), game) == 4;

        ((HumanDecision) game.state.turn.getCurrent().decision).setAction(createPutActionCurrentPlayer(game, InsectType.BEETLE, new Vector2i(2, 2))); // C B
        progress.doAction();

        assert inter.queenFreeNeighbour(game.state.turn.getCurrent(), game) == 3;

        ((HumanDecision) game.state.turn.getCurrent().decision).setAction(createPutActionCurrentPlayer(game, InsectType.BEETLE, new Vector2i(1, 2))); // D W
        progress.doAction();

        assert inter.queenFreeNeighbour(game.state.turn.getOpponent(), game) == 2;

        ((HumanDecision) game.state.turn.getCurrent().decision).setAction(createPutActionCurrentPlayer(game, InsectType.BEETLE, new Vector2i(0, 2))); // E B
        progress.doAction();

        assert inter.queenFreeNeighbour(game.state.turn.getCurrent(), game) == 1;

        ((HumanDecision) game.state.turn.getCurrent().decision).setAction(createPutActionCurrentPlayer(game, InsectType.BEETLE, new Vector2i(0, 1))); // F W
        progress.doAction();

        assert inter.queenFreeNeighbour(game.state.turn.getOpponent(), game) == 0;
    }
*/
    @Test
    public void testCurrentPlayerPossibilities()
    {
        ArrayList<Action> possibilities = inter.currentPlayerPossibilities2(game);

        assert possibilities.size() == 11;

        //TODO : a compléter
    }

    @Test
    public void testFreeTiles()
    {
        int nbFreeTiles = inter.freeTiles(game, game.state.turn.getCurrent()).size();

        ((HumanDecision) game.state.turn.getCurrent().decision).setAction(createPutActionCurrentPlayer(game, InsectType.QUEEN_BEE, new Vector2i(1, 1))); // Abeille W
        progress.doAction();

        assert nbFreeTiles - 1 == inter.freeTiles(game, game.state.turn.getOpponent()).size();

        nbFreeTiles = inter.freeTiles(game, game.state.turn.getCurrent()).size();

        ((HumanDecision) game.state.turn.getCurrent().decision).setAction(createPutActionCurrentPlayer(game, InsectType.QUEEN_BEE, new Vector2i(1, 0))); // Abeille W
        progress.doAction();

        assert nbFreeTiles - 1 == inter.freeTiles(game, game.state.turn.getCurrent()).size();
    }

    @Test
    public void testDoAction()
    {
        ArrayList<Decision> tmp = inter.startSimulation(game);

        Action putAction = createPutActionCurrentPlayer(game, InsectType.BEETLE, new Vector2i(0, 0));

        inter.doAction(game, putAction);

        inter.endSimulation(game, tmp);

        System.out.println(game.state.board);

        assert game.state.board.getHexagon(new Vector2i(0, 0)).value().peek().type == InsectType.BEETLE;
    }

    @Test
    public void testUndoAction()
    {
        ArrayList<Decision> tmp = inter.startSimulation(game);

        Action putAction = createPutActionCurrentPlayer(game, InsectType.BEETLE, new Vector2i(0, 0));

        String boardBeforeDo = game.state.board.toString();

        inter.doAction(game, putAction);

        String boardAfterDo = game.state.board.toString();

        inter.undoAction(game);

        String boardAfterUndo = game.state.board.toString();

        assert !boardBeforeDo.equals(boardAfterDo);
        assert boardBeforeDo.equals(boardAfterUndo);
    }
}
