/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model;

import hive.model.board.Cell;
import hive.model.board.Tile;
import hive.model.board.TilesStack;
import hive.model.game.Game;
import hive.model.game.GameStatus;
import hive.model.insects.InsectType;
import hive.model.players.Player;
import hive.model.players.actions.Action;
import hive.model.players.decisions.Decision;
import hive.model.players.decisions.HumanDecision;
import java.util.ArrayList;
import util.hexagons.iterators.NeighborsIterator;

/**
 *
 * @author lucas
 */
public class HiveInterfaceIA implements InterfaceIA
{

    @Override
    public boolean winCurrent(Game game)
    {
        return game.rules.getStatus(game.state) == GameStatus.CURRENT_WINS;
    }

    @Override
    public boolean winOpponent(Game game)
    {
        return game.rules.getStatus(game.state) == GameStatus.OPPONENT_WINS;
    }

    @Override
    public int queenFreeNeighbour(Player p, Game game)
    {
        ArrayList<Cell> queen_positions = game.state.data.tiles.get(p.color).get(InsectType.QUEEN_BEE);
        assert queen_positions.size() == 1;
        NeighborsIterator<TilesStack> neighIter = new NeighborsIterator<>(queen_positions.get(0).comb);
        int nbNeighbor = 0;
        while (neighIter.hasNext())
        {
            if (neighIter.next().getValue().isEmpty())
            {
                nbNeighbor++;
            }
        }
        return nbNeighbor;
    }

    @Override
    public ArrayList<Action> currentPlayerPossibilities(Game game)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Tile> freeTiles(Game game, Player p)
    {
        ArrayList<Tile> tiles = new ArrayList<>();
        for (InsectType type : InsectType.values())
        {
            for (int i = 0; i < p.collection.get(type); i++)
            {
                Tile tile = new Tile(type, p.color);
                tiles.add(tile);
            }
        }
        return tiles;
    }

    @Override
    public void doAction(Game game, Action action) // a completer
    {
        Decision tmp = game.state.turn.getCurrent().decision;
        game.state.turn.getCurrent().decision = new HumanDecision();
        ((HumanDecision)game.state.turn.getCurrent().decision).setAction(action);
        GameProgress gameprogress = new GameProgress(game);
        gameprogress.doAction();
        game.state.turn.getCurrent().decision = tmp;
    }

    @Override
    public void undoAction(Game game)
    {
        GameProgress gameprogress = new GameProgress(game);
        gameprogress.undoAction();
    }

}
