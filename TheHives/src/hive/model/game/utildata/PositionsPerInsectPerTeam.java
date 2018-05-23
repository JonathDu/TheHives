/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.game.utildata;

import hive.model.players.TeamColor;
import java.util.EnumMap;

/**
 * To know for each team where are their tiles
 * @author Thomas
 */
public class PositionsPerInsectPerTeam extends EnumMap<TeamColor, PositionsPerInsect>
{
    public PositionsPerInsectPerTeam()
    {
        super(TeamColor.class);
        for(TeamColor color : TeamColor.values())
        {
            put(color, new PositionsPerInsect());
        }
    }
    
}
