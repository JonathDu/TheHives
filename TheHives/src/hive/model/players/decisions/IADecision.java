/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.players.decisions;

import hive.model.players.decisions.IA.EasyIA;
import hive.model.players.decisions.IA.MediumIA;
import hive.model.players.decisions.IA.IA;
import hive.model.players.decisions.IA.HardIA;
import hive.model.players.decisions.IA.Level;
import hive.model.players.decisions.IA.HardIA;
import hive.model.HiveInterfaceIA;
import hive.model.game.Game;
import hive.model.players.actions.Action;
import java.util.ArrayList;

/**
 *
 * @author Thomas
 */
public class IADecision implements Decision
{
    Level qI;

    public IADecision(Level qI) {
        this.qI = qI;
    }
    
    public void changeQI(Level qI){
        this.qI = qI;
    }
    
    @Override
    public Action getAction(Game state)
    {
        HiveInterfaceIA hia = new HiveInterfaceIA();
        IA ia;
        ArrayList<Decision> decisions;
        decisions = hia.startSimulation(state);
        switch (qI) 
        {
            case EHARD :
                ia = new HardIA();
                break;
            case HARD :
                ia = new HardIA();
                break;
            case MEDIUM :
                ia = new MediumIA();
                break;
            default :
                ia = new EasyIA();
                break;
        }
        
        Action a = ia.SearchAction(state);
        hia.endSimulation(state, decisions);
        return a;
        
    }
    
}
