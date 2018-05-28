/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.players.decisions.cerveau.main;

import hive.model.players.decisions.cerveau.AdamEtEve;
import hive.model.players.decisions.cerveau.EvaluationLearning;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Coralie
 */
public class AffichagBoss {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, FileNotFoundException {
        // TODO code application logic here
        AdamEtEve AE = new AdamEtEve(3);
        ArrayList<Integer> boss;
        int victory [] = new int[9];
        for(int j = 0; j<9;j++){
            victory[j]=0;
        }
        EvaluationLearning [] dreamTeam = new EvaluationLearning[9];
        for(int i=0 ; i <7;i++){
            try {
                boss = AE.dlBoss("CoralieBoss"+i+".txt");
                System.out.println("CoralieBoss"+i+".txt \n"+boss);
                
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(BattleRoyal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(BattleRoyal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }/*
        for(int i=0 ; i <2;i++){
        try {
        boss = AE.dlBoss("LucasTheBoss"+i+".txt");
        System.out.println("LucasTheBoss"+i+".txt \n"+boss);
        
        } catch (FileNotFoundException ex) {
        Logger.getLogger(BattleRoyal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
        Logger.getLogger(BattleRoyal.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        for(int i=0 ; i <2;i++){
        try {
        boss = AE.dlBoss("AdelinaTheBoss"+i+".txt");
        System.out.println("AdelinaTheBoss"+i+".txt \n"+boss);
        
        } catch (FileNotFoundException ex) {
        Logger.getLogger(BattleRoyal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
        Logger.getLogger(BattleRoyal.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        boss = AE.dlBoss("TheBigBossCoralie");
        System.out.println("TheBigBoss \n"+boss);*/
    }
    
}
