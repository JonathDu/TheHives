/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.players.decisions.cerveau;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
/**
 *
 * @author Quentin
 */
public class RepertoryFamily {
    
    public static void repertory(ArrayList<Integer>[] family,String nom) throws FileNotFoundException, IOException {
        File file = new File ("src\\hive\\model\\players\\decisions\\cerveau\\geniteurs\\"+nom);
        try (PrintWriter fos = new PrintWriter ("src\\hive\\model\\players\\decisions\\cerveau\\geniteurs\\"+nom)) {
        
            // ecriture de la mere 
            String res;
            System.out.println("coucou\n");
            fos.print("mere : ");
            for(int i =0; i< family[0].size();i++){
                res =family[0].get(i)+" ";
                fos.write(res);
            }
            fos.print("\npere : ");
            // ecriture des fils 
            for(int i =0; i< family[1].size();i++){
                fos.print(family[1].get(i)+" ");
            }
            fos.print("\nbrother : ");
            // ecriture des fils 
            for(int i =0; i< family[2].size();i++){
                fos.print(family[2].get(i)+" ");
            }
            fos.print("\nfils : \n");
            //les fils :
            for(int j=3;j<family.length;j++){
                for(int i =0; i< family[j].size();i++){
                    fos.print(family[j].get(i)+" ");
                }
                fos.print("     \n");
            }
            fos.print("\n\n\n");
            fos.close();
        }
    }
    public static void clearFile(String nom) throws IOException{
        FileWriter fos = new FileWriter("src\\hive\\model\\players\\decisions\\cerveau\\geniteurs\\"+nom);
        fos.write("");      
    }
    
    public static void readFile(String nom) throws IOException{
        FileReader fos = new FileReader("src\\hive\\model\\players\\decisions\\cerveau\\geniteurs\\"+nom);
         char[] buf = new char[1];
        int n ;
         while ((n = fos.read(buf)) >= 0) {
             System.out.print(buf);
         }
         System.out.println();
    }
}
