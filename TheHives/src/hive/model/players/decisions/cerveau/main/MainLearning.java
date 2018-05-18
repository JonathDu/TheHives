/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.players.decisions.cerveau.main;

import hive.model.players.decisions.cerveau.IADecisionLearning;
import hive.model.GameProgress;
import hive.model.game.Game;
import hive.model.game.PrecalculatedGame;
import hive.model.game.rules.GameStatus;
import hive.model.game.rules.HiveFunctions;
import hive.model.players.Player;
import static hive.model.players.TeamColor.BLACK;
import static hive.model.players.TeamColor.WHITE;
import hive.model.players.decisions.IADecision;
import hive.model.players.decisions.Level;
import hive.model.players.decisions.cerveau.EvaluationLearning;
import hive.model.players.decisions.cerveau.Mate;
import hive.model.players.decisions.cerveau.Selection;
import hive.model.players.decisions.cerveau.generationAlpha.AdamEtEve;

/**
 *
 * @author Coralie
 */
public class MainLearning {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        Selection select = new Selection(10);
        AdamEtEve AE = new AdamEtEve();
        EvaluationLearning[] evaluations = AE.generate();
        
        
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (i != j) {
                    Game game = PrecalculatedGame.get(PrecalculatedGame.Id.DEFAULT, new IADecisionLearning(evaluations[i]), new IADecisionLearning(evaluations[j]));
                    GameProgress progress = new GameProgress(game);
                    GameStatus status;
                    while ((status = game.rules.getStatus(game.state)) == GameStatus.CONTINUES && (HiveFunctions.nbTurns(game.state) < 400)) {
                        Player player = game.state.turn.getCurrent();
                        
                        progress.doAction();

                    }
                    switch (status) {
                        case CURRENT_WINS:
                            if(game.state.turn.getCurrent().color == WHITE){
                                select.addVictory(i);
                            }
                            else
                                select.addVictory(j);
                            break;
                        case OPPONENT_WINS:
                            if(game.state.turn.getOpponent().color == BLACK){
                                select.addVictory(j);
                            }
                            else
                                select.addVictory(i);
                            break;
                        default:
                            break;
                    }
                    

                }
            }
        }
        int[] winner =  select.lesGagnants();
        Mate newGeneration;
        newGeneration = new Mate(evaluations[winner[0]].getEvalValues(), evaluations[winner[1]].getEvalValues(),10);

    }

}
