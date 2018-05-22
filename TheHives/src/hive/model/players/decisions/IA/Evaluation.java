/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.players.decisions.IA;

import hive.model.HiveInterfaceIA;
import hive.model.board.Tile;
import hive.model.game.Game;
import hive.model.insects.InsectType;
import hive.model.players.Player;
import hive.model.players.actions.Action;
import java.util.ArrayList;
import java.util.EnumMap;

/**
 *
 * @author Coralie
 */
public class Evaluation {
    //ajout atribut
    static int evaluation(Game state){
        HiveInterfaceIA hia = new HiveInterfaceIA();
        Player current = hia.currentPlayer(state);
        Player opponent = hia.opponentPlayer(state);
        int value;
        if(hia.winOpponent(state)){
            return -10000;
        }
        else if(hia.winCurrent(state)){
            return 10000;
        }
        else{
                ArrayList<Tile> currentFreeTile = hia.freeTiles(state,current);
                value = insectsValue(currentFreeTile);
                ArrayList<Tile> opponentFreeTile = hia.freeTiles(state,opponent);
                value -= insectsValue(opponentFreeTile);
                ArrayList<Tile> currentBlocTile = hia.blockedTiles(current, state);
                value -= insectsValueBloc(currentBlocTile);
                ArrayList<Tile> opponentBlocTile = hia.blockedTiles(opponent, state);
                value += insectsValueBloc(opponentBlocTile);
                value +=evalQueen( state);
                value +=valueNeighboursQueen(state);
        }
        return value;
    }
   
    
    static int evalQueen( Game state){
        HiveInterfaceIA hia = new HiveInterfaceIA();
        Player opponent = hia.opponentPlayer(state);
        Player current = hia.currentPlayer(state);
        int afterCurrentNeighbour = hia.queenFreeNeighbour(current, state);
        int afterOpponentNeighbour = hia.queenFreeNeighbour(opponent, state);
        Action hello = hia.undoAction(state);
        opponent = hia.currentPlayer(state);
        current = hia.opponentPlayer(state);
        int beforeCurrentNeighbour = hia.queenFreeNeighbour(current, state);
        int beforeOpponentNeighbour = hia.queenFreeNeighbour(opponent, state);
        hia.doAction(state, hello);
        return ((afterCurrentNeighbour-beforeCurrentNeighbour)-(afterOpponentNeighbour-beforeOpponentNeighbour))*(-30);
    }
    
    static int valueNeighboursQueen( Game state){
        int value=0;
        HiveInterfaceIA hia = new HiveInterfaceIA();
        Player opponent = hia.opponentPlayer(state);
        ArrayList<Tile> neighbours = hia.queenNeighbours(opponent,state);
        
        value+=insectsValueNeighboursQueen(neighbours);
        Tile tuile;
        while(!neighbours.isEmpty()){
            tuile=neighbours.remove(0);
            if(tuile.color!=opponent.color){
                value+=insectsValueNeighboursQueen(neighbours);
            }
        }
        return value;
    }
    
    static int insectsValue(ArrayList<Tile> freeTile){
        return evaluateList(freeTile, blockTilesValues);
    }
    
    static int insectsValueBloc(ArrayList<Tile> blocTile){
        return evaluateList(blocTile, blockTilesValues);
    }
    
    static int insectsValueNeighboursQueen(ArrayList<Tile> queenNeighboursTile){
        return evaluateList(queenNeighboursTile, QueenNeighbourValues);
    }
    static int evaluateList(ArrayList<Tile> Tile, EnumMap<InsectType, Integer> map){
        int value = 0;
        for(Tile tile : Tile)
            value += map.get(tile.type);
        return value;
    }
    
    static EnumMap<InsectType, Integer> blockTilesValues;
    static EnumMap<InsectType, Integer> freeTilesValues;
    static EnumMap<InsectType, Integer> QueenNeighbourValues;
    static
    {
        EnumMap<InsectType, Integer> blockTilesValues = new EnumMap<>(InsectType.class);
        blockTilesValues.put(InsectType.QUEEN_BEE, 100);
        blockTilesValues.put(InsectType.GRASSHOPPER, 20);
        blockTilesValues.put(InsectType.SOLDIER_ANT, 50);
        blockTilesValues.put(InsectType.SPIDER, 5);
        blockTilesValues.put(InsectType.BEETLE, 10);
        EnumMap<InsectType, Integer> freeTilesValues = new EnumMap<>(InsectType.class);
        freeTilesValues.put(InsectType.QUEEN_BEE, 100);
        freeTilesValues.put(InsectType.GRASSHOPPER, 20);
        freeTilesValues.put(InsectType.SOLDIER_ANT, 50);
        freeTilesValues.put(InsectType.SPIDER, 5);
        freeTilesValues.put(InsectType.BEETLE, 10);
         EnumMap<InsectType, Integer> QueenNeighbourValues = new EnumMap<>(InsectType.class);
        QueenNeighbourValues.put(InsectType.QUEEN_BEE, 0);
        QueenNeighbourValues.put(InsectType.GRASSHOPPER, 100);
        QueenNeighbourValues.put(InsectType.SOLDIER_ANT, 30);
        QueenNeighbourValues.put(InsectType.SPIDER, 40);
        QueenNeighbourValues.put(InsectType.BEETLE, 30);
           
    }
    
    
}
