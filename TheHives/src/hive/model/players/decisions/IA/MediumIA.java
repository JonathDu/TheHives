/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.players.decisions.IA;

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
     ArrayList<Action> [] actionList;
    //mettre évaluation + construteur
    
    private void init(int taille){
        actionList = new ArrayList[taille];
        for(int i=0;i<taille;i++){
            actionList[i]=new ArrayList<>(300);
        }
        
    }
    @Override
    public Action SearchAction(Game state){
        HiveInterfaceIA hia = new HiveInterfaceIA();
        init(1);
        actionList[0] = hia.currentPlayerPossibilities2(state);
        if(actionList[0].isEmpty()){
            return new NoAction();
        }
        int value=0, nbValue=0, res;
        Action currentAction;
        int i=0;
        int max = -50000;
        while(i<actionList[0].size()){
            currentAction = actionList[0].get(i);
            hia.doAction(state, currentAction);
            res = MiniMax.miniMaxOpponent(state, 1, max,actionList);
            if(hia.winOpponent(state)){
                hia.undoAction(state);
                return currentAction;
            }
            else if(hia.winCurrent(state)){
                hia.undoAction(state);
                actionList[0].remove(i);
                if(actionList[0].isEmpty())
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
        currentAction = actionList[0].get(rnd.nextInt(actionList[0].size()));
        hia.doAction(state, currentAction);
        while(Evaluation.evaluation(state)<(value/nbValue)){
            hia.undoAction(state);
            currentAction = actionList[0].get(rnd.nextInt(actionList[0].size()));
            hia.doAction(state, currentAction);
        }
        hia.undoAction(state);
        return currentAction;
    }
}
