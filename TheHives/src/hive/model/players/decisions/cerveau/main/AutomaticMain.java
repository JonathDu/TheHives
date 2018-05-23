/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.players.decisions.cerveau.main;

import hive.model.players.decisions.cerveau.GeneratePhase;
import static hive.model.players.decisions.IA.Level.EHARD;
import hive.model.players.decisions.cerveau.EvaluationLearning;
import hive.model.players.decisions.cerveau.FirstPhase;
import hive.model.players.decisions.cerveau.Phase;

/**
 *
 * @author Coralie
 */
public class AutomaticMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Phase firstP;
        Phase selection ;
        Phase generation;
        EvaluationLearning boss;
        EvaluationLearning winEval;
        do{
            do{
                firstP = new FirstPhase();
            }while(firstP.isNoWinners());
            winEval = new EvaluationLearning(firstP.getNewGeneration().getSon()[0]);
            selection = new SelectedPhase(winEval,EHARD);
        }while(selection.isNoWinners());
        
        boss = winEval;
        for(int i =0 ; i<2 ; i++){
            generation = new GeneratePhase(boss,firstP.getNewGeneration().getSon(),firstP.getDossierSuivant());
            winEval = new EvaluationLearning(generation.getNewGeneration().getSon()[0]);
            if(generation.isNoWinners()){
                System.out.println("Il n'y a pas de gagnant à la génération "+i);
            }
            else{
                selection = new SelectedPhase(boss,winEval);
                if(!selection.isNoWinners()){
                    boss = winEval;
                }
            }
        }
        
        
    }
    
}
