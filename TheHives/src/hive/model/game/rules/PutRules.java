/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.game.rules;

import hive.model.board.Cell;
import hive.model.game.Game;
import java.util.ArrayList;

/**
 *
 * @author Thomas
 */
public interface PutRules
{
    public ArrayList<Cell> getPossibleDestinations(Game game);
    public int getMaxQueenTurn(Game game);
}
