/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.controller.gamescene.game.selectors;

import hive.controller.gamescene.game.GameController;
import hive.controller.gamescene.game.selections.MoveActionSelection;
import hive.controller.gamescene.game.selections.NoGameSelection;
import hive.controller.gamescene.game.selections.PutActionSelection;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Thomas
 */
public class InsectsCollectionClickedSelector implements GameSelector
{
    GameController controller;
    MouseEvent event;
    
    public InsectsCollectionClickedSelector(GameController controller, MouseEvent event)
    {
        assert event.getEventType() == MouseEvent.MOUSE_CLICKED;
        this.controller = controller;
        this.event = event;
    }
    @Override
    public void visit(PutActionSelection selection)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(MoveActionSelection selection)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(NoGameSelection selection)
    {
        controller.selection = new PutActionSelection();
        controller.selection.accept(this);
    }
    
}
