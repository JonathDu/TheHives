/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.controller.gamescene.game.handlers;

import hive.controller.gamescene.game.GameController;
import hive.model.board.Cell;
import hive.model.board.Tile;
import hive.model.game.Game;
import hive.model.players.actions.Action;
import hive.model.players.decisions.HumanDecision;
import hive.vue.InterfacePlateau;
import java.util.ArrayList;
import java.util.Arrays;
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
