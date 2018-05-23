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
    
    public void theBestLoosers(int[] turnLoose){
        int max[] = new int[2];
        int maxValue, maxValue2;
        
        if(turnLoose[0]>turnLoose[1]){
            max[0]= 0;
            maxValue = turnLoose[0];
            max[1]= 1;
            maxValue2 = turnLoose[1];
        }
        else{
            max[0]= 1;
            maxValue = turnLoose[1];
            max[1]= 0;
            maxValue2 = turnLoose[0];
        }
        for(int i = 2 ; i < turnLoose.length ; i++){
            if(turnLoose[i]>maxValue){
                if(turnLoose[i]>maxValue2){
                    max[1]= max[0];
                    maxValue2 = maxValue;
                    max[0]= i;
                    maxValue = turnLoose[i];
                    
                }
                max[1]= i;
                maxValue2 = turnLoose[i];
            }
        }
        addVictory(max[0]);
        addVictory(max[1]);
    }
    
    public int[] lesGagnants(){
        int max[] = new int[3];
        int imax = 0;
        for(int i =0; i<3;i++){
            for(int j=1; j<nbSon;j++){
                if(nbOfVictory[j]>nbOfVictory[imax]){
                    imax = j;
                }
            }
            max[i]=nbOfVictory[imax];
            nbOfVictory[imax]= (- 20);
            imax=0;
        }
        return max;
    }
    
    
}
