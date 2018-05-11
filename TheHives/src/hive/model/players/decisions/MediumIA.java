/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.players.decisions;

import hive.model.game.GameState;
import hive.model.players.actions.Action;
import static hive.model.players.decisions.UtileIA.ArrayAction;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Coralie
 */
public class MediumIA implements IA{
    
    @Override
    public Action SearchAction(GameState state){
        ArrayList<Action> actionList = ArrayAction(state);
        int value=0, nbValue=0, res;
        Action currentAction;
        int i=0;
        while(i<actionList.size()){
            currentAction = actionList.get(i);
            res = UtileIA.evaluationOpponent(state);
            if(currentAction.isWon()){
                return currentAction;
            }
            else if(currentAction.isLost()){
                actionList.remove(i);
                if(actionList.isEmpty())
                    return currentAction;
            }
            else{
                value += res;
                nbValue++;
                i++; 
            }
        }
        Random rnd = new Random();
        do{
            currentAction = actionList.get(rnd.nextInt(actionList.size()));
        }while(UtileIA.evaluationOpponent(state)<(value/nbValue));
        return currentAction;
    }
}
