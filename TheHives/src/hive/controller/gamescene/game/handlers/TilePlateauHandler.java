/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.controller.gamescene.game.handlers;

import hive.controller.gamescene.game.GameController;
import hive.model.board.Cell;
import hive.model.game.Game;
import hive.model.players.actions.Action;
import hive.model.players.decisions.Decision;
import hive.model.players.decisions.HumanDecision;
import hive.vue.InterfacePlateau;
import hive.vue.InterfaceRuche;
import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import util.Vector2i;

/**
 * Appelé lorsque l'on clique sur une cellule du plateau contenant quelque chose
 *
 * @author Thomas
 */
public class TilePlateauHandler implements EventHandler<MouseEvent>
{

    GameController controller;
    Game game;
    Cell cell;
    InterfacePlateau uiPlateau;
    InterfaceRuche uiRuche;

    public TilePlateauHandler(GameController controller, InterfacePlateau uiPlateau, Vector2i pos)
    {
        this.controller = controller;
        this.game = controller.progress.game;
        this.cell = new Cell(game.state.board.getHexagon(pos), game.state.board.getHexagon(pos).value().size() - 1);
        this.uiPlateau = uiPlateau;
        this.uiRuche = uiPlateau.ruche;
    }

    @Override
    public void handle(MouseEvent event)
    {
        System.out.println("--- TILE PLATEAU ---");
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED)
        {
            Decision decision = game.state.turn.getCurrent().decision;
            if (decision instanceof HumanDecision)
            {
                System.out.println(cell);

                HumanDecision human_decision = (HumanDecision) decision;

                switch (controller.builder.getState())
                {
                    case BEGIN:
                        System.out.println("Source selectionnée");

                        /* ACTION BUILDER */
                        controller.builder.setSource(cell);
                        controller.builder.setPossibleDestinations(game.rules.getPossibleDestinations(game.state, cell));

                        /* MAJ GRPAHIQUE */
                        uiRuche.selectCell(cell.comb.pos); //MAJ graphique : mettre en evidence la source
                        uiRuche.surlignerCells(controller.builder.possibleDestinations); //MAJ graphique : surligner les destinations
                        break;
                    case SOURCE_SELECTED:
                        if (cell != controller.builder.source) //si on ne clique pas sur la cellule deja selectionnée
                        {
                            System.out.println("Destination selectionnée");
                            HandlersUtils.moveOnBoard(controller, human_decision, cell, uiRuche);
                        } else
                        {
                            System.err.println("Aucun changement : source = destination");
                        }
                        break;
                    case TILE_SELECTED:
                        System.out.println("Placement selectionné");
                        HandlersUtils.putOnBoard(controller, human_decision, cell, uiRuche);
                        break;
                }
            }
        } else if (true) // autre evenement ? mouseOver ?
        {
            // information about the tile ? IA or not
            // etc
        }
    }
}
