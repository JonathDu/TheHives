/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.players.decisions;

import hive.model.game.Game;
import hive.model.game.rules.HiveUtil;
import hive.model.players.actions.Action;
import hive.model.players.actions.NoAction;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Thomas
 */
public class RandomDecision implements Decision
{
    static ArrayList<Action> actions = new ArrayList<>(200);
    static Random rand = new Random();
    
    @Override
    public Action getAction(Game game)
    {
        actions.clear();
        HiveUtil.setActions(game, actions);
        if(actions.isEmpty())
            actions.add(new NoAction());
        return actions.get(rand.nextInt(actions.size()));
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        return true;
    }
}
