/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.controller.plateau;

import hive.model.players.decisions.IADecision;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 *
 * @author lucas
 */
public class IAPlayerHandler extends PlateauHandlerData implements EventHandler<ActionEvent>
{
    public IAPlayerHandler(GameController controller)
    {
        super(controller);
    }

    @Override
    public void handle(ActionEvent event)
    {
        if (progress.game.state.turn.getCurrent().decision instanceof IADecision)
        {
            System.out.println("IA FAIT ACTION");
            progress.doAction();
            ActionGraphicUpdater gUpdater = new ActionGraphicUpdater(uiPlateau, progress.game);
            progress.game.state.data.trace.peek().accept(gUpdater);
            uiPlateau.majJoueurCourant(game.state.turn.getCurrent().color);
        } else
        {
            throw new RuntimeException("IADecision attendu");
        }
    }
}