/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.controller;

import hive.model.Match;
import hive.model.MatchLoader;
import hive.model.game.Game;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import util.LoaderXML;

/**
 *
 * @author lucas
 */
public class SavesGesture
{
    private static final String SAVES_DIR_PATH = System.getProperty("user.dir") + "/src/savefiles/";

    
    public static Match loadGame(String fileName)
    {
        LoaderXML<Match> loader = new MatchLoader();
        Match match = null;
        try
        {
            match = loader.loadFromFile(SAVES_DIR_PATH + fileName);
        } catch (FileNotFoundException ex)
        {
            System.err.println("PAS DE FICHIER TROUVE");
        }
        return match;
    }

    public static void saveGame(Match match, String fileName)
    {
        LoaderXML<Match> loader = new MatchLoader();
        try
        {
            loader.loadInFile(match, SAVES_DIR_PATH + fileName);
        } catch (IOException ex)
        {
            System.err.println("PAS DE FICHIER TROUVE");
        }
    }
    
    public static ArrayList<String> getSavedFileNames()
    {
        File folder = new File(SAVES_DIR_PATH);
        File[] listOfFiles = folder.listFiles();
        ArrayList<String> files = new ArrayList<>();
        for (File file : listOfFiles)
        {
            if (file.isFile())
            {
                files.add(file.getName());
            }
        }
        return files;
    }
 
}
