/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.controller.gamescene.game.handlers;

import hive.controller.gamescene.game.GameController;
import hive.model.board.Cell;
import hive.model.players.actions.Action;
import hive.model.players.decisions.HumanDecision;
import hive.vue.InterfaceRuche;
import java.util.ArrayList;

/**
 *
 * @author lucas
 */
public class HandlersUtils
{

    public static void moveOnBoard(GameController controller, HumanDecision human_decision, Cell cell, InterfaceRuche uiRuche)
    {
        //TODO : tester si le move est possible ??? connexité etc ...

        /* ACTION BUILDER */
        controller.builder.setDestination(cell);
        Action action = controller.builder.produce(); //move
        human_decision.setAction(action);
        controller.progress.doAction();

        /* MAJ GRPAHIQUE */
        ArrayList<Cell> cells = new ArrayList<>();
        cells.add(controller.builder.source); //source
        cells.add(cell); //destination
        uiRuche.majCells(cells); // MAJ graphique : mettre a jour le deplacement
        uiRuche.deselectCell(controller.builder.source.comb.pos); // MAJ graphique : on deselectionne la source
        uiRuche.desurlignerCells(controller.builder.possibleDestinations); // MAJ graphique : desurligne les destinations possible de la sources   
    }

    public static void putOnBoard(GameController controller, HumanDecision human_decision, Cell cell, InterfaceRuche uiRuche)
    {
        //TODO : tester si le put est possible ??? connexité etc ...

        /* ACTION BUILDER */
        controller.builder.setPlacement(cell);
        Action action = controller.builder.produce(); //put
        human_decision.setAction(action);
        controller.progress.doAction();

        /* MAJ GRPAHIQUE */
        //TODO : MAJ graphique : on deselectionne la tile
        ArrayList<Cell> cells = new ArrayList<>();
        cells.add(controller.builder.placement_or_destination); //destination
        uiRuche.majCells(cells); // MAJ graphique : met a jour la case ajoutée
    }
}
