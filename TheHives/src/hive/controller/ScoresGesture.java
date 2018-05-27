/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lucas
 */
public class ScoresGesture
{

    private final String scoresPropertiesPath;
    private final Properties properties;

    public ScoresGesture()
    {
        scoresPropertiesPath = "properties/scores/scores.properties";
        properties = new Properties();
        try
        {
            properties.load(new FileInputStream(scoresPropertiesPath));
        } catch (IOException ex)
        {
            Logger.getLogger(ScoresGesture.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getScoreFor(String playerName)
    {
        return Integer.parseInt(properties.getProperty(playerName));
    }

    public void setScoreFor(String playerName, int playerScore)
    {
        if (getPlayersNames().contains(playerName))
        {
            properties.setProperty(playerName, String.valueOf(playerName));
        } else
        {
            properties.put(playerName, String.valueOf(playerName));
        }
    }

    public HashMap<String, String> getScores()
    {
        HashMap<String, String> map = new HashMap<>();
        getPlayersNames().forEach((name) ->
        {
            map.put(name, properties.getProperty(name));
        });
        return map;
    }

    private Set<String> getPlayersNames()
    {
        return properties.stringPropertyNames();
    }

}
