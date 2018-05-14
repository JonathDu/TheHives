/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.controller.gamescene;

import hive.controller.gamescene.game.ActionBuilder;
import hive.controller.gamescene.game.GameController;
import hive.model.GameProgress;

/**
 *
 * @author Thomas
 */
public class GameSceneController
{
    // model
    
    // interface
    
    // controller
    public GameController controller;
    
    public GameSceneController()
    {
        this.controller = new GameController(null); // TODO
    }
}
