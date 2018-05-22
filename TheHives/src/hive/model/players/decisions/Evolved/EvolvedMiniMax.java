/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.players.decisions.Evolved;

import hive.model.HiveInterfaceIA;
import hive.model.game.Game;
import hive.model.players.actions.Action;
import hive.model.players.actions.NoAction;
import static hive.model.players.decisions.Evolved.EvolvedEvaluation.evolvedEvaluation;
import static java.lang.Integer.max;
import static java.lang.Integer.min;
import java.util.ArrayList;

/**
 *
 * @author Coralie
 */
public class EvolvedMiniMax{
    static public int evolvedMiniMaxCurrentPlayer(Game state, int depth, int min, ArrayList<Action>[] actionList){
        HiveInterfaceIA hia = new HiveInterfaceIA();
        if(depth == 0 || hia.winCurrent(state) || hia.winOpponent(state)){
            return evolvedEvaluation(state);
        }
        else{
            int vMax = -50000;
            hia.currentPlayerPossibilities(state,actionList[depth]);
            int tmp;
            Action currentAction;
            if(actionList[depth].isEmpty()){
                hia.doAction(state, new NoAction());
                vMax=max(evolvedMiniMaxOpponent(state, depth-1, vMax,actionList),vMax);
                hia.undoAction(state);
                
            }
            else{
                while(!actionList[depth].isEmpty()){
                    currentAction = actionList[depth].remove(0);
                    assert currentAction!=null;
                    hia.doAction(state,currentAction);
                    tmp = evolvedMiniMaxOpponent(state, depth-1, vMax,actionList);
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
    static public int evolvedMiniMaxOpponent(Game state, int depth, int max,ArrayList<Action>[] actionList){
        HiveInterfaceIA hia = new HiveInterfaceIA();
        if(depth == 0 || hia.winCurrent(state)|| hia.winOpponent(state)){
            return -(evolvedEvaluation(state));
        }
        else{
            int vMin = 50000;
            hia.currentPlayerPossibilities(state,actionList[depth]);
            int tmp;
            Action currentAction;
            if(actionList[depth].isEmpty()){
                hia.doAction(state, new NoAction());
                vMin=min(evolvedMiniMaxCurrentPlayer(state, depth-1, vMin, actionList),vMin);
                hia.undoAction(state);
                
            }
            else{
                while(!actionList[depth].isEmpty()){
                    currentAction = actionList[depth].remove(0);
                    assert currentAction!=null;
                    hia.doAction(state,currentAction);
                    tmp = evolvedMiniMaxCurrentPlayer(state, depth-1, vMin,actionList);
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
