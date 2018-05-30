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
public class MyProperties
{

    private static String PROPERTIES_PATH;
    private final File file;
    protected final HashMap<String, String> map;

    public MyProperties(String filePath)
    {
        super();
        PROPERTIES_PATH = filePath;
        file = new File(PROPERTIES_PATH);
        try
        {
            file.createNewFile(); //ne fait rien si le fichier existe deja
        } catch (IOException ex)
        {
            Logger.getLogger(MyProperties.class.getName()).log(Level.SEVERE, null, ex);
        }
        map = readProperties();
    }

    public HashMap<String, String> getProperties()
    {
        return map;
    }

    public String get(String key)
    {
        return map.get(key);
    }

    public void set(String key, String value)
    {
        map.put(key, value);
        writeProperties(map);
    }

    private HashMap<String, String> readProperties()
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
            Logger.getLogger(MyProperties.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
            try
            {
                reader.close();
            } catch (IOException ex)
            {
                Logger.getLogger(MyProperties.class.getName()).log(Level.SEVERE, null, ex);
            }
            return _settings;
        }
    }

    private void writeProperties(HashMap<String, String> map)
    {
        BufferedWriter writer = null;
        try
        {
            writer = new BufferedWriter(new FileWriter(file));
            for (Map.Entry<String, String> entry : map.entrySet())
            {
                String key = entry.getKey();
                String value = entry.getValue();
                String line = key + "=" + value + "\n";
                writer.write(line);
            }
        } catch (IOException ex)
        {
            Logger.getLogger(MyProperties.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
            try
            {
                writer.close();
            } catch (IOException ex)
            {
                Logger.getLogger(MyProperties.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
