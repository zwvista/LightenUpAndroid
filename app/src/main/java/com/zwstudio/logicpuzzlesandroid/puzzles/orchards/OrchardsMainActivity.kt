package com.zwstudio.logicpuzzlesandroid.puzzles.orchards

import com.zwstudio.logicpuzzlesandroid.R
import com.zwstudio.logicpuzzlesandroid.common.android.GameHelpActivity
import com.zwstudio.logicpuzzlesandroid.common.android.GameMainActivity
import com.zwstudio.logicpuzzlesandroid.common.android.GameOptionsActivity
import org.androidannotations.annotations.Bean
import org.androidannotations.annotations.Click
import org.androidannotations.annotations.EActivity

@EActivity(R.layout.activity_game_main)
class OrchardsMainActivity : GameMainActivity<OrchardsGame, OrchardsDocument, OrchardsGameMove, OrchardsGameState>() {
    @Bean
    protected lateinit var document: OrchardsDocument
    override val doc get() = document

    @Click
    fun btnOptions() {
        OrchardsOptionsActivity_.intent(this).start()
    }

    protected override fun resumeGame() {
        doc.resumeGame()
        OrchardsGameActivity_.intent(this).start()
    }
}

@EActivity(R.layout.activity_game_options)
class OrchardsOptionsActivity : GameOptionsActivity<OrchardsGame, OrchardsDocument, OrchardsGameMove, OrchardsGameState>() {
    @Bean
    protected lateinit var document: OrchardsDocument
    override val doc get() = document
}

@EActivity(R.layout.activity_game_help)
class OrchardsHelpActivity : GameHelpActivity<OrchardsGame, OrchardsDocument, OrchardsGameMove, OrchardsGameState>() {
    @Bean
    protected lateinit var document: OrchardsDocument
    override val doc get() = document
}