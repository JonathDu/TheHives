/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.players.decisions.IA;

import hive.model.HiveInterfaceIA;
import hive.model.board.Tile;
import hive.model.game.Game;
import hive.model.game.rules.HiveUtil;
import hive.model.players.Player;
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
            ArrayList<Tile> currentFreeTile = hia.freeTiles(state, current);
            value = insectsValue(currentFreeTile);
            ArrayList<Tile> opponentFreeTile = hia.freeTiles(state, opponent);
            value -= insectsValue(opponentFreeTile);
            ArrayList<Tile> currentBlocTile = hia.blockedTiles(current, state);
            value -= insectsValueBloc(currentBlocTile);
            ArrayList<Tile> opponentBlocTile = hia.blockedTiles(opponent, state);
            value += insectsValueBloc(opponentBlocTile);
            value += evalQueen(state);
            value += valueNeighboursQueen(state);
        }
        return value;
    }
   
    
    static int evalQueen( Game state){
        HiveInterfaceIA hia = new HiveInterfaceIA();
        int values=0;
        Player opponent = hia.opponentPlayer(state);
        Player current = hia.currentPlayer(state);
        int queenOpponentPossibilities = hia.nbPossibilitiesQueen(state, opponent);
        int queenCurrentPossibilities = hia.nbPossibilitiesQueen(state, current);
        if(hia.queenIsCurshed(current, state)){
            values-=200;
        }
        if(hia.queenIsCurshed(opponent, state)){
            values+=100;
        }
        switch (queenOpponentPossibilities) {
            case 0:
                values+= 170;
                break;
            case 1:
                values+= 152;
                break;
            default:
                values-= 166;
                break;
        }
        switch (queenCurrentPossibilities) {
            case 0:
                values-= 184;
                break;
            case 1:
                values-= 170;
                break;
            default:
                values+= 150;
                break;
        }
        
        
        return values;
    }
    
    static int valueNeighboursQueen( Game state){
        int value = 0;
        HiveInterfaceIA hia = new HiveInterfaceIA();
        Player opponent = hia.opponentPlayer(state);
        Player current = hia.currentPlayer(state);
        ArrayList<Tile> neighboursOpponent = hia.queenNeighbours(opponent, state);
        ArrayList<Tile> neighboursCurrent = hia.queenNeighbours(current, state);
        
        for(Tile tuile : neighboursOpponent){
            value += insectsValueNeighboursQueenOpponent(tuile, state);

        }
        for(Tile tuile : neighboursCurrent){
            value -= insectsValueNeighboursQueenCurrent(tuile, state);

        }
        return value;
    }
    
    static int insectsValue(ArrayList<Tile> freeTile){
        
        int value = 0;
            
            for(Tile currentTile : freeTile){
                switch (currentTile.type) 
                {
                    case  QUEEN_BEE:
                        value += 50;
                        break;
                    case  GRASSHOPPER:
                        value += 191;
                        break;
                    case  SOLDIER_ANT:
                        value += 132;
                        break;
                    case  SPIDER:
                        value += 56;
                        break;
                    case  BEETLE:
                        value += 50;
                        break;
                }
                
            }
            return value;
    }
    
    static int insectsValueBloc(ArrayList<Tile> blocTile){
       
        int value = 0;
            for(Tile currentTile : blocTile){
                switch (currentTile.type) 
                {
                    case  QUEEN_BEE:
                        value += 64;
                        break;
                    case  GRASSHOPPER:
                        value += 144;
                        break;
                    case  SOLDIER_ANT:
                        value += 140;
                        break;
                    case  SPIDER:
                        value += 122;
                        break;
                    case  BEETLE:
                        value += 121;
                        break;
                }
                
            }
            return value;
    }
    
    static int insectsValueNeighboursQueenCurrent(Tile tile, Game state){
        HiveInterfaceIA hia = new HiveInterfaceIA();
        if (tile.color == hia.currentPlayer(state).color) {
            switch (tile.type) {
                case QUEEN_BEE:
                    return 0;
                default:
                    return 93;

            }
        }else{
            switch (tile.type) {
                case QUEEN_BEE:
                    return 136;
                case BEETLE:
                    return 200;
                default:
                    return 181;

            }
        }
    }
    static int insectsValueNeighboursQueenOpponent(Tile freeTile, Game state){
            HiveInterfaceIA hia = new HiveInterfaceIA();
            if (freeTile.color == hia.currentPlayer(state).color) {
                switch (freeTile.type) {
                    case QUEEN_BEE:
                        return 16;
                    case GRASSHOPPER:
                        return 200;
                    case SOLDIER_ANT:
                        return 115;
                    case SPIDER:
                        return 65;
                    case BEETLE:
                        return 84;
                    default:
                        return 100;

                }
            }else{
                return 0;
            }

    }
    
}
