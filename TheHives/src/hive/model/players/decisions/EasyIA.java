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
public class EasyIA implements IA{
    @Override
    public Action SearchAction(GameState state){
        ArrayList<Action> actionList = ArrayAction(state);
        Action currentAction;
        int i=0;
        while(i<actionList.size()){
            currentAction = actionList.get(i);
            if(currentAction.isWon()){
                return currentAction;
            }
            else if(currentAction.isLost()){
                actionList.remove(i);
                 if(actionList.isEmpty())
                    return currentAction;
            }
            else
                i++; 
        }
        Random rnd = new Random();
        return actionList.get(rnd.nextInt(actionList.size()));
    }
}
