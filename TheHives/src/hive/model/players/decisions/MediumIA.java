/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.players.decisions;

import hive.model.HiveInterfaceIA;
import hive.model.game.Game;
import hive.model.players.actions.Action;
import hive.model.players.actions.NoAction;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Coralie
 */
public class MediumIA implements IA{
    
    @Override
    public Action SearchAction(Game state){
        HiveInterfaceIA hia = new HiveInterfaceIA();
        ArrayList<Action> actionList = hia.currentPlayerPossibilities(state);
        if(actionList.isEmpty()){
            return new NoAction();
        }
        int value=0, nbValue=0, res;
        Action currentAction;
        int i=0;
        while(i<actionList.size()){
            currentAction = actionList.get(i);
            hia.doAction(state, currentAction);
            res = UtileIA.evaluationOpponent(state);
            if(hia.winCurrent(state)){
                hia.undoAction(state);
                return currentAction;
            }
            else if(hia.winOpponent(state)){
                hia.undoAction(state);
                actionList.remove(i);
                if(actionList.isEmpty())
                    return currentAction;
            }
            else{
                hia.undoAction(state);
                value += res;
                nbValue++;
                i++; 
            }
        }
        Random rnd = new Random();
        currentAction = actionList.get(rnd.nextInt(actionList.size()));
        hia.doAction(state, currentAction);
        while(UtileIA.evaluationOpponent(state)<(value/nbValue)){
            hia.undoAction(state);
            currentAction = actionList.get(rnd.nextInt(actionList.size()));
            hia.doAction(state, currentAction);
        }
        hia.undoAction(state);
        return currentAction;
    }
}
