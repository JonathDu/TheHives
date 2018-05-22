/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.players.decisions.cerveau;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Quentin
 */
public class RepertoryFamily {
    
    public static void repertory(ArrayList<Integer>[] family,String nom) throws FileNotFoundException, IOException {
        FileWriter fos = new FileWriter("src\\hive\\model\\players\\decisions\\cerveau\\geniteurs\\"+nom, true);
        // ecriture de la mere 
        fos.write("     mere : ");
        for(int i =0; i< family[0].size();i++){
            fos.write(family[0].get(i)+" ");
        }
        fos.write("     pere : ");
        // ecriture des fils 
        for(int i =0; i< family[1].size();i++){
            fos.write(family[1].get(i)+" ");
        }
        fos.write("     brother : ");
        // ecriture des fils 
        for(int i =0; i< family[2].size();i++){
            fos.write(family[1].get(i)+" ");
        }
        fos.write("     \nfils : \n");
        //les fils :
        for(int j=3;j<family.length;j++){
            for(int i =0; i< family[1].size();i++){
                fos.write(family[1].get(i)+" ");
                if (j%3==2)
                    fos.write("\n");
            }
            fos.write("     ");
        }
        fos.write("\n\n\n");
        
    }
    public static void clearFile(String nom) throws IOException{
        FileWriter fos = new FileWriter("src\\hive\\model\\players\\decisions\\cerveau\\geniteurs\\"+nom);
        fos.write("");      
    }
}
