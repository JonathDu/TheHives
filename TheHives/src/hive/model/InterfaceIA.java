/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model;

import hive.model.board.Cell;
import hive.model.board.Tile;
import hive.model.game.Game;
import hive.model.insects.InsectType;
import hive.model.players.Player;
import hive.model.players.actions.Action;
import hive.model.players.decisions.Decision;
import java.util.ArrayList;

/**
 *
 * @author lucas
 */
public interface InterfaceIA
{
    public ArrayList<Tile> queenFreeNeighbour(Player p, Game game); // deprecated
    public ArrayList<Tile> queenNeighbours(Player p, Game game); // deprecated
    public ArrayList<Action> currentPlayerPossibilities2(Game game); // deprecated
    public ArrayList<Tile> freeTiles(Game game, Player p); // deprecated
    public ArrayList<Tile> blockedTiles(Player p, Game game); // deprecated
    
    public ArrayList<Decision> startSimulation(Game game);
    public void endSimulation(Game game, ArrayList<Decision> decisions);
    
    public void doAction(Game game, Action action);
    public Action undoAction(Game game);
    
    public Player currentPlayer(Game game);
    public Player opponentPlayer(Game game);
    
    public boolean winCurrent(Game game);
    public boolean winOpponent(Game game);
    public boolean winBoth(Game game);
    
    public void currentPlayerPossibilities(Game game, ArrayList<Action> actions);
    
    public int nbPossibilitiesQueen(Game game, Player player);
    public void setQueenNeighbors(Game game, Player player, ArrayList<Tile> free, ArrayList<Tile> blocked);
    public void setTiles(Game game, ArrayList<Tile> free, ArrayList<Tile> blocked);
    public Integer nbInsectsPlayerHand(Game game, Player player, InsectType type);
}
