/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.insects.behaviors;

import hive.model.board.Cell;

/**
 *
 * @author Thomas
 * @param <I>
 */
public class CellInfo<I>
{
    Cell cell;
    I info;
    
    public CellInfo(Cell cell, I info)
    {
        this.cell = cell;
        this.info = info;
    }
}
