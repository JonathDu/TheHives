/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.insects;

import hive.model.board.Cell;
import hive.model.game.GameState;
import hive.model.insects.behaviors.info.CombData;
import hive.model.insects.behaviors.info.Info;
import java.util.function.Consumer;

/**
 *
 * @author Thomas
 */
public interface InsectBehavior
{
    public void consumeDestinations(GameState state, Cell cell, Consumer<Cell> consumer);
    public Info consumeDestinations(GameState state, Cell cell, Consumer<Cell> consumer, Consumer<CombData> info_giver);
    public boolean isFree(GameState state, Cell cell);
}
