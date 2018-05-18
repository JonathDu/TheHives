/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.players.decisions.cerveau.geniteurs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Quentin
 */
public class RepertoryFamily {
    
    public static void repertory(ArrayList<Integer>[] family) throws FileNotFoundException, IOException {
        FileOutputStream fos;
        File file = new File("family.txt");
        fos = new FileOutputStream(file);
        // ecriture de la mere 
        for(int i =0; i< family[0].size();i++){
            fos.write(family[0].get(i));
        }/*
        // ecriture du pere 
        for(int i =0; i< family[1].size();i++){
            fos.write(family[1].get(i));
        }
        */
        //
        
    }
    
}
