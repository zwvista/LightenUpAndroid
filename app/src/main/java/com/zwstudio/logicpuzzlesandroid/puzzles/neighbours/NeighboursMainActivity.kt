package com.zwstudio.logicpuzzlesandroid.puzzles.neighbours

import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckedTextView
import android.widget.TextView
import com.zwstudio.logicpuzzlesandroid.R
import com.zwstudio.logicpuzzlesandroid.common.android.GameHelpActivity
import com.zwstudio.logicpuzzlesandroid.common.android.GameMainActivity
import com.zwstudio.logicpuzzlesandroid.common.android.GameOptionsActivity
import com.zwstudio.logicpuzzlesandroid.home.android.realm
import org.androidannotations.annotations.*

@EActivity(R.layout.activity_game_main)
class NeighboursMainActivity : GameMainActivity<NeighboursGame, NeighboursDocument, NeighboursGameMove, NeighboursGameState>() {
    @Bean
    protected lateinit var document: NeighboursDocument
    override val doc get() = document

    @Click
    fun btnOptions() {
        NeighboursOptionsActivity_.intent(this).start()
    }

    protected override fun resumeGame() {
        doc.resumeGame()
        NeighboursGameActivity_.intent(this).start()
    }
}

@EActivity(R.layout.activity_game_options)
class NeighboursOptionsActivity : GameOptionsActivity<NeighboursGame, NeighboursDocument, NeighboursGameMove, NeighboursGameState>() {
    @Bean
    protected lateinit var document: NeighboursDocument
    override val doc get() = document

    @AfterViews
    protected override fun init() {
        val lst: List<String> = GameOptionsActivity.lstMarkers
        val adapter = object : ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, GameOptionsActivity.lstMarkers) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val v = super.getView(position, convertView, parent)
                val s = lst[position]
                val tv = v.findViewById<TextView>(android.R.id.text1)
                tv.text = s
                return v
            }

            override fun getDropDownView(position: Int, convertView: View, parent: ViewGroup): View {
                val v = super.getDropDownView(position, convertView, parent)
                val s = lst[position]
                val ctv = v.findViewById<CheckedTextView>(android.R.id.text1)
                ctv.text = s
                return v
            }
        }
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice)
        spnMarker.adapter = adapter
        spnMarker.setSelection(doc.markerOption)
    }

    @ItemSelect
    protected override fun spnMarkerItemSelected(selected: Boolean, position: Int) {
        realm.beginTransaction()
        val rec = doc.gameProgress()
        doc.setMarkerOption(rec, position)
        realm.insertOrUpdate(rec)
        realm.commitTransaction()
    }

    protected fun onDefault() {
        realm.beginTransaction()
        val rec = doc.gameProgress()
        doc.setMarkerOption(rec, 0)
        realm.insertOrUpdate(rec)
        realm.commitTransaction()
        spnMarker.setSelection(doc.markerOption)
    }
}

@EActivity(R.layout.activity_game_help)
class NeighboursHelpActivity : GameHelpActivity<NeighboursGame, NeighboursDocument, NeighboursGameMove, NeighboursGameState>() {
    @Bean
    protected lateinit var document: NeighboursDocument
    override val doc get() = document
}