package com.zwstudio.logicpuzzlesandroid.puzzles.sentinels.data

import com.zwstudio.logicpuzzlesandroid.common.data.GameDocument
import com.zwstudio.logicpuzzlesandroid.common.data.MoveProgress
import com.zwstudio.logicpuzzlesandroid.common.domain.Position
import com.zwstudio.logicpuzzlesandroid.puzzles.sentinels.domain.SentinelsGame
import com.zwstudio.logicpuzzlesandroid.puzzles.sentinels.domain.SentinelsGameMove
import com.zwstudio.logicpuzzlesandroid.puzzles.sentinels.domain.SentinelsObject
import org.androidannotations.annotations.EBean

@EBean
class SentinelsDocument : GameDocument<SentinelsGame, SentinelsGameMove>() {
    override fun saveMove(move: SentinelsGameMove, rec: MoveProgress) {
        rec.row = move.p.row
        rec.col = move.p.col
        rec.strValue1 = move.obj.objAsString()
    }

    override fun loadMove(rec: MoveProgress) =
        SentinelsGameMove(Position(rec.row, rec.col), SentinelsObject.objFromString(rec.strValue1))
}