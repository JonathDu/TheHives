/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lucas
 */
public class ScoresGesture
{

    private static final String SCORES_PATH = "src/properties/scores/scores.txt";

    private final File file;
    private final HashMap<String, Integer> scorePerPlayer;

    public ScoresGesture()
    {
        file = new File(SCORES_PATH);
        scorePerPlayer = readScorePerPlayer();
    }

    public void setScoreFor(String playerName)
    {
        int score = scorePerPlayer.getOrDefault(playerName, 0) + 1;
        scorePerPlayer.put(playerName, score);
        writeScorePerPlayer();
    }

    public HashMap<String, Integer> getScorePerPlayer()
    {
        return scorePerPlayer;
    }

    private HashMap<String, Integer> readScorePerPlayer()
    {
        HashMap<String, Integer> _scorePerPlayers = new HashMap<>();
        BufferedReader reader = null;
        try
        {
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null)
            {
                _scorePerPlayers.put(line.split("=")[0], Integer.valueOf(line.split("=")[1]));
                line = reader.readLine();
            }
            return _scorePerPlayers;
        } catch (IOException ex)
        {
            Logger.getLogger(ScoresGesture.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
            try
            {
                reader.close();
            } catch (IOException ex)
            {
                Logger.getLogger(ScoresGesture.class.getName()).log(Level.SEVERE, null, ex);
            }
            return _scorePerPlayers;
        }
    }

    private void writeScorePerPlayer()
    {
        BufferedWriter writer = null;
        try
        {
            writer = new BufferedWriter(new FileWriter(file));
            for (Map.Entry<String, Integer> entry : scorePerPlayer.entrySet())
            {
                String key = entry.getKey();
                Integer value = entry.getValue();
                String line = key + "=" + String.valueOf(value) + "\n";
                writer.write(line);
            }
        } catch (IOException ex)
        {
            Logger.getLogger(ScoresGesture.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
            try
            {
                writer.close();
            } catch (IOException ex)
            {
                Logger.getLogger(ScoresGesture.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
