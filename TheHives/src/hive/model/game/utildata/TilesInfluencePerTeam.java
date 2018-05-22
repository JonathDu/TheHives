/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.game.utildata;

import hive.model.players.TeamColor;
import java.util.EnumMap;

/**
 * To know for each team what are tiles influence
 * @author Thomas
 */
public class TilesInfluencePerTeam extends EnumMap<TeamColor, TilesInfluence>
{
    public TilesInfluencePerTeam()
    {
        super(TeamColor.class);
        for(TeamColor tc : TeamColor.values())
        {
            put(tc, new TilesInfluence());
        }
    }
}
