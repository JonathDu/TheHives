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
public class Selection {
    int[] nbOfVictory;
    
    public Selection(){
        nbOfVictory = new int[15];
        for(int i =0 ; i<15 ; i++){
            nbOfVictory[i]=0;
        }
    }
    public Selection(int nbSon){
        nbOfVictory = new int[nbSon];
        for(int i =0 ; i<nbSon ; i++){
            nbOfVictory[i]=0;
        }
    }
    
    public void addVictory(int son){
        nbOfVictory[son]++;
    }
    
    
    
}
