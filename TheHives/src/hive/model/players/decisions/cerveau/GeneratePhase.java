/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.players.decisions.cerveau;

import hive.model.GameProgress;
import hive.model.game.Game;
import hive.model.game.PrecalculatedGame;
import hive.model.game.rules.GameStatus;
import hive.model.game.rules.HiveUtil;
import static hive.model.players.TeamColor.BLACK;
import static hive.model.players.TeamColor.WHITE;
import hive.model.players.decisions.IADecisionLearning;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Coralie
 */
public class GeneratePhase extends Phase{
    EvaluationLearning boss;
    public GeneratePhase(EvaluationLearning boss, ArrayList<Integer>[] challegers, int dossiers){
        this.boss = boss;
        int nbChildren = 12;
        AdamEtEve AE = new AdamEtEve(nbChildren);
        initEval( challegers);
        dossier = new String[2];
        dossier[0]="generationAlpha";
        dossier[1]="generationBeta";
        dossierSuivant =dossiers;
        int[] victoryTurn = new int[nbChildren];
        Selection select = new Selection(nbChildren);
        for (int i = 0; i < nbChildren; i++) {
            Game game = PrecalculatedGame.get(PrecalculatedGame.Id.DEFAULT, new IADecisionLearning(evaluations[i]), new IADecisionLearning(boss));
            GameProgress progress = new GameProgress(game);
            GameStatus status;
            while ((status = game.rules.getStatus(game.state)) == GameStatus.CONTINUES && (HiveUtil.nbTurns(game.state) < 65)) {
               progress.doAction();
            }
            switch (status) {
                case CURRENT_WINS:
                    if (game.state.turn.getCurrent().color == WHITE) {
                        select.addVictory(i);
                        victoryTurn[i]=HiveUtil.nbTurns(game.state);
                    } 
                    break;
                case OPPONENT_WINS:
                    if (!(game.state.turn.getOpponent().color == BLACK)) {
                        select.addVictory(i);
                        victoryTurn[i]=HiveUtil.nbTurns(game.state);
                    } 
                    break;
                default:
                    break;
            }
        }
        winner = select.theBestWinners(victoryTurn);
        if(select.isNoWarrior())
            noWinners = true;
        else
            noWinners = false;
        theWinner = winner[0];
        try {
            newGeneration = new Mate(evaluations[winner[0]].getEvalValues(), evaluations[winner[1]].getEvalValues(),evaluations[winner[2]].getEvalValues(), 12);
        } catch (IOException ex) {
            Logger.getLogger(FirstPhase.class.getName()).log(Level.SEVERE, null, ex);
        }
        AE.registerGen(dossier[dossierSuivant], newGeneration.getSon());
        evaluations =  AE.initGeneration(dossier[dossierSuivant]);
        dossierSuivant = (dossierSuivant+1)%2;
        
    }
    
   
    public void initEval(ArrayList<Integer>[] challegers){
        for(int i =0; i<12;i++){
            evaluations[i] = new EvaluationLearning( challegers[i]);
        }
    }
}
