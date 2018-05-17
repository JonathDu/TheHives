/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.insects.behaviors;

import hive.model.board.Cell;
import hive.model.game.GameState;
import hive.model.insects.InsectBehavior;
import java.util.ArrayList;

/**
 *
 * @author Thomas
 */
public class PillBugBehavior implements InsectBehavior
{

    @Override
    public ArrayList<Cell> getPossibleDestinations(GameState state, Cell cell)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isFree(GameState state, Cell cell)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
