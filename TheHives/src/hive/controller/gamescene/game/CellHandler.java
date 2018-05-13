/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.controller.gamescene.game;

import hive.controller.gamescene.game.selectors.CellClickedSelector;
import hive.model.board.Cell;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Thomas
 */
public class CellHandler implements EventHandler<MouseEvent>
{
    GameController controller;
    Cell cell;
    
    
    public CellHandler(GameController controller, Cell cell)
    {
        this.controller = controller;
        this.cell = cell;
    }
    
    @Override
    public void handle(MouseEvent event)
    {
        if(event.getEventType() == MouseEvent.MOUSE_CLICKED)
        {
            controller.selection.accept(new CellClickedSelector(controller, event));
        }
    }
}
