/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.insects;

import hive.model.board.Cell;
import hive.model.game.GameState;
import java.util.ArrayList;

/**
 *
 * @author Thomas
 */
public interface InsectBehavior
{
    public ArrayList<Cell> getPossibleDestinations(GameState state, Cell cell);
}
