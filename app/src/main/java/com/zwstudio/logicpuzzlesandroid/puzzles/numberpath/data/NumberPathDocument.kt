package com.zwstudio.logicpuzzlesandroid.puzzles.numberpath.data

import com.zwstudio.logicpuzzlesandroid.common.data.GameDocument
import com.zwstudio.logicpuzzlesandroid.common.data.MoveProgress
import com.zwstudio.logicpuzzlesandroid.common.domain.Position
import com.zwstudio.logicpuzzlesandroid.puzzles.numberpath.domain.NumberPathGame
import com.zwstudio.logicpuzzlesandroid.puzzles.numberpath.domain.NumberPathGameMove
import org.androidannotations.annotations.EBean

@EBean
class NumberPathDocument : GameDocument<NumberPathGame, NumberPathGameMove>() {
    override fun saveMove(move: NumberPathGameMove, rec: MoveProgress) {
        rec.row = move.p.row
        rec.col = move.p.col
        rec.intValue1 = move.dir
    }

    override fun loadMove(rec: MoveProgress) =
        NumberPathGameMove(Position(rec.row, rec.col), rec.intValue1)
}