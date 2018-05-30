/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model;

import hive.model.board.Cell;
import hive.model.board.Honeycomb;
import hive.model.board.Tile;
import hive.model.board.TilesStack;
import hive.model.game.Game;
import hive.model.game.rules.GameStatus;
import hive.model.game.rules.HiveUtil;
import hive.model.insects.InsectType;
import hive.model.players.Player;
import hive.model.players.TeamColor;
import hive.model.players.actions.Action;
import hive.model.players.decisions.Decision;
import hive.model.players.decisions.SimulatedDecision;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;
import util.hexagons.iterators.Neighbor;
import util.hexagons.iterators.NeighborsIterator;

/**
 *
 * @author lucas
 */
public class HiveInterfaceIA implements InterfaceIA
{
    // deprecated
    @Override
    public int queenFreeNeighbour(Player p, Game game)
    {
        HashSet<Cell> queen_positions = game.state.data.tiles.get(p.color).get(InsectType.QUEEN_BEE);
        if (queen_positions.isEmpty())
        {
            return 4;
        }
        NeighborsIterator<TilesStack> neighIter = new NeighborsIterator<>(queen_positions.iterator().next().comb);
        int nbNeighbor = 0;
        while (neighIter.hasNext())
        {
            if (neighIter.next().hexagon.value.isEmpty())
            {
                nbNeighbor++;
            }
        }
        return nbNeighbor;
    }
    
    // deprecated
    @Override
    public ArrayList<Tile> queenNeighbours(Player p, Game game)
    {
        HashSet<Cell> queen_positions = game.state.data.tiles.get(p.color).get(InsectType.QUEEN_BEE);
        ArrayList<Tile> neighbours = new ArrayList<>();
        Neighbor<TilesStack> hex;
        if(queen_positions.isEmpty()){
            return neighbours;
        }
        NeighborsIterator<TilesStack> neighIter = new NeighborsIterator<>(queen_positions.iterator().next().comb);
        while (neighIter.hasNext()){
            hex = neighIter.next();
            if (!hex.hexagon.value().isEmpty()){
                neighbours.add(hex.hexagon.value().peek());

            }
        }
        return neighbours;
    }
    
    // deprecated
    @Override
    public ArrayList<Action> currentPlayerPossibilities2(Game game)
    {
        return HiveUtil.getActions(game);

    }
    
    // deprecated
    @Override
    public ArrayList<Tile> freeTiles(Game game, Player p)
    {
        ArrayList<Tile> free_tiles = new ArrayList<>();
        for (InsectType type : InsectType.implemented_insects)
        {
            HashSet<Cell> sources = game.state.data.tiles.get(p.color).get(type);
            for(Cell source : sources)
            {
                if (game.rules.isFree(game.state, source))
                {
                    free_tiles.add(source.getTile());
                }
            }
        }
        return free_tiles;
    }

    // deprecated
    @Override
    public ArrayList<Tile> blockedTiles(Player p, Game game)
    {
        ArrayList<Tile> blocked_tiles = new ArrayList<>();
        for (InsectType type : InsectType.implemented_insects)
        {
            HashSet<Cell> sources = game.state.data.tiles.get(p.color).get(type);
            for(Cell source : sources)
            {
                if (!game.rules.isFree(game.state, source))
                {
                    blocked_tiles.add(source.getTile());
                }
            }
        }
        return blocked_tiles;
    }

    @Override
    public ArrayList<Decision> startSimulation(Game game)
    {
        ArrayList<Decision> decisions = new ArrayList<>();
        Iterator<Player> player_iterator = game.state.players.iterator();
        while (player_iterator.hasNext())
        {
            Player player = player_iterator.next();
            decisions.add(player.decision);
            player.decision = new SimulatedDecision();
        }
        return decisions;
    }

    @Override
    public void endSimulation(Game game, ArrayList<Decision> decisions)
    {
        Iterator<Player> player_iterator = game.state.players.iterator();
        Iterator<Decision> decision_iterator = decisions.iterator();
        while (player_iterator.hasNext())
        {
            Player player = player_iterator.next();
            player.decision = decision_iterator.next();
        }
    }
    
