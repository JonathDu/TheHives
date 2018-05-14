/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.controller.gamescene.game;

import hive.controller.gamescene.game.selections.GameSelection;
import hive.controller.gamescene.game.selections.NoGameSelection;
import hive.model.GameProgress;
import hive.model.game.Game;

/**
 *
 * @author Thomas
 */
public class GameController
{
    public GameProgress progress;
    
    public GameSelection selection;
    
    public GameController(Game game)
    {
        this.progress = new GameProgress(game);
        this.selection = new NoGameSelection();
    }
}
