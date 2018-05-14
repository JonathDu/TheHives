/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.controller.gamescene.game.selectors;

import hive.controller.gamescene.game.selections.MoveActionSelection;
import hive.controller.gamescene.game.selections.NoGameSelection;
import hive.controller.gamescene.game.selections.PutActionSelection;

/**
 *
 * @author Thomas
 */
public interface GameSelector
{
    public void visit(NoGameSelection selection);
    public void visit(PutActionSelection selection);
    public void visit(MoveActionSelection selection);
}
