/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.players.decisions;

import hive.model.game.GameState;
import hive.model.players.actions.Action;
import static java.lang.Integer.max;
import static java.lang.Integer.min;
import java.util.ArrayList;

/**
 *
 * @author Coralie
 */
public class UtileIA {
    static int evaluationCurrentPlayer(GameState state){
        return 0;
    }
    static int evaluationOpponent(GameState state){
        return 0;
    }
    static int miniMaxCurrentPlayer(GameState state, int depth, int min){
        if(depth == 0 || !state.gameOver()){
            return evaluationCurrentPlayer(state);
        }
        else{
            int vMax = -50000;
            ArrayList<Action> actionList = ArrayAction(state);
            int tmp;
            Action currentAction;
            while(!actionList.isEmpty()){
                currentAction = actionList.remove(0);
                tmp = miniMaxOpponent(newStateApply(state,currentAction), depth-1, vMax);
                vMax = max(tmp,vMax);
                if(vMax > min)
                    return vMax;
            }
            return vMax;
        }
    }
    static int miniMaxOpponent(GameState state, int depth, int max){
        if(depth == 0 || !state.gameOver()){
            return evaluationCurrentPlayer(state);
        }
        else{
            int vMin = 50000;
            ArrayList<Action> actionList = ArrayAction(state);
            int tmp;
            Action currentAction;
            while(!actionList.isEmpty()){
                currentAction = actionList.remove(0);
                tmp = miniMaxOpponent(newStateApply(state,currentAction), depth-1, vMin);
                vMin = min(tmp,vMin);
                if(vMin < max)
                    return vMin;
            }
            return vMin;
        }
    }
    static ArrayList<Action> ArrayAction(GameState state){
        return new ArrayList<>();
    }
    static GameState newStateApply(GameState state,Action action){
        return state;
    }
    
    
}
