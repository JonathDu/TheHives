/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.game;

import hive.model.GameProgress;
import hive.model.board.Board;
import hive.model.board.Cell;
import hive.model.board.Honeycomb;
import hive.model.board.Tile;
import hive.model.game.ActionsTrace;
import hive.model.game.DefaultGame;
import static hive.model.game.DefaultGame.getBoard;
import static hive.model.game.DefaultGame.getPlayers;
import hive.model.game.Game;
import hive.model.game.GameState;
import hive.model.game.PlayerTurn;
import hive.model.game.rules.GameStatus;
import hive.model.game.rules.HiveRules;
import hive.model.game.rules.Rules;
import hive.model.game.utildata.TilesInfluencePerTeam;
import hive.model.game.utildata.PositionsPerInsectPerTeam;
import hive.model.game.utildata.UtilData;
import hive.model.insects.InsectType;
import hive.model.players.Player;
import hive.model.players.Players;
import hive.model.players.TeamColor;
import hive.model.players.actions.Action;
import hive.model.players.actions.PutAction;
import hive.model.players.decisions.Decision;
import hive.model.players.decisions.SimulatedDecision;
import java.util.ArrayList;
import java.util.Iterator;
import util.Vector2i;

/**
 *
 * @author Thomas
 */
public class PrecalculatedGame
{
    public enum Id
    {
        DEFAULT,
        GAME_A,
        GAME_B,
        GAME_C;
    }
    
    public static Game get(Id id, Decision d1, Decision d2)
    {
        Game game = DefaultGame.get(d1, d2);
        
        ArrayList<Action> actions = getActions(id, game.state.board);
        
        setGameAfter(game, actions);
        
        
        return game;
    }
    
    public static void setGameAfter(Game game, ArrayList<Action> actions_data)
    {
        
        ArrayList<Decision> tmp = new ArrayList<>();
        Iterator<Player> players;
        
        players = game.state.players.iterator();
        while(players.hasNext())
        {
            Player player = players.next();
            tmp.add(player.decision);
            player.decision = new SimulatedDecision();
        }
        
        GameProgress progress = new GameProgress(game);
        Iterator<Action> actions = actions_data.iterator();
        while (game.rules.getStatus(game.state) == GameStatus.CONTINUES && actions.hasNext())
        {
            Player player = progress.game.state.turn.getCurrent();
            
            SimulatedDecision decision = (SimulatedDecision)player.decision;
            decision.setAction(actions.next());
            
            progress.doAction();
        }
        
        players = game.state.players.iterator();
        Iterator<Decision> decisions = tmp.iterator();
        while(players.hasNext())
        {
            Player player = players.next();
            Decision decision = decisions.next();
            player.decision = decision;
        }
    }
    
    public static ArrayList<Action> getActions(Id id, Board board)
    {
        ArrayList<Action> actions = new ArrayList<>();
        switch(id)
        {
        case DEFAULT:
            break;
        case GAME_A:
            actions.add(new PutAction(new Cell((Honeycomb)board.getHexagon(new Vector2i(11, 9))), new Tile(InsectType.SOLDIER_ANT, TeamColor.WHITE)));
            actions.add(new PutAction(new Cell((Honeycomb)board.getHexagon(new Vector2i(11, 10))), new Tile(InsectType.BEETLE, TeamColor.WHITE)));
            actions.add(new PutAction(new Cell((Honeycomb)board.getHexagon(new Vector2i(11, 11))), new Tile(InsectType.GRASSHOPPER, TeamColor.BLACK)));
            actions.add(new PutAction(new Cell((Honeycomb)board.getHexagon(new Vector2i(11, 12))), new Tile(InsectType.SOLDIER_ANT, TeamColor.BLACK)));
            actions.add(new PutAction(new Cell((Honeycomb)board.getHexagon(new Vector2i(12, 10))), new Tile(InsectType.QUEEN_BEE, TeamColor.WHITE)));
            actions.add(new PutAction(new Cell((Honeycomb)board.getHexagon(new Vector2i(12, 12))), new Tile(InsectType.QUEEN_BEE, TeamColor.BLACK)));
            break;
        case GAME_B:
            break;
        case GAME_C:
            break;
        }
        
        return actions;
    }
}
