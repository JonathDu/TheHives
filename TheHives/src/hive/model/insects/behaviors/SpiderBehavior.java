/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.insects.behaviors;

import hive.model.board.Cell;
import hive.model.game.Game;
import hive.model.insects.InsectBehavior;
import java.util.ArrayList;

/**
 *
 * @author Thomas
 */
public class SpiderBehavior implements InsectBehavior
{

    @Override
    public ArrayList<Cell> getPossibleDestinations(Game game, Cell cell)
    {
        ArrayList<Cell> list = new ArrayList<>();
        
        return list;
    }
    
}
