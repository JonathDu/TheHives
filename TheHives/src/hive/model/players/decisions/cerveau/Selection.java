/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.players.decisions.cerveau;

import static java.lang.Integer.max;
import static java.lang.Integer.min;

/**
 *
 * @author Coralie
 */
public class Selection {
    int[] nbOfVictory;
    int nbSon;
    public Selection(){
        nbOfVictory = new int[15];
        for(int i =0 ; i<15 ; i++){
            nbOfVictory[i]=0;
        }
        nbSon = 15;
    }
    public Selection(int nbSon){
        nbOfVictory = new int[nbSon];
        for(int i =0 ; i<nbSon ; i++){
            nbOfVictory[i]=0;
        }
        this.nbSon= nbSon;
    }
    
    public void addVictory(int son){
        nbOfVictory[son]++;
    }
    
    public int[] lesGagnants(){
        int max[] = new int[2];
        int maxValue, maxValue2;
        
        if(nbOfVictory[0]>nbOfVictory[1]){
            max[0]= 0;
            maxValue = nbOfVictory[0];
            max[1]= 1;
            maxValue2 = nbOfVictory[1];
        }
        else{
            max[0]= 1;
            maxValue = nbOfVictory[1];
            max[1]= 0;
            maxValue2 = nbOfVictory[0];
        }
        for(int i = 2 ; i < nbSon ; i++){
            if(nbOfVictory[i]>maxValue){
                if(nbOfVictory[i]>maxValue2){
                    max[1]= max[0];
                    maxValue2 = maxValue;
                    max[0]= i;
                    maxValue = nbOfVictory[i];
                    
                }
                max[1]= i;
                maxValue2 = nbOfVictory[i];
            }
        }
        return max;
    }
    
    
}
