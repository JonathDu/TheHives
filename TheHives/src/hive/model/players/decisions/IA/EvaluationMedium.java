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
import static hive.model.players.decisions.IA.Heuristic.QUEEN_CRUSHED_CUR;
import static hive.model.players.decisions.IA.Heuristic.QUEEN_CRUSHED_OP;
import static hive.model.players.decisions.IA.MiniMax.neighboursBlock;
import static hive.model.players.decisions.IA.MiniMax.neighboursFree;

/**
 *
 * @author Coralie
 */
public class EvaluationMedium {
    //ajout atribut
    static int heuristicVal [][][];
    static int evaluationMedium(Game state){
        heuristicVal = HeuristicMedium.getHeuristic();
        HiveInterfaceIA hia = new HiveInterfaceIA();
        Player current = hia.currentPlayer(state);
        Player opponent = hia.opponentPlayer(state);
        int value=0;
        if(hia.winOpponent(state)){
            return -100000;
        }
        else if(hia.winCurrent(state)){
            return 100000;
        }
        else{
            hia.setTiles(state, neighboursFree, neighboursBlock);
            value += tileValues(state);
            value += evalQueen(state);
            value += valueNeighboursQueen(state);
            value += hand(state, current);
            value += hand(state, opponent);
        }
        return value;
    }
   
    static int tileValues(Game state){
        HiveInterfaceIA hia = new HiveInterfaceIA();
        int value = 0;
        for(Tile tuile : neighboursFree)
                value += heuristicVal[(tuile.color == hia.currentPlayer(state).color ? 1 : 0)][5][Heuristic.trans(tuile.type)];
                    
        for(Tile tuile : neighboursBlock)
                value += heuristicVal[(tuile.color == hia.currentPlayer(state).color ? 1 : 0)][6][Heuristic.trans(tuile.type)];
            return value;
    }
    
    static int evalQueen( Game state){
        HiveInterfaceIA hia = new HiveInterfaceIA();
        int values=0;
        Player opponent = hia.opponentPlayer(state);
        Player current = hia.currentPlayer(state);
        int queenOpponentPossibilities = hia.nbPossibilitiesQueen(state, opponent);
        int queenCurrentPossibilities = hia.nbPossibilitiesQueen(state, current);
        
        values+=QUEEN_CRUSHED_CUR[(hia.queenIsCurshed(current, state) ? 1 : 0)];
        values+=QUEEN_CRUSHED_OP[(hia.queenIsCurshed(opponent, state) ? 1 : 0)];
        values+= heuristicVal[0][0][queenOpponentPossibilities];
        values+= heuristicVal[1][0][queenCurrentPossibilities];
        return values;
    }
    
    static int valueNeighboursQueen( Game state){
        int value = 0;
        HiveInterfaceIA hia = new HiveInterfaceIA();
        Player opponent = hia.opponentPlayer(state);
        Player current = hia.currentPlayer(state);
        hia.setQueenNeighbors(state, opponent, neighboursFree, neighboursBlock);
        
        for(Tile tuile : neighboursFree){
            value += heuristicVal[0][((tuile.color == hia.currentPlayer(state).color) ? 4 : 2)][Heuristic.trans(tuile.type)];
        }
        for(Tile tuile : neighboursBlock){
            value += heuristicVal[0][((tuile.color == hia.currentPlayer(state).color) ? 3 : 1)][Heuristic.trans(tuile.type)];
        }
        hia.setQueenNeighbors(state, current, neighboursFree, neighboursBlock);

        for(Tile tuile : neighboursFree){
            value += heuristicVal[1][((tuile.color == hia.currentPlayer(state).color) ? 2 : 4)][Heuristic.trans(tuile.type)];
        }
        for(Tile tuile : neighboursBlock){
            value += heuristicVal[1][((tuile.color == hia.currentPlayer(state).color) ? 1 : 3)][Heuristic.trans(tuile.type)];
        }
        
        return value;
    }
    
    static int hand(Game state, Player p){
        HiveInterfaceIA hia = new HiveInterfaceIA();
        int value=0;
        value+=heuristicVal[(p==hia.currentPlayer(state) ? 1 : 0)][7][0]*hia.nbInsectsPlayerHand(state, p , InsectType.QUEEN_BEE);
        value+=heuristicVal[(p==hia.currentPlayer(state) ? 1 : 0)][7][1]*hia.nbInsectsPlayerHand(state, p , InsectType.SPIDER);
        value+=heuristicVal[(p==hia.currentPlayer(state) ? 1 : 0)][7][2]*hia.nbInsectsPlayerHand(state, p , InsectType.BEETLE);
        value+=heuristicVal[(p==hia.currentPlayer(state) ? 1 : 0)][7][3]*hia.nbInsectsPlayerHand(state, p , InsectType.GRASSHOPPER);
        value+=heuristicVal[(p==hia.currentPlayer(state) ? 1 : 0)][7][4]*hia.nbInsectsPlayerHand(state, p , InsectType.SOLDIER_ANT);
        return value;

    }
    
}
