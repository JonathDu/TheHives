/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.controller.plateauscene.game;

import hive.model.players.decisions.IADecision;
import javafx.animation.Animation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 *
 * @author lucas
 */
public final class FrameHandler extends PlateauHandlerData implements EventHandler<ActionEvent>
{
    public FrameHandler(GameController controller)
    {
        super(controller);
    }
    
    @Override
    public void handle(ActionEvent event)
    {
        if(controller.game.state.turn.getCurrent().decision instanceof IADecision && controller.timerJouerIA.getStatus() != Animation.Status.RUNNING)
            controller.timerJouerIA.playFromStart();
    }
}
