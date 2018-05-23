/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.players.decisions.cerveau;

/**
 *
 * @author Coralie
 */
public class Phase {
    int[] winner;
    int theWinner;
    boolean noWinners;
    Mate newGeneration;
    EvaluationLearning[] evaluations;
    String dossier[];
    int dossierSuivant;
    
    
    public int[] getWinner() {
        return winner;
    }

    public int getTheWinner() {
        return theWinner;
    }

    public boolean isNoWinners() {
        return noWinners;
    }

    public Mate getNewGeneration() {
        return newGeneration;
    }

    public EvaluationLearning[] getEvaluations() {
        return evaluations;
    }

    public String[] getDossier() {
        return dossier;
    }

    public int getDossierSuivant() {
        return dossierSuivant;
    }
}
