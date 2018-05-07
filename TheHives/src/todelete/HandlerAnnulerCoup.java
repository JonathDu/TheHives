/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todelete;

import hive.model.intelligence.JoueurHumain;
import hive.model.Joueur;
import hive.model.Partie;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author lucas
 */
public class HandlerAnnulerCoup implements EventHandler<MouseEvent>
{
    private final Partie partie;
    
    public HandlerAnnulerCoup(Partie partie)
    {
        this.partie = partie;
    }

    
    @Override
    public void handle(MouseEvent event)
    {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED)
        {
            Joueur j = partie.joueurCourant().comportement;
            System.out.println("yo");
            if(j instanceof JoueurHumain)
            {
                partie.faireAnnuler();
                partie.faireAnnuler();
            }
        }
    }
    
}
