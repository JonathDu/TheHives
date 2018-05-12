/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.players.decisions;

import hive.model.game.Game;
import hive.model.players.actions.Action;

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
        IA ia;
        switch (qI) 
        {
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
        return ia.SearchAction(state);
    }
    
}
