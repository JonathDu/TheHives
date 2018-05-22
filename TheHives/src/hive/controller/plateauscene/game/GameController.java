/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.controller.plateauscene.game;

import hive.model.GameProgress;
import hive.model.game.Game;

/**
 *
 * @author Thomas
 */
public class GameController
{
    // model
    public GameProgress progress;
    
    // interface
    
    // controller
    public ActionBuilder builder;
    
    
    public GameController(Game game) // TODO +interface parameter
    {
        this.progress = new GameProgress(game);
        this.builder = new ActionBuilder();
    }
}
