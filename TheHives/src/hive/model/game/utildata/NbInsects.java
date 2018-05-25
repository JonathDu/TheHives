/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.game.utildata;

import hive.model.game.utildata.PositionsPerInsect;
import hive.model.insects.InsectType;
import java.util.EnumMap;

/**
 *
 * @author Thomas
 */
public class NbInsects extends EnumMap<InsectType, Integer>
{
    public NbInsects()
    {
        super(InsectType.class);
    }
}
