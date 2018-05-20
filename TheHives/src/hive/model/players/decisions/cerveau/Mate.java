/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.players.decisions.cerveau;

import static hive.model.players.decisions.cerveau.RepertoryFamily.repertory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Coralie
 */
public final class Mate {

    ArrayList<Integer>[] son;
    int nbSon;

    public ArrayList<Integer>[] getSon() {
        return son;
    }

    public int getNbSon() {
        return nbSon;
    }

    public Mate(ArrayList<Integer> mother, ArrayList<Integer> father,ArrayList<Integer> brother, int numberSon) {
        nbSon = numberSon;
        try {
            init(numberSon);
            son[0] = mother;
            son[1] = father;
            son[2] = brother;
            Mates(mother, father, (numberSon/3)-1, 3);
            Mates(mother, brother, (numberSon/3)-1,6);
            Mates(mother, father, (numberSon/3)-1, 9);
            repertory(son);
        } catch (IOException ex) {
            Logger.getLogger(Mate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Mates(ArrayList<Integer> mother, ArrayList<Integer> father, int nbSon, int from) {
        init(15);
        Random rnd = new Random();
        int alea;
        int aleaStat;
        for (int i = from; i < nbSon+from; i++) { // pour chaque enfant
            for (int j = 0; j < 27; j++) { //pour chaque allèles
                alea = rnd.nextInt(100);
                if (alea <= 9) { // mutation
                    aleaStat = rnd.nextInt(101);
                    son[i].add(aleaStat);
                } else if (alea <= 54) {
                    son[i].add(mother.get(j));

                } else {
                    son[i].add(father.get(j));
                }
            }
        }
    }

    private void init(int taille) {
        son = new ArrayList[taille];
        for (int i = 0; i < taille; i++) {
            son[i] = new ArrayList<>(300);
        }

    }

    @Override
    public String toString() {
        String res = "";
        for (int i = 0; i < nbSon; i++) {
            res += "fils" + i + " : ";
            res += son[i].toString();
            res += "\n";
        }
        return res;
    }

}
