/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model;

import hive.model.game.Game;
import hive.model.game.utildata.PositionsPerInsect;
import hive.model.game.utildata.PositionsPerInsectPerTeam;
import hive.model.game.utildata.TilesInfluence;
import hive.model.insects.InsectType;
import hive.model.players.TeamColor;
import java.util.EnumMap;

/**
 *
 * @author Thomas
 */
public class TilesData
{
    public PositionsPerInsectPerTeam positions = new PositionsPerInsectPerTeam();
    
    public Integer getFreeTiles(Game game, TeamColor color, InsectType type)
    {
        /*for(TeamColor tc : TeamColor.values())
        {
            put(tc, new TilesInfluence());
        }*/
        return 0;
    }
}

