/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.players;

import java.util.ArrayList;

/**
 *
 * @author Thomas
 */
public class Players extends ArrayList<Player>
{
    public Players(Player p1, Player p2)
    {
        super();
        assert p1.color != p2.color;
        add(p1);
        add(p2);
    }
}
