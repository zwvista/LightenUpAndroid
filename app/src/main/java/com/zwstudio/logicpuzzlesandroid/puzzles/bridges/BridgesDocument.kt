package com.zwstudio.logicpuzzlesandroid.puzzles.bridges

import com.zwstudio.logicpuzzlesandroid.common.data.GameDocument
import com.zwstudio.logicpuzzlesandroid.common.data.MoveProgress
import com.zwstudio.logicpuzzlesandroid.common.domain.Position
import org.androidannotations.annotations.EBean

@EBean
class BridgesDocument : GameDocument<BridgesGameMove>() {
    override fun saveMove(move: BridgesGameMove, rec: MoveProgress) {
        rec.row = move.pFrom.row
        rec.col = move.pFrom.col
        rec.row2 = move.pTo.row
        rec.col2 = move.pTo.col
    }

    override fun loadMove(rec: MoveProgress) =
        BridgesGameMove(Position(rec.row, rec.col), Position(rec.row2, rec.col2))
}
