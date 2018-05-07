/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todelete;

import hive.model.intelligence.JoueurIA;
import hive.model.Partie;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 *
 * @author lucas
 */
public class HandlerJouerIA implements EventHandler<ActionEvent>
{
    private Partie p;
    
    public HandlerJouerIA(Partie p)
    {
        this.p = p;
    }

    @Override
    public void handle(ActionEvent event)
    {
        if(p.joueurCourant().comportement instanceof JoueurIA)
            p.faireJouer();
        else
            throw new RuntimeException("JoueurIA attendu");
    }
}