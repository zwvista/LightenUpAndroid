package com.zwstudio.logicpuzzlesandroid.puzzles.domino.android;

import android.view.View;

import com.zwstudio.logicpuzzlesandroid.R;
import com.zwstudio.logicpuzzlesandroid.common.android.GameActivity;
import com.zwstudio.logicpuzzlesandroid.common.data.MoveProgress;
import com.zwstudio.logicpuzzlesandroid.puzzles.domino.data.DominoDocument;
import com.zwstudio.logicpuzzlesandroid.puzzles.domino.domain.DominoGame;
import com.zwstudio.logicpuzzlesandroid.puzzles.domino.domain.DominoGameMove;
import com.zwstudio.logicpuzzlesandroid.puzzles.domino.domain.DominoGameState;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

import java.util.List;

@EActivity(R.layout.activity_game_game)
public class DominoGameActivity extends GameActivity<DominoGame, DominoDocument, DominoGameMove, DominoGameState> {
    public DominoDocument doc() {return app.dominoDocument;}

    protected DominoGameView gameView;
    protected View getGameView() {return gameView;}

    @AfterViews
    protected void init() {
        gameView = new DominoGameView(this);
        super.init();
    }

    protected void startGame() {
        String selectedLevelID = doc().selectedLevelID;
        List<String> layout = doc().levels.get(selectedLevelID);
        tvLevel.setText(selectedLevelID);
        updateSolutionUI();

        levelInitilizing = true;
        game = new DominoGame(layout, this);
        try {
            // restore game state
            for (MoveProgress rec : doc().moveProgress()) {
                DominoGameMove move = doc().loadMove(rec);
                game.setObject(move);
            }
            int moveIndex = doc().levelProgress().moveIndex;
            if (!(moveIndex >= 0 && moveIndex < game.moveCount())) return;
            while (moveIndex != game.moveIndex())
                game.undo();
        } finally {
            levelInitilizing = false;
        }
    }
}