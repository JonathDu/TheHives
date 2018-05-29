/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.controller.plateau.handlers.mousehandlers;

import hive.controller.plateau.PlateauController;
import hive.controller.plateau.graphicaction.ActionGraphicUpdaterDeselect;
import hive.controller.plateau.handlers.PlateauHandlerData;
import hive.model.players.decisions.HumanDecision;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author lucas
 */
public abstract class PlateauHandler extends PlateauHandlerData implements EventHandler<MouseEvent>
{

    public PlateauHandler(PlateauController controller)
    {
        super(controller);
    }

    @Override
    public void handle(MouseEvent event)
    {
        if (!(game.state.turn.getCurrent().decision instanceof HumanDecision))
        {
            return;
        }

        if (!progress.game.state.data.trace.isEmpty())
        {
            ActionGraphicUpdaterDeselect gUpdaterDeselect = new ActionGraphicUpdaterDeselect(uiPlateau, game);
            progress.game.state.data.trace.peek().accept(gUpdaterDeselect);
        }
        
        handlePlateau(event);
    }
    
    public abstract void handlePlateau(MouseEvent event);
}
