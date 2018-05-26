/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.controller.plateau.handlers;

import hive.controller.plateau.PlateauController;
import hive.controller.plateau.graphicaction.ActionGraphicUpdaterIASelect;
import hive.controller.plateau.graphicaction.ActionGraphicUpdater;
import hive.controller.plateau.graphicaction.ActionGraphicUpdaterIADeselect;
import hive.model.players.decisions.IADecision;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 *
 * @author lucas
 */
public class IAPlayerHandler extends PlateauHandlerData implements EventHandler<ActionEvent>
{

    public IAPlayerHandler(PlateauController controller)
    {
        super(controller);
    }

    @Override
    public void handle(ActionEvent event)
    {
        if (!(progress.game.state.turn.getCurrent().decision instanceof IADecision))
        {
            throw new RuntimeException("IADecision attendu");
        }

        System.out.println("IA FAIT ACTION");

        if (game.state.data.trace.size() > 2)
        {
            ActionGraphicUpdaterIADeselect gUpdaterDeselect = new ActionGraphicUpdaterIADeselect(uiPlateau, game);
            progress.game.state.data.trace.get(progress.game.state.data.trace.size() - 2).accept(gUpdaterDeselect);
        }

        progress.doAction();

        ActionGraphicUpdater gUpdater = new ActionGraphicUpdater(uiPlateau, progress.game);
        progress.game.state.data.trace.peek().accept(gUpdater);

        ActionGraphicUpdaterIASelect gUpdaterSelect = new ActionGraphicUpdaterIASelect(uiPlateau, progress.game);
        progress.game.state.data.trace.peek().accept(gUpdaterSelect);
        
        controller.startOfTurnInfos();
    }
}
