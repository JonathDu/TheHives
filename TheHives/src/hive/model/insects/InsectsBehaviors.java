/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.insects;

import java.util.EnumMap;

/**
 *
 * @author Thomas
 */
public class InsectsBehaviors extends EnumMap<InsectType, InsectBehavior>
{
    public InsectsBehaviors()
    {
        super(InsectType.class);
    }
}
