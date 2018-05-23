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
    boolean noWarrior;
    
    public Selection(){
        nbOfVictory = new int[15];
        for(int i =0 ; i<15 ; i++){
            nbOfVictory[i]=0;
        }
        nbSon = 15;
        noWarrior=false;
    }
    public Selection(int nbSon){
        nbOfVictory = new int[nbSon];
        for(int i =0 ; i<nbSon ; i++){
            nbOfVictory[i]=0;
        }
        this.nbSon= nbSon;
        noWarrior=false;
    }
    
    public void addVictory(int son){
        nbOfVictory[son]++;
    }

    public boolean isNoWarrior() {
        return noWarrior;
    }
    
    public int[] theBestWinners(int[] turnWin){
        
        int min[] = new int[3];
        int imin = 0;
        int n =0;
        do{
            imin = n;
            n++;
        }while( n<nbSon && turnWin[imin]==0);
        if(imin==nbSon){
            noWarrior = true;
            min[0]=0;
            min[1]=1;
            min[2]=2;
            return min;
        }
        for(int i =0; i<3;i++){
            for(int j=1; j<nbSon;j++){
                if(turnWin[j]!=0 && turnWin[j]<turnWin[imin]){
                    imin = j;
                }
            }
            min[i]=imin;
            turnWin[imin]= 0;
            imin=0;
            n=0;
            do{
                imin = n;
                n++;
            }while(n<nbSon && turnWin[imin]==0);
            if(i==0 && n==nbSon){
                noWarrior = true;
                min[1]=0;
                min[2]=1;
                return min; 
            }
            if(i==1 && n==nbSon){
                noWarrior = true;
                min[2]=0;
                return min; 
            }
            
        }
        return min;
        
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
