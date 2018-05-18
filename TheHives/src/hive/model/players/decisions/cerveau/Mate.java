/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.players.decisions.cerveau;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Coralie
 */
public class Mate {
    ArrayList<Integer>[] son;
    
    Mate(ArrayList<Integer> mother, ArrayList<Integer> father, int numberSon){
        init(numberSon);
        son[0]= mother;
        son[1]= father;
        Random rnd = new Random();
        int alea;
        int aleaStat;
        for(int i = 2; i<numberSon ; i++){ // pour chaque enfant
            for(int j= 0 ; j<15 ; j++){ //pour chaque gène
                alea = rnd.nextInt(100);
                if(alea <= 1){ // mutation
                    aleaStat = rnd.nextInt(101);
                    son[i].add(aleaStat);
                }else if(alea <= 50){
                    son[i].add(mother.get(j));
                        
                }else{
                    son[i].add(father.get(j));
                }       
            }
        } 
    }
    
    Mate(ArrayList<Integer> mother, ArrayList<Integer> father){
        init(15);
        son[0]= mother;
        son[1]= father;
        Random rnd = new Random();
        int alea;
        int aleaStat;
        for(int i = 2; i<15 ; i++){ // pour chaque enfant
            for(int j= 0 ; j<15 ; j++){ //pour chaque gène
                alea = rnd.nextInt(100);
                if(alea <= 1){ // mutation
                    aleaStat = rnd.nextInt(101);
                    son[i].add(aleaStat);
                }else if(alea <= 50){
                    son[i].add(mother.get(j));
                        
                }else{
                    son[i].add(father.get(j));
                }       
            }
        } 
    }
    private void init(int taille){
        son = new ArrayList[taille];
        for(int i=0;i<taille;i++){
            son[i]=new ArrayList<>(300);
        }
        
    }
}
