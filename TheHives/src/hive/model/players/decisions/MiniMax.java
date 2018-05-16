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
import static hive.model.players.decisions.Evaluation.evaluationCurrentPlayer;
import static hive.model.players.decisions.Evaluation.evaluationOpponent;
import static java.lang.Integer.max;
import static java.lang.Integer.min;
import java.util.ArrayList;

/**
 *
 * @author Coralie
 */
public class MiniMax{
    static int miniMaxCurrentPlayer(Game state, int depth, int min){
        HiveInterfaceIA hia = new HiveInterfaceIA();
        if(depth == 0 || hia.winCurrent(state) || hia.winOpponent(state)){
            return evaluationCurrentPlayer(state);
        }
        else{
            int vMax = -50000;
            ArrayList<Action> actionList = hia.currentPlayerPossibilities2(state);
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
            ArrayList<Action> actionList = hia.currentPlayerPossibilities2(state);
            int tmp;
            Action currentAction;
            if(actionList.isEmpty()){
                vMin=min(miniMaxCurrentPlayer(state, depth-1, vMin),vMin);
            }
            else{
                while(!actionList.isEmpty()){
                    currentAction = actionList.remove(0);
                    hia.doAction(state,currentAction);
                    tmp = miniMaxCurrentPlayer(state, depth-1, vMin);
                    hia.undoAction(state);
                    vMin = min(tmp,vMin);
                    if(vMin < max)
                        return vMin;
                }
            }
            return vMin;
        }
    }
 
}
