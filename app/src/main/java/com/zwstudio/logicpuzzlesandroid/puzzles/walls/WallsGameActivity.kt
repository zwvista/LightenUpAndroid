package com.zwstudio.logicpuzzlesandroid.puzzles.walls

import com.zwstudio.logicpuzzlesandroid.R
import com.zwstudio.logicpuzzlesandroid.common.android.GameGameActivity
import com.zwstudio.logicpuzzlesandroid.common.data.GameLevel
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.Bean
import org.androidannotations.annotations.Click
import org.androidannotations.annotations.EActivity

@EActivity(R.layout.activity_game_game)
class WallsGameActivity : GameGameActivity<WallsGame, WallsDocument, WallsGameMove, WallsGameState>() {
    @Bean
    protected lateinit var document: WallsDocument
    override val doc get() = document

    @AfterViews
    protected override fun init() {
        gameView = WallsGameView(this)
        super.init()
    }

    override fun newGame(level: GameLevel) =
        WallsGame(level.layout, this, doc)

    @Click
    protected fun btnHelp() {
        WallsHelpActivity_.intent(this).start()
    }
}