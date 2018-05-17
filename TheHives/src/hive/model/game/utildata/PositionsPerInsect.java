/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.game.utildata;

import hive.model.board.Cell;
import hive.model.insects.InsectType;
import java.util.EnumMap;
import java.util.HashSet;

/**
 *
 * @author Thomas
 */
public class PositionsPerInsect extends EnumMap<InsectType, HashSet<Cell>>
{
    public PositionsPerInsect()
    {
        super(InsectType.class);
        for(InsectType type : InsectType.values())
        {
            put(type, new HashSet<>());
        }
    }
}
