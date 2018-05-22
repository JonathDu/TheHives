/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.game;

import hive.model.players.actions.Action;
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
 * @param <T>
 */
public class LoaderXML<T>
{
    public void loadInFile(T x, String name) throws FileNotFoundException, IOException
    {
        File file = new File(name);
        if(!file.exists())
            file.createNewFile();
        try (XMLEncoder encoder = new XMLEncoder(new FileOutputStream(file)))
        {
            encoder.writeObject(x);
            encoder.flush();
        }
    }
    
    public T loadFromFile(String name) throws FileNotFoundException
    {
        T x;
        File file = new File(name);
        try (XMLDecoder decoder = new XMLDecoder(new FileInputStream(file)))
        {
            x = (T)decoder.readObject();
        }
        return x;
    }
}
