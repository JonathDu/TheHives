/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.game.utildata;

import hive.model.board.Honeycomb;
import hive.model.players.TeamColor;
import java.util.EnumMap;

/**
 *
 * @author Thomas
 */
public class OccurencesPerTeamHoneycomb extends EnumMap<TeamColor, OccurencesPerHoneycomb>
{
    public OccurencesPerTeamHoneycomb()
    {
        super(TeamColor.class);
        for(TeamColor tc : TeamColor.values())
        {
            put(tc, new OccurencesPerHoneycomb());
        }
    }
}
