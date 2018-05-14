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
import hive.model.game.rules.GameStatus;
import hive.model.game.rules.HiveRules;
import hive.model.insects.InsectType;
import hive.model.players.Player;
import hive.model.players.actions.Action;
import hive.model.players.actions.MoveAction;
import hive.model.players.actions.PutAction;
import hive.model.players.decisions.Decision;
import hive.model.players.decisions.SimulatedDecision;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import util.hexagons.iterators.NeighborsIterator;

/**
 *
 * @author lucas
 */
public class HiveInterfaceIA implements InterfaceIA
{
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

    @Override
    public int queenFreeNeighbour(Player p, Game game)
    {
        HashSet<Cell> queen_positions = game.state.data.tiles.get(p.color).get(InsectType.QUEEN_BEE);
        if(queen_positions.isEmpty()){
            return 0;
        }
        NeighborsIterator<TilesStack> neighIter = new NeighborsIterator<>(queen_positions.iterator().next().comb);
        int nbNeighbor = 0;
        while (neighIter.hasNext())
            if (neighIter.next().hexagon.value().isEmpty())
                nbNeighbor++;
        return nbNeighbor;
    }
    
    // it does NOT copy equals tiles and equals cells
    @Override
    public ArrayList<Action> currentPlayerPossibilities(Game game)
    {
        ArrayList<Action> actions = new ArrayList<>();
        Player current = game.state.turn.getCurrent();
        
        // PutAction
        for(InsectType type : InsectType.implemented_insects)
        {
            Tile tile = new Tile(type, current.color);
            /*for(int i = 0; i < current.collection.get(type); ++i)
            {
                Iterator<Cell> dest = destinations.iterator();
                while(dest.hasNext())
                    actions.add(new PutAction(dest.next(), tile));
            }*/
            ArrayList<Cell> placements = game.rules.getPossiblePlacements(game.state, tile);
            if(current.collection.get(type) > 0)
            {
                Iterator<Cell> place = placements.iterator();
                while(place.hasNext())
                    actions.add(new PutAction(place.next(), tile));
            }
        }
        
        
        // MoveAction
        for(InsectType type : InsectType.implemented_insects)
        {
            HashSet<Cell> sources = game.state.data.tiles.get(current.color).get(type);
            Iterator<Cell> source_iterator = sources.iterator();
            while(source_iterator.hasNext())
            {
                Cell source = source_iterator.next();
                ArrayList<Cell> destinations = game.rules.getPossibleDestinations(game.state, source);
                Iterator<Cell> dest_iterator = destinations.iterator();
                while(dest_iterator.hasNext())
                    actions.add(new MoveAction(source, dest_iterator.next()));
            }
        }
        return actions;
    }

    @Override
    public ArrayList<Tile> freeTiles(Game game, Player p)
    {
        ArrayList<Tile> free_tiles = new ArrayList<>();
        for(InsectType type : InsectType.implemented_insects)
        {
            HashSet<Cell> sources = game.state.data.tiles.get(p.color).get(type);
            Iterator<Cell> it = sources.iterator();
            while(it.hasNext())  
                free_tiles.add(it.next().getTile());
            
        }
        return free_tiles;
    }

    @Override
    public void doAction(Game game, Action action)
    {
        ((SimulatedDecision)game.state.turn.getCurrent().decision).setAction(action);
        GameProgress gameprogress = new GameProgress(game);
        gameprogress.doAction();
    }

    @Override
    public Action undoAction(Game game)
    {
        GameProgress gameprogress = new GameProgress(game);
        gameprogress.undoAction();
        return game.state.data.last_undo;
    }
    
    @Override
    public ArrayList<Decision> startSimulation(Game game)
    {
        ArrayList<Decision> decisions = new ArrayList<>();
        Iterator<Player> player_iterator = game.state.players.iterator();
        while(player_iterator.hasNext())
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
        while(player_iterator.hasNext())
        {
            Player player = player_iterator.next();
            player.decision = decision_iterator.next();
        }
    }
}
