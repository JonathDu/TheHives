/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.players;

import hive.model.board.Board;

/**
 *
 * @author Thomas
 */
public interface Decision
{
    public Action getAction(Board board);
}
