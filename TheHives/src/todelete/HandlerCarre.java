/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todelete;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import hive.model.Coup;
import hive.model.intelligence.JoueurHumain;
import hive.model.Joueur;
import hive.model.OperationGaufre;
import hive.model.Partie;
import hive.view.InterfacePlateau;
import p.util.Position;
import util.Vector2;

/**
 * Handler quand la souris interagit avec un carré de la gaufre
 * @author Thomas
 */
public class HandlerCarre implements EventHandler<MouseEvent>
{
    private final Partie partie;
    private final InterfacePlateau i;
    private final Vector2<Integer> pos;
    
    public HandlerCarre(Partie partie, InterfacePlateau i, Vector2<Integer> pos)
    {
        this.partie = partie;
        this.i = i;
        this.pos = pos;
    }
    
    @Override
    public void handle(MouseEvent event)
    {
        Vector2<Double> cursor = new Vector2<>(event.getX(), event.getY());
        
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED)
        {
            Joueur j = partie.joueurCourant().comportement;
            
            if(j instanceof JoueurHumain)
            {
                JoueurHumain jh = (JoueurHumain) j;
                
                Coup c = new Coup(pos.x, pos.y);
                
                if(OperationGaufre.estCoupValide(partie.getGaufre(), c))
                {
                    jh.setCoup(new Coup(pos.x, pos.y));
                    partie.faireJouer();
                    i.actualiserGaufre();
                }
            }
        }
        else
            throw new RuntimeException("Clic sur le carré attendu");
    }
    
}
