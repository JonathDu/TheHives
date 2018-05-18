/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.players.decisions.cerveau.generationAlpha;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Coralie
 */
public class AdamEtEve {

    public void generate() {
        FileOutputStream fos;
        Random rnd= new Random();
        ObjectOutputStream oos;
        ArrayList<Integer> l = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            try {
                File file = new File("fils"+i+".txt");
                fos = new FileOutputStream(file);
                 l.clear();
                for(int j =0;i<15;i++){
                    l.add(rnd.nextInt(101));
                }
                oos= new ObjectOutputStream(fos);
                oos.writeObject(l);
                fos.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(AdamEtEve.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(AdamEtEve.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}

