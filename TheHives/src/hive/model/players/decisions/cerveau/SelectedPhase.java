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
import hive.model.players.decisions.IA.Evaluation;
import hive.model.players.decisions.IA.Level;
import static hive.model.players.decisions.IA.Level.EHARD;
import hive.model.players.decisions.IADecisionLearning;
import hive.model.players.decisions.cerveau.EvaluationLearning;
import hive.model.players.decisions.cerveau.Phase;

/**
 *
 * @author Coralie
 */
public class SelectedPhase extends Phase{
    int victoire;
    EvaluationLearning selected;
    boolean masterWin;

    public EvaluationLearning getSelected() {
        return selected;
    }

    public boolean isMasterWin() {
        return masterWin;
    }
    
    public SelectedPhase(EvaluationLearning eval, Level Ehard){
        masterWin = true;
        victoire = 0;
        int i=0;
        while(i<10){
            Game game;
            if(i%2==0)
                game = PrecalculatedGame.get(PrecalculatedGame.Id.DEFAULT, new IADecisionLearning(eval), new IADecisionLearning(EHARD));
            else
                game = PrecalculatedGame.get(PrecalculatedGame.Id.DEFAULT, new IADecisionLearning(EHARD), new IADecisionLearning(eval));
            GameProgress progress = new GameProgress(game);
            GameStatus status;
            while ((status = game.rules.getStatus(game.state)) == GameStatus.CONTINUES && (HiveUtil.nbTurns(game.state) < 65)) {
               progress.doAction();
            }
            switch (status) {
                    case CURRENT_WINS:
                        if ((i%2==0 && game.state.turn.getCurrent().color == WHITE ) || (i%2!=0 && game.state.turn.getCurrent().color == BLACK)) {
                            victoire++;
                        } 
                        break;
                    case OPPONENT_WINS:
                        if ((i%2!=0 && game.state.turn.getOpponent().color == BLACK)||(i%2==0 && game.state.turn.getOpponent().color == WHITE )) {
                            victoire++;
                        } 
                        break;
                    default:
                        break;
            }
            i++;
        }
        if(victoire > 5){
           selected = eval;
           masterWin = false;
        }
        else
            selected = null;
    }
    
    public SelectedPhase(EvaluationLearning aBattre, EvaluationLearning challenger){
         masterWin = true;
        victoire = 0;
        int i=0;
        while(i<10){
            Game game;
            if(i%2==0)
                game = PrecalculatedGame.get(PrecalculatedGame.Id.DEFAULT, new IADecisionLearning(challenger), new IADecisionLearning(aBattre));
            else
                game = PrecalculatedGame.get(PrecalculatedGame.Id.DEFAULT, new IADecisionLearning(aBattre), new IADecisionLearning(challenger));
            GameProgress progress = new GameProgress(game);
            GameStatus status;
            while ((status = game.rules.getStatus(game.state)) == GameStatus.CONTINUES && (HiveUtil.nbTurns(game.state) < 65)) {
               progress.doAction();
            }
            switch (status) {
                    case CURRENT_WINS:
                        if ((i%2==0 && game.state.turn.getCurrent().color == WHITE ) || (i%2!=0 && game.state.turn.getCurrent().color == BLACK)) {
                            victoire++;
                        } 
                        break;
                    case OPPONENT_WINS:
                        if ((i%2!=0 && game.state.turn.getOpponent().color == BLACK)||(i%2==0 && game.state.turn.getOpponent().color == WHITE )) {
                            victoire++;
                        } 
                        break;
                    default:
                        break;
            }
            i++;
        }
        if(victoire > 5){
           selected = challenger;
           masterWin = false;
        }
        else
            selected = null;
        
    }
}
