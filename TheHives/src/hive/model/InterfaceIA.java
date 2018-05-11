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
import java.util.ArrayList;

/**
 *
 * @author lucas
 */
public interface InterfaceIA
{
    public boolean winCurrent(Game game);
    public boolean winOpponent(Game game);
    public int queenFreeNeighbour(Player p, Game game);
    public ArrayList<Action> currentPlayerPossibilities(Game game);
    public ArrayList<Tile> freeTiles(Game game, Player p);
    public void doAction(Game game, Action action);
    public void undoAction(Game game);
}
