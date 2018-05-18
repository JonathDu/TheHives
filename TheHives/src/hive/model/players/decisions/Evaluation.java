/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.players.decisions;

import hive.model.HiveInterfaceIA;
import hive.model.board.Tile;
import hive.model.game.Game;
import hive.model.players.Player;
import hive.model.players.actions.Action;
import java.util.ArrayList;

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
        Tile tuile;
        while(!neighbours.isEmpty()){
            tuile=neighbours.remove(0);
            if(tuile.color!=opponent.color){
                value+=insectsValueNeighboursQueen(tuile);
            }
        }
        return value;
    }
    
    static int insectsValue(ArrayList<Tile> freeTile){
        Tile currentTile;
        int value = 0;
            while(!freeTile.isEmpty()){
                currentTile = freeTile.remove(0);
                switch (currentTile.type) 
                {
                    case  QUEEN_BEE:
                        value += 100;
                        break;
                    case  GRASSHOPPER:
                        value += 20;
                        break;
                    case  SOLDIER_ANT:
                        value += 50;
                        break;
                    case  SPIDER:
                        value += 5;
                        break;
                    case  BEETLE:
                        value += 10;
                        break;
                }
                
            }
            return value;
    }
    
    static int insectsValueBloc(ArrayList<Tile> blocTile){
        Tile currentTile;
        int value = 0;
            while(!blocTile.isEmpty()){
                currentTile = blocTile.remove(0);
                switch (currentTile.type) 
                {
                    case  QUEEN_BEE:
                        value += 100;
                        break;
                    case  GRASSHOPPER:
                        value += 20;
                        break;
                    case  SOLDIER_ANT:
                        value += 50;
                        break;
                    case  SPIDER:
                        value += 5;
                        break;
                    case  BEETLE:
                        value += 10;
                        break;
                }
                
            }
            return value;
    }
    
    static int insectsValueNeighboursQueen(Tile freeTile){
                switch (freeTile.type) 
                {
                    case  QUEEN_BEE:
                        return 0;
                    case  GRASSHOPPER:
                        return 100;
                    case  SOLDIER_ANT:
                        return 30;
                    case  SPIDER:
                        return 40;
                    case  BEETLE:
                        return 30;
                    default :
                        return 0;
                   
                }
        
    }
}
