/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.players;

import hive.model.insects.InsectType;
import java.util.EnumMap;

/**
 *
 * @author Thomas
 */
public class PlayerCollection extends EnumMap<InsectType, Integer>
{
    public PlayerCollection()
    {
        super(InsectType.class);
    }
}
