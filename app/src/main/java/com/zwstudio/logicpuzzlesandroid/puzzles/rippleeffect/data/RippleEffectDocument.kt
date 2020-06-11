package com.zwstudio.logicpuzzlesandroid.puzzles.rippleeffect.data

import com.zwstudio.logicpuzzlesandroid.common.data.GameDocument
import com.zwstudio.logicpuzzlesandroid.common.data.MoveProgress
import com.zwstudio.logicpuzzlesandroid.common.domain.Position
import com.zwstudio.logicpuzzlesandroid.puzzles.rippleeffect.domain.RippleEffectGame
import com.zwstudio.logicpuzzlesandroid.puzzles.rippleeffect.domain.RippleEffectGameMove
import org.androidannotations.annotations.EBean

@EBean
class RippleEffectDocument : GameDocument<RippleEffectGame, RippleEffectGameMove>() {
    override fun saveMove(move: RippleEffectGameMove, rec: MoveProgress) {
        rec.row = move.p.row
        rec.col = move.p.col
        rec.intValue1 = move.obj
    }

    override fun loadMove(rec: MoveProgress) =
        RippleEffectGameMove(Position(rec.row, rec.col), rec.intValue1)
}