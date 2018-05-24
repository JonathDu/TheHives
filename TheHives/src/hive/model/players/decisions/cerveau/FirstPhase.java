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
import static hive.model.players.decisions.IA.Level.EHARD;
import hive.model.players.decisions.IADecisionLearning;
import static hive.model.players.decisions.cerveau.RepertoryFamily.clearFile;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Coralie
 */
public class FirstPhase extends Phase{
    public FirstPhase(){
        int nbFirstChildren = 12;
        dossier = new String[2];
        dossier[0]="generationAlpha";
        dossier[1]="generationBeta";
        dossierSuivant =0;
        int[] victoryTurn = new int[nbFirstChildren];
        Selection select = new Selection(nbFirstChildren);
        AdamEtEve AE = new AdamEtEve(nbFirstChildren);
        try {
            clearFile("Family.txt");
        } catch (IOException ex) {
            Logger.getLogger(FirstPhase.class.getName()).log(Level.SEVERE, null, ex);
        }
        evaluations = AE.generate(dossier[dossierSuivant]);
        dossierSuivant = (dossierSuivant+1)%2;
        for (int i = 0; i < nbFirstChildren; i++) {
            Game game = PrecalculatedGame.get(PrecalculatedGame.Id.DEFAULT, new IADecisionLearning(evaluations[i]), new IADecisionLearning(EHARD));
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
        else {
            noWinners = false;
            theWinner = winner[0];
            System.out.println("Les fils de la génération suivante : ");
            try {
                newGeneration = new Mate(evaluations[winner[0]].getEvalValues(), evaluations[winner[1]].getEvalValues(),evaluations[winner[2]].getEvalValues(), 12);
            } catch (IOException ex) {
                Logger.getLogger(FirstPhase.class.getName()).log(Level.SEVERE, null, ex);
            }
            //manque un truc ici
            AE.registerGen(dossier[dossierSuivant], newGeneration.getSon());
            evaluations =  AE.initGeneration(dossier[dossierSuivant]);
            dossierSuivant = (dossierSuivant+1)%2;
        }
    }
}
