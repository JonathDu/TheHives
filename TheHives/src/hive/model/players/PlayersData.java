/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.players;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 *
 * @author Thomas
 */
public class PlayersData extends TreeMap<Player, PlayerData>
{
    public PlayersData()
    {
        super((p1, p2) -> p1 != p2 ? 0 : -1);
    }
    
    public PlayersData(Player player1, PlayerData data1, Player player2, PlayerData data2)
    {
        this();
        put(player1, data1);
        put(player2, data2);
    }
}