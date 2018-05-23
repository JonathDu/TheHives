/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.players.decisions.cerveau.main;

import hive.model.players.decisions.cerveau.AdamEtEve;
import hive.model.players.decisions.cerveau.EvaluationLearning;
import hive.model.players.decisions.cerveau.Mate;
import hive.model.players.decisions.cerveau.RepertoryFamily;
import static hive.model.players.decisions.cerveau.RepertoryFamily.readFile;
import java.io.IOException;

/**
 *
 * @author Quentin
 */
public class LireGeneration {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        AdamEtEve AE = new AdamEtEve(3);
        Mate newGeneration;
        EvaluationLearning[] evaluations = AE.generate("generationAlpha");
        newGeneration = new Mate(evaluations[0].getEvalValues(), evaluations[1].getEvalValues(),evaluations[2].getEvalValues(), 12);
        System.out.println("read!");
        RepertoryFamily.clearFile("Family.txt");
        RepertoryFamily.readFile("Family.txt");
    }
    
}