    @Override
    public void doAction(Game game, Action action)
    {
        ((SimulatedDecision) game.state.turn.getCurrent().decision).setAction(action);
        GameProgress gameprogress = new GameProgress(game);
        gameprogress.doAction();
    }

    @Override
    public Action undoAction(Game game)
    {
        GameProgress gameprogress = new GameProgress(game);
        gameprogress.undoAction();
        return game.state.data.undos.peek();
    }
    
    @Override
    public Player currentPlayer(Game game)
    {
        return game.state.turn.getCurrent();
    }

    @Override
    public Player opponentPlayer(Game game)
    {
        return game.state.turn.getOpponent();
    }

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
    public boolean winBoth(Game game)
    {
        return game.rules.getStatus(game.state) == GameStatus.DRAW;
    }
    
    // does not treat NoAction case
    @Override
    public void currentPlayerPossibilities(Game game, ArrayList<Action> actions)
    {
        actions.clear();
        HiveUtil.setPutActions(game, actions);
        HiveUtil.setMoveActions(game, actions);
    }
    
    // does not treat NoAction case
    @Override
    public void currentPlayerPossibilities(Game game, ArrayList<Action> actions, ArrayList<InsectType> insects)
    {
        actions.clear();
        HiveUtil.setMoveActions(game, actions, insects);
        HiveUtil.setPutActions(game, actions);
    }
    
    @Override
    public int nbPossibilitiesQueen(Game game, Player player)
    {
        AtomicInteger nb = new AtomicInteger(0);
        
        HashSet<Cell> sources = game.state.data.tiles.get(player.color).get(InsectType.QUEEN_BEE);
        for(Cell source : sources)
        {
            game.rules.consumeDestinations(game.state, source, cell -> nb.incrementAndGet());
        }
        return nb.get();
    }
    
    
    @Override
    public void setQueenNeighbors(Game game, Player player, ArrayList<Tile> free, ArrayList<Tile> blocked)
    {
        free.clear();
        blocked.clear();
        
        HashSet<Cell> queen_positions = game.state.data.tiles.get(player.color).get(InsectType.QUEEN_BEE);
        if(queen_positions.isEmpty())
            return;
        
        NeighborsIterator<TilesStack> neighbors = new NeighborsIterator<>(queen_positions.iterator().next().comb);
        while (neighbors.hasNext())
        {
            Neighbor<TilesStack> neighbor = neighbors.next();
            
            Cell cell = new Cell((Honeycomb)neighbor.hexagon, 0);
            while(cell.level < cell.comb.value().size())
            {
                if (game.rules.isFree(game.state, cell))
                    free.add(cell.getTile());
                else
                    blocked.add(cell.getTile());
                cell.up();
            }
        }
    }
    
    @Override
    public void setTiles(Game game, ArrayList<Tile> free, ArrayList<Tile> blocked)
    {
        free.clear();
        blocked.clear();
        
        for(TeamColor color : TeamColor.values())
        {
            for (InsectType type : InsectType.implemented_insects)
            {
                HashSet<Cell> sources = game.state.data.tiles.get(color).get(type);
                for(Cell source : sources)
                {
                    if (game.rules.isFree(game.state, source))
                        free.add(source.getTile());
                    else
                        blocked.add(source.getTile());
                }
            }
        }
    }
    @Override
    public boolean queenIsCurshed(Player p, Game game)
    {
        HashSet<Cell> queens = game.state.data.tiles.get(p.color).get(InsectType.QUEEN_BEE);
        Iterator<Cell> ite = queens.iterator();
        if(ite.hasNext())
        {
            Cell cell = ite.next();
            return HiveUtil.isCrushed(cell);          
        }
        return false;
}
    @Override
    public Integer nbInsectsPlayerHand(Game game, Player player, InsectType type)
    {
        return player.collection.get(type);
    }
}

