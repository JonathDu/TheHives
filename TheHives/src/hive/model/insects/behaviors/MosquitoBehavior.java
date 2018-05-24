/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.insects.behaviors;

import hive.model.board.Cell;
import hive.model.game.GameState;
import hive.model.insects.InsectBehavior;
import hive.model.players.actions.Action;
import java.util.ArrayList;
import java.util.function.Consumer;

/**
 *
 * @author Thomas
 */
public class MosquitoBehavior implements InsectBehavior
{
    @Override
    public void consumeDestinations(GameState state, Cell cell, Consumer<Cell> consumer)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isFree(GameState state, Cell cell)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public int hashCode()
    {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        return true;
    }
    
}
