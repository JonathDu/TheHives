/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model;

import hive.model.board.Board;
import hive.model.board.PositionsPerTeamInsect;
import hive.model.board.TilesStack;
import hive.model.insects.InsectType;
import hive.model.insects.InsectsBehaviors;
import hive.model.insects.behaviors.BeetleBehavior;
import hive.model.insects.behaviors.GrasshopperBehavior;
import hive.model.insects.behaviors.LadybugBehavior;
import hive.model.insects.behaviors.MosquitoBehavior;
import hive.model.insects.behaviors.PillBugBehavior;
import hive.model.insects.behaviors.QueenBeeBehavior;
import hive.model.insects.behaviors.SoldierAntBehavior;
import hive.model.insects.behaviors.SpiderBehavior;
import hive.model.players.Decision;
import hive.model.players.Player;
import hive.model.players.PlayerCollection;
import hive.model.players.Players;
import hive.model.players.TeamColor;
import hive.model.players.decisions.example.HumanDecision;
import util.Matrix;

/**
 *
 * @author Thomas
 */
public class DefaultGame
{
    public static int nbTiles = 11;
    public static int nbPlayers = 2;
    
    public static GameState getGameState()
    {
        Board board = getBoard();
        Players players = getPlayers(new HumanDecision(), new HumanDecision());
        PlayerTurn turn = new PlayerTurn(players);
    
        ActionsTrace trace = new ActionsTrace();
        AlgorithmsData data = new AlgorithmsData(new PositionsPerTeamInsect());
        
        return new GameState(board, players, turn, trace, data);
    }
    
    public static Board getBoard()
    {
        int size = nbTiles * nbPlayers + 2;
        Matrix<TilesStack> m = new Matrix<>(size, size);
        m.setAll(() -> new TilesStack());
        return new Board(m);
    }
    
    public static InsectsBehaviors getInsectBehaviors(InsectType type)
    {
        InsectsBehaviors behaviors = new InsectsBehaviors();
        
        behaviors.put(InsectType.QUEEN_BEE, new QueenBeeBehavior());
        behaviors.put(InsectType.SPIDER, new SpiderBehavior());
        behaviors.put(InsectType.BEETLE, new BeetleBehavior());
        behaviors.put(InsectType.GRASSHOPPER, new GrasshopperBehavior());
        behaviors.put(InsectType.SOLDIER_ANT, new SoldierAntBehavior());
        behaviors.put(InsectType.MOSQUITO, new MosquitoBehavior());
        behaviors.put(InsectType.LADYBUG, new LadybugBehavior());
        behaviors.put(InsectType.PILL_BUG, new PillBugBehavior());
        
        return behaviors;
    }
    
    public static PlayerCollection getCollection()
    {
        PlayerCollection collection = new PlayerCollection();
        
        collection.put(InsectType.QUEEN_BEE, 1);
        collection.put(InsectType.SPIDER, 2);
        collection.put(InsectType.BEETLE, 2);
        collection.put(InsectType.GRASSHOPPER, 3);
        collection.put(InsectType.SOLDIER_ANT, 3);
        collection.put(InsectType.MOSQUITO, 1);
        collection.put(InsectType.LADYBUG, 1);
        collection.put(InsectType.PILL_BUG, 1);
        
        return collection;
    }
    
    public static Player getPlayer(TeamColor color, Decision decision)
    {
        return new Player(color, decision, getCollection());
    }
    
    public static Players getPlayers(Decision d1, Decision d2)
    {
        return new Players(getPlayer(TeamColor.WHITE, d1), getPlayer(TeamColor.BLACK, d2));
    }
}
