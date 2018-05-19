/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model;

import hive.model.board.Tile;
import hive.model.game.Game;
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
    public Player currentPlayer(Game game);
    public Player opponentPlayer(Game game);
    public boolean winCurrent(Game game);
    public boolean winOpponent(Game game);
    public boolean winBoth(Game game);
    public int queenFreeNeighbour(Player p, Game game);
    public ArrayList<Tile> queenNeighbours(Player p, Game game);
    public void currentPlayerPossibilities(Game game,ArrayList<Action> actions);
    public ArrayList<Action> currentPlayerPossibilities2(Game game);
    public ArrayList<Tile> freeTiles(Game game, Player p);
    public int nbPossibilitiesQueen(Game game, Player p);
    public void doAction(Game game, Action action);
    public Action undoAction(Game game);
    public ArrayList<Decision> startSimulation(Game game);
    public void endSimulation(Game game, ArrayList<Decision> decisions);
}
