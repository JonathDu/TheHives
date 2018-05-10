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
    public Honeycomb comb;
    public int index;
    
    public Cell(Honeycomb comb)
    {
        this.comb = comb;
        this.index = comb.stack().size();
    }

    public Cell(Honeycomb comb, int index)
    {
        this.comb = comb;
        this.index = index;
    }
    
    @Override
    public String toString()
    {
        return "( " + comb + " : " + comb.stack() + " at " + index + " )";
    }
}
