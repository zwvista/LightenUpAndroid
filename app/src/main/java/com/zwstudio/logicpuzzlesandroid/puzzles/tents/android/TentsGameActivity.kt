package com.zwstudio.logicpuzzlesandroid.puzzles.tents.android

import com.zwstudio.logicpuzzlesandroid.R
import com.zwstudio.logicpuzzlesandroid.common.android.GameGameActivity
import com.zwstudio.logicpuzzlesandroid.puzzles.tents.data.TentsDocument
import com.zwstudio.logicpuzzlesandroid.puzzles.tents.domain.TentsGame
import com.zwstudio.logicpuzzlesandroid.puzzles.tents.domain.TentsGameMove
import com.zwstudio.logicpuzzlesandroid.puzzles.tents.domain.TentsGameState
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.Bean
import org.androidannotations.annotations.Click
import org.androidannotations.annotations.EActivity

@EActivity(R.layout.activity_game_game)
class TentsGameActivity : GameGameActivity<TentsGame, TentsDocument, TentsGameMove, TentsGameState>() {
    @Bean
    protected lateinit var document: TentsDocument
    override val doc get() = document

    @AfterViews
    protected override fun init() {
        gameView = TentsGameView(this)
        super.init()
    }

    protected override fun startGame() {
        val selectedLevelID = doc.selectedLevelID
        val level = doc.levels[doc.levels.indexOfFirst { it.id == selectedLevelID }.coerceAtLeast(0)]
        tvLevel.text = selectedLevelID
        updateSolutionUI()
        levelInitilizing = true
        game = TentsGame(level.layout, this, doc)
        try {
            // restore game state
            for (rec in doc.moveProgress()) {
                val move = doc.loadMove(rec)
                game.setObject(move)
            }
            val moveIndex = doc.levelProgress().moveIndex
            if (moveIndex in 0 until game.moveCount)
                while (moveIndex != game.moveIndex)
                    game.undo()
        } finally {
            levelInitilizing = false
        }
    }

    @Click
    protected fun btnHelp() {
        TentsHelpActivity_.intent(this).start()
    }
}