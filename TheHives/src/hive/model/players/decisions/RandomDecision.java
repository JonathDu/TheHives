/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.players.decisions;

import hive.model.HiveInterfaceIA;
import hive.model.game.Game;
import hive.model.game.rules.HiveRules;
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
        ((HiveRules)game.rules).setPossibleActions(game.state, actions);
        return actions.get(rand.nextInt(actions.size()));
    }
}