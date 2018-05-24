/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.game;

import hive.model.GameProgress;
import hive.model.game.rules.GameStatus;
import hive.model.players.decisions.Decision;
import hive.model.players.decisions.RandomDecision;

/**
 *
 * @author Thomas
 */
public class GameUtil
{
    public static Game getDefault(Decision d1, Decision d2)
    {
        return DefaultGame.get(d1, d2);
    }
    
    // get a game from the beginning after n random actions
    public static Game getRandomGame(int n)
    {
        Game game = DefaultGame.get(new RandomDecision(), new RandomDecision());
        applyActions(game, n);
        return game;
    }
    
    // apply n actions on a game
    public static void applyActions(Game game, int n)
    {
        GameProgress progress = new GameProgress(game);
        
        int k = 0;
        while (progress.continues() && k++ < n)
            progress.doAction();
    }
}
