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
public final class FrameHandler implements EventHandler<ActionEvent>
{
    private final GraphicGameState graphicGameState;
    
    public FrameHandler(GraphicGameState graphicGameState)
    {
        this.graphicGameState = graphicGameState;
    }
    
    @Override
    public void handle(ActionEvent event)
    {
        if(graphicGameState.game.state.turn.getCurrent().decision instanceof IADecision && graphicGameState.timerJouerIA.getStatus() != Animation.Status.RUNNING)
            graphicGameState.timerJouerIA.playFromStart();
    }
}
