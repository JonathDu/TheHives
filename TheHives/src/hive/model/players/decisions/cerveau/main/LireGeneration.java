/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.players.decisions.cerveau.main;

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
        readFile("Family.txt");
    }
    
}
