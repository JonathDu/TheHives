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
public class SettingsGesture
{

    private static final String SCORES_PATH = "src/properties/settings/setting.txt";

    private final File file;
    private final HashMap<String, String> settings;

    public SettingsGesture()
    {
        file = new File(SCORES_PATH);
        settings = readSettings();
    }
    
    public String getSetting(String name)
    {
        return settings.get(name);
    }

    public void setSetting(String name, String value)
    {
        settings.put(name, value);
        writeScorePerPlayer();
    }

    private HashMap<String, String> readSettings()
    {
        HashMap<String, String> _settings = new HashMap<>();
        BufferedReader reader = null;
        try
        {
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null)
            {
                _settings.put(line.split("=")[0], line.split("=")[1]);
                line = reader.readLine();
            }
            return _settings;
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
            return _settings;
        }
    }

    private void writeScorePerPlayer()
    {
        BufferedWriter writer = null;
        try
        {
            writer = new BufferedWriter(new FileWriter(file));
            for (Map.Entry<String, String> entry : settings.entrySet())
            {
                String key = entry.getKey();
                String value = entry.getValue();
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
