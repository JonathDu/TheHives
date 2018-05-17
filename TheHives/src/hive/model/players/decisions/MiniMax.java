/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.players.decisions;

import hive.model.HiveInterfaceIA;
import hive.model.game.Game;
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
    static int miniMaxCurrentPlayer(Game state, int depth, int min, ArrayList<Action>[] actionList){
        HiveInterfaceIA hia = new HiveInterfaceIA();
        if(depth == 0 || hia.winCurrent(state) || hia.winOpponent(state)){
            return evaluationCurrentPlayer(state);
        }
        else{
            int vMax = -50000;
            hia.currentPlayerPossibilities(state,actionList[depth]);
            int tmp;
            Action currentAction;
            if(actionList[depth].isEmpty()){
                vMax=max(miniMaxOpponent(state, depth-1, vMax,actionList),vMax);
            }
            else{
                while(!actionList[depth].isEmpty()){
                    currentAction = actionList[depth].remove(0);
                    hia.doAction(state,currentAction);
                    tmp = miniMaxOpponent(state, depth-1, vMax,actionList);
                    hia.undoAction(state);
                    vMax = max(tmp,vMax);
                    if(vMax > min){
                            actionList[depth].clear();
                        return vMax;
                    }
                }
            }
            return vMax;
        }
    }
    static int miniMaxOpponent(Game state, int depth, int max,ArrayList<Action>[] actionList){
        HiveInterfaceIA hia = new HiveInterfaceIA();
        if(depth == 0 || hia.winCurrent(state)|| hia.winOpponent(state)){
            return evaluationOpponent(state);
        }
        else{
            int vMin = 50000;
            hia.currentPlayerPossibilities(state,actionList[depth]);
            int tmp;
            Action currentAction;
            if(actionList[depth].isEmpty()){
                vMin=min(miniMaxCurrentPlayer(state, depth-1, vMin, actionList),vMin);
            }
            else{
                while(!actionList[depth].isEmpty()){
                    currentAction = actionList[depth].remove(0);
                    hia.doAction(state,currentAction);
                    tmp = miniMaxCurrentPlayer(state, depth-1, vMin,actionList);
                    hia.undoAction(state);
                    vMin = min(tmp,vMin);
                    if(vMin < max){
                            actionList[depth].clear();
                        return vMin;
                    }
                }
            }
            return vMin;
        }
    }
 
}
