/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.controller.plateauscene;

import hive.controller.plateauscene.game.ActionBuilder;
import hive.controller.plateauscene.game.GameController;
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
