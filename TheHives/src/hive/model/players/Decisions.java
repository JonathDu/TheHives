/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.players;

import java.util.EnumMap;

/**
 *
 * @author Thomas
 */
public class Decisions extends EnumMap<ActionType, Decision>
{
    public Decisions()
    {
        super(ActionType.class);
    }
}
