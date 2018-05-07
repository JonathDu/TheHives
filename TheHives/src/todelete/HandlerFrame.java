/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todelete;

import hive.model.intelligence.JoueurIA;
import javafx.animation.Animation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 *
 * @author lucas
 */
public final class HandlerFrame implements EventHandler<ActionEvent>
{
    private final EtatPartie e;
    
    public HandlerFrame(EtatPartie e)
    {
        this.e = e;
    }
    
    @Override
    public void handle(ActionEvent event)
    {
        if(e.partie.joueurCourant().comportement instanceof JoueurIA && e.timerJouerIA.getStatus() != Animation.Status.RUNNING)
            e.timerJouerIA.playFromStart();
    }
}