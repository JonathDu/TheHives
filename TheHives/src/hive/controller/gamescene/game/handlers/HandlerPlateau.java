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
    //Cell action_source;
    //Cell action_placement_or_destination;
    //Tile action_tile;

    public HandlerPlateau(GameController controller, InterfacePlateau uiPlateau)
    {
        this.controller = controller;
        this.game = controller.progress.game;
        this.uiPlateau = uiPlateau;
        //action_source = controller.builder.source;
        //action_placement_or_destination = controller.builder.placement_or_destination;
        //action_tile = controller.builder.tile;
    }

    @Override
    abstract public void handle(MouseEvent event);

    public void moveOnBoard(HumanDecision human_decision, Cell cellClicked)
    {
        if (!controller.builder.possibleDestinations.contains(new Cell(cellClicked.comb)))
        {
            System.err.println("Destination impossible");
            return;
        }

        doAction(human_decision, cellClicked);

        /* MAJ GRPAHIQUE */
        uiPlateau.ruche.majCells(new ArrayList<>(Arrays.asList(controller.builder.source, controller.builder.placement_or_destination)));
        uiPlateau.ruche.deselectCell(controller.builder.source.comb.pos);
        uiPlateau.ruche.desurlignerCells(controller.builder.possibleDestinations); 
    }

    public void putOnBoard(HumanDecision human_decision, Cell cellClicked)
    {
        if (!controller.builder.possibleDestinations.contains(new Cell(cellClicked.comb)))
        {
            System.err.println("Placement impossible");
            return;
        }

        doAction(human_decision, cellClicked);

        /* MAJ GRPAHIQUE */
        //TODO : MAJ graphique : on deselectionne la tile
        uiPlateau.ruche.majCells(new ArrayList<>(Arrays.asList(controller.builder.placement_or_destination))); // MAJ graphique : met a jour la case ajoutée
        uiPlateau.ruche.desurlignerCells(controller.builder.possibleDestinations);
    }
    
    private void doAction(HumanDecision human_decision, Cell cellClicked)
    {
        controller.builder.setPlacement(cellClicked);
        Action action = controller.builder.produce();
        human_decision.setAction(action);
        controller.progress.doAction();     
    }

}
