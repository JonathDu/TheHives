/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.players.decisions.cerveau;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Coralie
 */
public class AdamEtEve {

    public EvaluationLearning[] generate(String Dossier) {
        EvaluationLearning[] salut = new EvaluationLearning[15];

        FileOutputStream fos;
        Random rnd= new Random();
        ObjectOutputStream oos;
        ArrayList<Integer> l = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            try {
                File file = new File("src\\hive\\model\\players\\decisions\\cerveau\\"+Dossier+"\\fils"+i+".txt");
                System.out.println(file.getAbsolutePath());
                fos = new FileOutputStream(file);
                 l.clear();
                for(int j =0;j<15;j++){
                    l.add(rnd.nextInt(101));
                }
                oos= new ObjectOutputStream(fos);
                oos.writeObject(l);
                fos.close();
                salut[i]=new EvaluationLearning("src\\hive\\model\\players\\decisions\\cerveau\\"+Dossier+"\\fils"+i+".txt");
                System.out.println("C'est initialisé");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(AdamEtEve.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(AdamEtEve.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        return salut;
    }
    
}

