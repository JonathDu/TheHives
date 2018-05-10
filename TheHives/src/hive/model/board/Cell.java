/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.board;

import util.hexagons.Hexagon;

/**
 *
 * @author Thomas
 */
public class Cell
{
    public Hexagon<TilesStack> hexagon;
    public int index;
    TilesStack stack;
    
    public Cell(Hexagon<TilesStack> hexagon, int index)
    {
        this.hexagon = hexagon;
        this.index = index;
        this.stack = hexagon.getValue();
    }
}
