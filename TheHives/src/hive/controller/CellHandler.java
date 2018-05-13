/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.controller;

import hive.model.board.Honeycomb;
import hive.model.game.Game;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import util.Vector2i;

/**
 *
 * @author Thomas
 */
public class CellHandler implements EventHandler<MouseEvent>
{
    Game game;
    Honeycomb comb;
    
    
    public CellHandler(Game game, Vector2i pos)
    {
        this.game = game;
        this.comb = game.state.board.getHexagon(pos);
    }
    
    @Override
    public void handle(MouseEvent event)
    {
        if(event.getEventType() == MouseEvent.MOUSE_CLICKED)
        {
            
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
