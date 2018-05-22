/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.controller.plateauscene.game.mousehandlers;

import hive.controller.plateauscene.game.GameController;
import hive.model.game.Game;
import hive.vue.InterfacePlateau;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author lucas
 */
public abstract class HandlerPlateau implements EventHandler<MouseEvent>
{

    GameController controller;
    Game game;
    InterfacePlateau uiPlateau;

    public HandlerPlateau(GameController controller, InterfacePlateau uiPlateau)
    {
        this.controller = controller;
        this.game = controller.progress.game;
        this.uiPlateau = uiPlateau;
    }

    @Override
    abstract public void handle(MouseEvent event);
}
