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
public class HardIA implements IA{   
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
        int depth = 4;
        init(depth+1);
        hia.currentPlayerPossibilities(state,actionList[depth],Heuristic.insects_min);
        int nbCoup = actionList[depth].size();

        if(actionList[depth].isEmpty()){
            return new NoAction();
        }
        ArrayList<Action> maxActionList = new ArrayList<>();
        int max=-5000000, tmp;
        Action currentAction;

        while(!actionList[depth].isEmpty()){
            currentAction = actionList[depth].remove(actionList[depth].size()-1);

            hia.doAction(state, currentAction);
            if(hia.winOpponent(state)){
                hia.undoAction(state);
                actionList[depth].clear();
                return currentAction;
            }
            else{
                tmp = MiniMax.miniMaxOpponent(state, depth-1, max,actionList, nbCoup);
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
        
        assert !maxActionList.isEmpty();
        currentAction = maxActionList.get(rnd.nextInt(maxActionList.size()));
        assert currentAction !=null;
        return currentAction;
    }
}