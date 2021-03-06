package com.zwstudio.logicpuzzlesandroid.puzzles.busyseas

import com.zwstudio.logicpuzzlesandroid.R
import com.zwstudio.logicpuzzlesandroid.common.android.GameGameActivity
import com.zwstudio.logicpuzzlesandroid.common.data.GameLevel
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.Bean
import org.androidannotations.annotations.Click
import org.androidannotations.annotations.EActivity

@EActivity(R.layout.activity_game_game)
class BusySeasGameActivity : GameGameActivity<BusySeasGame, BusySeasDocument, BusySeasGameMove, BusySeasGameState>() {
    @Bean
    protected lateinit var document: BusySeasDocument
    override val doc get() = document

    @AfterViews
    override fun init() {
        gameView = BusySeasGameView(this)
        super.init()
    }

    override fun newGame(level: GameLevel) =
        BusySeasGame(level.layout, this, doc)

    @Click
    protected fun btnHelp() {
        BusySeasHelpActivity_.intent(this).start()
    }
}