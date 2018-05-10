/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.game.utildata;

import hive.model.board.Cell;
import hive.model.insects.InsectType;
import java.util.ArrayList;
import java.util.EnumMap;

/**
 *
 * @author Thomas
 */
public class PositionsPerInsect extends EnumMap<InsectType, ArrayList<Cell>>
{
    public PositionsPerInsect()
    {
        super(InsectType.class);
        for(InsectType type : InsectType.values())
        {
            put(type, new ArrayList<>());
        }
    }
    
}
