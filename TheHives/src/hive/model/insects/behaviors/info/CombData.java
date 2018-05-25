/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.insects.behaviors.info;

import hive.model.board.Cell;
import hive.model.board.Honeycomb;

/**
 *
 * @author Thomas
 */
public class CombData
{
    public Honeycomb comb;
    public CombInfo info;
    
    public CombData(Honeycomb comb, CombInfo info)
    {
        this.comb = comb;
        this.info = info;
    }
}
