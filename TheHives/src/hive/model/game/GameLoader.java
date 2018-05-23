/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.game;

import util.LoaderXML;
import hive.model.board.Board;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author Thomas
 */
public class GameLoader extends LoaderXML<Game>
{
    @Override
    public Game loadFromFile(String name) throws FileNotFoundException
    {
        Game game = super.loadFromFile(name);
        // links between hexagon are not serialized (because we have a circular matrix)
        // so we reload the board manually
        game.state.board.setHexagons();
        return game;
    }
}
