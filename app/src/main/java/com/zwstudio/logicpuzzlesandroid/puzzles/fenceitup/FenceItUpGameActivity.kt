package com.zwstudio.logicpuzzlesandroid.puzzles.fenceitup

import com.zwstudio.logicpuzzlesandroid.R
import com.zwstudio.logicpuzzlesandroid.common.android.GameGameActivity
import com.zwstudio.logicpuzzlesandroid.common.data.GameLevel
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.Bean
import org.androidannotations.annotations.Click
import org.androidannotations.annotations.EActivity

@EActivity(R.layout.activity_game_game)
class FenceItUpGameActivity : GameGameActivity<FenceItUpGame, FenceItUpDocument, FenceItUpGameMove, FenceItUpGameState>() {
    @Bean
    protected lateinit var document: FenceItUpDocument
    override val doc get() = document

    @AfterViews
    override fun init() {
        gameView = FenceItUpGameView(this)
        super.init()
    }

    override fun newGame(level: GameLevel) =
        FenceItUpGame(level.layout, this, doc)

    @Click
    protected fun btnHelp() {
        FenceItUpHelpActivity_.intent(this).start()
    }
}