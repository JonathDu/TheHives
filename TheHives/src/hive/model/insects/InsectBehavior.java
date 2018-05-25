/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.insects;

import hive.model.board.Cell;
import hive.model.game.GameState;
import java.util.function.Consumer;

/**
 *
 * @author Thomas
 */
public interface InsectBehavior
{
    //public void consumeDestinations(GameState state, Cell cell, Consumer<Cell> valid, Consumer<CellInfo<?>> invalid);
    public void consumeDestinations(GameState state, Cell cell, Consumer<Cell> consumer);
    public boolean isFree(GameState state, Cell cell);
}
