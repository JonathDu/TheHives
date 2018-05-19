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
import java.util.ArrayList;

/**
 *
 * @author Thomas
 */
public class IADecisionLearning implements Decision
{
    EvaluationLearning eval;
    boolean hardIA;
    
    public IADecisionLearning(EvaluationLearning eva) {
        this.eval = eva;
        hardIA = false;
    }
    public IADecisionLearning(Level EHARD){
        eval = null;
        hardIA = true;
    }
    
    
    
    
    @Override
    public Action getAction(Game state)
    {
        HiveInterfaceIA hia = new HiveInterfaceIA();
        IA ia;
        ArrayList<Decision> decisions;
        decisions = hia.startSimulation(state);
        if(hardIA == true)
            ia=new EvolvedHardIA();
        else
            ia = new HardIALearning(eval);
        Action a = ia.SearchAction(state);
        hia.endSimulation(state, decisions);
        return a;
        
    }
    
}
