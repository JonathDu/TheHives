/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.players.decisions.cerveau.main;

import hive.model.GameProgress;
import hive.model.game.Game;
import hive.model.game.PrecalculatedGame;
import hive.model.game.rules.GameStatus;
import hive.model.game.rules.HiveUtil;
import static hive.model.players.TeamColor.BLACK;
import static hive.model.players.TeamColor.WHITE;
import static hive.model.players.decisions.IA.Level.EHARD;
import hive.model.players.decisions.IADecisionLearning;
import hive.model.players.decisions.cerveau.AdamEtEve;
import hive.model.players.decisions.cerveau.EvaluationLearning;
import hive.model.players.decisions.cerveau.Mate;
import hive.model.players.decisions.cerveau.RepertoryFamily;
import static hive.model.players.decisions.cerveau.RepertoryFamily.readFile;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Quentin
 */
public class BattleRoyal {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        AdamEtEve AE = new AdamEtEve(3);
        int victory [] = new int[9];
        for(int j = 0; j<9;j++){
            victory[j]=0;
        }
        EvaluationLearning [] dreamTeam = new EvaluationLearning[9];
        for(int i=0 ; i <5;i++){
            try {
                dreamTeam[i] = new EvaluationLearning(AE.dlBoss("CoralieTheBoss"+i+".txt"));
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(BattleRoyal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(BattleRoyal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        for(int i=0 ; i <2;i++){
            try {
                dreamTeam[i+5] = new EvaluationLearning(AE.dlBoss("LucasTheBoss"+i+".txt"));
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(BattleRoyal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(BattleRoyal.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
        for(int i=0 ; i <2;i++){
            try {
                dreamTeam[i+7] = new EvaluationLearning(AE.dlBoss("AdelinaTheBoss"+i+".txt"));
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(BattleRoyal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(BattleRoyal.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
        
        for(int i = 0;i<9;i++){
            for(int j = 0 ; j<9;j++){
                if(i!=j){
                    System.out.println("Nous sommes à la partie de fils" + i);
                    Game game = PrecalculatedGame.get(PrecalculatedGame.Id.DEFAULT, new IADecisionLearning(dreamTeam[i]), new IADecisionLearning(dreamTeam[j]));
                    GameProgress progress = new GameProgress(game);
                    GameStatus status;
                    while ((status = game.rules.getStatus(game.state)) == GameStatus.CONTINUES && (HiveUtil.nbTurns(game.state) < 55)) {

                        if (HiveUtil.nbTurns(game.state) % 20 == 0 && game.state.turn.getCurrent().color ==WHITE) {
                            System.out.println("Turn : " + HiveUtil.nbTurns(game.state));
                        }
                        progress.doAction();

                    }
                    switch (status) {
                        case CURRENT_WINS:
                            System.out.println(game.state.turn.getCurrent().color + " gagne !");
                            System.out.println("Turn : " + HiveUtil.nbTurns(game.state));
                            if (game.state.turn.getCurrent().color == WHITE) {
                                victory[i]++;
                            } else {
                                victory[j]++;
                            }
                            break;
                        case OPPONENT_WINS:
                            System.out.println(game.state.turn.getOpponent().color + " gagne !");
                            System.out.println("Turn : " + HiveUtil.nbTurns(game.state));
                            if (game.state.turn.getOpponent().color == BLACK) {
                                victory[j]++;
                            } else {
                                victory[i]++;
                            }
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        int max = 0;
        for(int i=1;i<9;i++){
            if(victory[i]>victory[max]){
                max =i;
            }
        }
        AE.saveBoss(dreamTeam[max].getEvalValues(), "TheBigBoss");  
        
       
    }
    
}
