/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.players.decisions.cerveau.main;

import hive.model.players.decisions.cerveau.AdamEtEve;

/**
 *
 * @author Quentin
 */
public class GenerationFilsAlea {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AdamEtEve AE= new AdamEtEve(5);
        AE.generate("generationBeta");
    }
    
}
