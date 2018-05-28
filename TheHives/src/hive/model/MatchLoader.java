/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model;

import hive.model.game.Game;
import java.io.FileNotFoundException;
import util.LoaderXML;

/**
 *
 * @author Thomas
 */
public class MatchLoader extends LoaderXML<Match>
{
    @Override
    public Match loadFromFile(String name) throws FileNotFoundException
    {
        Match match = super.loadFromFile(name);
        // links between hexagon are not serialized (because we have a circular matrix)
        // so we reload the board manually
        match.game.state.board.setHexagons();
        return match;
    }
}
