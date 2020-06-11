package com.zwstudio.logicpuzzlesandroid.puzzles.tennergrid.data

import com.zwstudio.logicpuzzlesandroid.common.data.GameDocument
import com.zwstudio.logicpuzzlesandroid.common.data.MoveProgress
import com.zwstudio.logicpuzzlesandroid.common.domain.Position
import com.zwstudio.logicpuzzlesandroid.puzzles.tennergrid.domain.TennerGridGame
import com.zwstudio.logicpuzzlesandroid.puzzles.tennergrid.domain.TennerGridGameMove
import org.androidannotations.annotations.EBean

@EBean
class TennerGridDocument : GameDocument<TennerGridGame, TennerGridGameMove>() {
    override fun saveMove(move: TennerGridGameMove, rec: MoveProgress) {
        rec.row = move.p.row
        rec.col = move.p.col
        rec.intValue1 = move.obj
    }

    override fun loadMove(rec: MoveProgress) =
        TennerGridGameMove(Position(rec.row, rec.col), rec.intValue1)
}