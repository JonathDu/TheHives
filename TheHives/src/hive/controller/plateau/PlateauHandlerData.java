/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.controller.plateau;

import hive.controller.plateau.GameController;
import hive.model.GameProgress;
import hive.model.game.Game;
import hive.vue.InterfacePlateau;

/**
 *
 * @author Thomas
 */
public abstract class PlateauHandlerData
{
    protected GameController controller;
    
    protected Game game;
    protected InterfacePlateau uiPlateau;
    protected GameProgress progress;
    
    public PlateauHandlerData(GameController controller)
    {
        this.controller = controller;
        
        this.game = controller.game;
        this.uiPlateau = controller.uiPlateau;
        this.progress = new GameProgress(game);
    }
}
