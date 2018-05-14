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
import static java.lang.Integer.max;
import static java.lang.Integer.min;
import java.util.ArrayList;

/**
 *
 * @author Coralie
 */
public class UtileIA {
    static int evaluationCurrentPlayer(Game state){
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
            value +=evalQueen( state);
        }
        return value;
    }
    static int evaluationOpponent(Game state){
        HiveInterfaceIA hia = new HiveInterfaceIA();
        Player current = hia.currentPlayer(state);
        Player opponent = hia.opponentPlayer(state);
        int value;
        if(hia.winOpponent(state)){
            return 10000;
        }
        else if(hia.winCurrent(state)){
            return -10000;
        }
        else{
            ArrayList<Tile> currentFreeTile = hia.freeTiles(state,current);
            value = -(insectsValue(currentFreeTile));
            ArrayList<Tile> opponentFreeTile = hia.freeTiles(state,opponent);
            value += insectsValue(opponentFreeTile);
            value -=evalQueen( state);
        }
        return value;
    }
    static int miniMaxCurrentPlayer(Game state, int depth, int min){
        HiveInterfaceIA hia = new HiveInterfaceIA();
        if(depth == 0 || hia.winCurrent(state) || hia.winOpponent(state)){
            return evaluationCurrentPlayer(state);
        }
        else{
            int vMax = -50000;
            ArrayList<Action> actionList = hia.currentPlayerPossibilities(state);
            int tmp;
            Action currentAction;
            if(actionList.isEmpty()){
                vMax=max(miniMaxOpponent(state, depth-1, vMax),vMax);
            }
            else{
                while(!actionList.isEmpty()){
                    currentAction = actionList.remove(0);
                    hia.doAction(state,currentAction);
                    tmp = miniMaxOpponent(state, depth-1, vMax);
                    hia.undoAction(state);
                    vMax = max(tmp,vMax);
                    if(vMax > min)
                        return vMax;
                }
            }
            return vMax;
        }
    }
    static int miniMaxOpponent(Game state, int depth, int max){
        HiveInterfaceIA hia = new HiveInterfaceIA();
        if(depth == 0 || hia.winCurrent(state)|| hia.winOpponent(state)){
            return evaluationOpponent(state);
        }
        else{
            int vMin = 50000;
            ArrayList<Action> actionList = hia.currentPlayerPossibilities(state);
            int tmp;
            Action currentAction;
            if(actionList.isEmpty()){
                vMin=min(miniMaxCurrentPlayer(state, depth-1, vMin),vMin);
            }
            else{
                while(!actionList.isEmpty()){
                    currentAction = actionList.remove(0);
                    hia.doAction(state,currentAction);
                    tmp = miniMaxOpponent(state, depth-1, vMin);
                    hia.undoAction(state);
                    vMin = min(tmp,vMin);
                    if(vMin < max)
                        return vMin;
                }
            }
            return vMin;
        }
    }
    
    static int evalQueen( Game state){
        HiveInterfaceIA hia = new HiveInterfaceIA();
        Player opponent = hia.opponentPlayer(state);
        Player current = hia.currentPlayer(state);
        int afterCurrentNeighbour = hia.queenFreeNeighbour(current, state);
        int afterOpponentNeighbour = hia.queenFreeNeighbour(opponent, state);
        Action hello = hia.undoAction(state);
        int beforeCurrentNeighbour = hia.queenFreeNeighbour(current, state);
        int beforeOpponentNeighbour = hia.queenFreeNeighbour(opponent, state);
        hia.doAction(state, hello);
        return ((afterOpponentNeighbour-beforeOpponentNeighbour)-(currentNeighbour))*50;
    }
    
    static int insectsValue(ArrayList<Tile> freeTile){
        Tile currentTile;
        int value = 0;
            while(!freeTile.isEmpty()){
                currentTile = freeTile.remove(0);
                switch (currentTile.type) 
                {
                    case  QUEEN_BEE:
                        value += 40;
                        break;
                    case  GRASSHOPPER:
                        value += 10;
                        break;
                    case  SOLDIER_ANT:
                        value += 20;
                        break;
                    case  SPIDER:
                        value += 3;
                        break;
                    case  BEETLE:
                        value += 5;
                        break;
                }
                
            }
            return value;
    }
       
    
}
