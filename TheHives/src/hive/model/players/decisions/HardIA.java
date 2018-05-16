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
public class HardIA implements IA{   
    
    ArrayList<Action> actionList = new ArrayList<>();
    
    /*private void initializedActionlist(){
        int i;
        for (i=0;i<10;i++){
            this.actionList[i]=new ArrayList<>();
        }
    }*/
    @Override
    public Action SearchAction(Game state){
        int depth =4;
        //initializedActionlist();
        HiveInterfaceIA hia = new HiveInterfaceIA();
        actionList = hia.currentPlayerPossibilities(state);
        if(actionList.isEmpty()){
            return new NoAction();
        }
        ArrayList<Action> maxActionList = new ArrayList<>();
        int max=-50000, tmp;
        Action currentAction;
        while(!actionList.isEmpty()){
            currentAction = actionList.remove(0);
            hia.doAction(state, currentAction);
            if(hia.winOpponent(state)){
                hia.undoAction(state);
                return currentAction;
            }
            else if(!hia.winCurrent(state)){
                tmp = MiniMax.miniMaxOpponent(state, depth-1, max, actionList, actionList.size());
                hia.undoAction(state);
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