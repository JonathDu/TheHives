/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.players.decisions.cerveau;

import hive.model.players.decisions.*;
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
public class HardIALearning implements IA{   
    ArrayList<Action> [] actionList;
    EvaluationLearning eval;
    //mettre évaluation + construteur
    
    private void init(int taille){
        actionList = new ArrayList[taille];
        for(int i=0;i<taille;i++){
            actionList[i]=new ArrayList<>(300);
        }
        
    }

    public HardIALearning(EvaluationLearning eval) {
        this.eval = eval;
    }
    
    
    
    @Override
    public Action SearchAction(Game state){
        HiveInterfaceIA hia = new HiveInterfaceIA();
        int depth =3;
        init(depth+1);
        MiniMaxLearning mini = new MiniMaxLearning(eval);
        hia.currentPlayerPossibilities(state,actionList[depth]);
        if(actionList[depth].isEmpty()){
            return new NoAction();
        }
        ArrayList<Action> maxActionList = new ArrayList<>();
        int max=-50000, tmp;
        Action currentAction;

        while(!actionList[depth].isEmpty()){
            currentAction = actionList[depth].remove(0);

            hia.doAction(state, currentAction);
            if(hia.winOpponent(state)){
                hia.undoAction(state);
                return currentAction;
            }
            else if(!hia.winCurrent(state)){
                tmp = mini.miniMaxOpponent(state, depth-1, max,actionList);
                hia.undoAction(state);
                if(tmp > max){
                    max = tmp;
                    maxActionList.clear();
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
            currentAction = new NoAction() ; //action null
        return currentAction;
    }
}