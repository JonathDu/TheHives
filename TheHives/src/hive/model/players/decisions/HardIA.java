/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.players.decisions;

import hive.model.game.GameState;
import hive.model.players.actions.Action;
import static hive.model.players.decisions.UtileIA.ArrayAction;
import static hive.model.players.decisions.UtileIA.newStateApply;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Coralie
 */
public class HardIA implements IA{   
    
    @Override
    public Action SearchAction(GameState state){
        ArrayList<Action> actionList = ArrayAction(state);
        ArrayList<Action> maxActionList = new ArrayList<>();
        int max=-50000, tmp;
        Action currentAction;
        int depth =4;
        while(!actionList.isEmpty()){
            currentAction = actionList.remove(0);
            if(currentAction.isWon()){
                return currentAction;
            }
            else if(!currentAction.isLost()){
                tmp = UtileIA.miniMaxOpponent(UtileIA.newStateApply(state, currentAction), depth-1, max);
                if(tmp > max){
                    max = tmp;
                    maxActionList = new ArrayList<>();
                    maxActionList.add(currentAction);
                }
                else if(tmp == max){
                    maxActionList.add(currentAction);
                }
                
            }
        }
        Random rnd = new Random();
        if(!maxActionList.isEmpty()){
            currentAction = maxActionList.get(rnd.nextInt(maxActionList.size()));
            
        }
        else
            currentAction = null ; //action null
        return currentAction;
    }
}