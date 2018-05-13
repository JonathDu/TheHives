/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.controller.gamescene.game.selectors;

import hive.controller.gamescene.game.GameController;
import hive.controller.gamescene.game.selections.PutActionSelection;
import hive.controller.gamescene.game.selections.MoveActionSelection;
import hive.controller.gamescene.game.selections.NoGameSelection;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Thomas
 */
public class CellClickedSelector implements GameSelector
{
    GameController controller;
    MouseEvent event;
    
    public CellClickedSelector(GameController controller, MouseEvent event)
    {
        assert event.getEventType() == MouseEvent.MOUSE_CLICKED;
        this.controller = controller;
        this.event = event;
    }
    
    @Override
    public void visit(PutActionSelection selection)
    {
        switch(selection.state)
        {
        case BEGIN:
            // impossible
            break;
        case TILE_SELECTED:
            // code here
            break;
        case CELL_SELECTED:
            // impossible
            break;
        case END:
            // impossible
            break;
        }
        assert false;
    }
    
    @Override
    public void visit(MoveActionSelection selection)
    {
        switch(selection.state)
        {
        case BEGIN:
            // code here
            break;
        case SOURCE_SELECTED:
            // code here
            break;
        case DESINATION_SELECTED:
            // impossible
            break;
        case END:
            // impossible
            break;
        }
    }

    @Override
    public void visit(NoGameSelection selection)
    {
        controller.selection = new MoveActionSelection();
        controller.selection.accept(this);
    }
}
