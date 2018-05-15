/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.controller.gamescene.game;

import hive.model.board.Cell;
import hive.model.board.Honeycomb;
import hive.model.game.Game;
import hive.model.players.actions.Action;
import hive.model.players.decisions.Decision;
import hive.model.players.decisions.HumanDecision;
import hive.vue.InterfaceRuche;
import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import util.Vector2i;

/**
 *
 * @author Thomas
 */
public class CellHandler implements EventHandler<MouseEvent>
{

    GameController controller;
    Game game;
    Cell cell;
    InterfaceRuche uiRuche;

    public CellHandler(GameController controller, InterfaceRuche uiRuche, Vector2i pos)
    {
        this.controller = controller;
        this.game = controller.progress.game;
        this.cell = new Cell(game.state.board.getHexagon(pos));
        this.uiRuche = uiRuche;
    }

    @Override
    public void handle(MouseEvent event)
    {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED)
        {
            Decision decision = game.state.turn.getCurrent().decision;
            if (decision instanceof HumanDecision)
            {
                //System.out.println(cell);

                HumanDecision human_decision = (HumanDecision) decision;

                switch (controller.builder.getState())
                {
                    case BEGIN:
                        System.out.println("Source selectionnée");
                        controller.builder.setSource(cell);
                        uiRuche.selectCell(cell.comb.pos); //MAJ graphique : mettre en evidence la source
                        uiRuche.surlignerCells(game.rules.getPossibleDestinations(game.state, cell)); //MAJ graphique : surligner les destinations
                        break;
                    case SOURCE_SELECTED:
                        if (cell != controller.builder.source) //si on ne clique pas sur la cellule deja selectionnée
                        {
                            System.out.println("Destination selectionnée");
                            controller.builder.setDestination(cell);
                            Action action = controller.builder.produce();
                            human_decision.setAction(action);
                            controller.progress.doAction();
                            ArrayList<Cell> cells = new ArrayList<>();
                            cells.add(controller.builder.source); //source
                            cells.add(cell); //destination
                            uiRuche.majCells(cells); // MAJ graphique : mettre a jour le deplacement
                            uiRuche.deselectCell(controller.builder.source.comb.pos); // MAJ graphique : on deselectionne la source
                            uiRuche.desurlignerCells(game.rules.getPossibleDestinations(game.state, controller.builder.source)); // MAJ graphique : desurligne les destinations possible de la sources
                        } else
                        {
                            System.out.println("Aucun changement : source = destination");
                        }
                        break;
                    case TILE_SELECTED:
                        System.out.println("Placement selectionné");
                        controller.builder.setPlacement(cell);
                        Action action = controller.builder.produce();
                        human_decision.setAction(action);
                        controller.progress.doAction();
                        //TODO : MAJ graphique : on deselectionne la tile
                        ArrayList<Cell> cells = new ArrayList<>();
                        uiRuche.majCells(cells); // MAJ graphique : met a jour la case ajoutée
                        cells.add(controller.builder.placement_or_destination); //destination
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
