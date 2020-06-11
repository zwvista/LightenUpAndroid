package com.zwstudio.logicpuzzlesandroid.puzzles.robotcrosswords.data

import com.zwstudio.logicpuzzlesandroid.common.data.GameDocument
import com.zwstudio.logicpuzzlesandroid.common.data.MoveProgress
import com.zwstudio.logicpuzzlesandroid.common.domain.Position
import com.zwstudio.logicpuzzlesandroid.puzzles.robotcrosswords.domain.RobotCrosswordsGame
import com.zwstudio.logicpuzzlesandroid.puzzles.robotcrosswords.domain.RobotCrosswordsGameMove
import org.androidannotations.annotations.EBean

@EBean
class RobotCrosswordsDocument : GameDocument<RobotCrosswordsGame, RobotCrosswordsGameMove>() {
    override fun saveMove(move: RobotCrosswordsGameMove, rec: MoveProgress) {
        rec.row = move.p.row
        rec.col = move.p.col
        rec.intValue1 = move.obj
    }

    override fun loadMove(rec: MoveProgress) =
        RobotCrosswordsGameMove(Position(rec.row, rec.col), rec.intValue1)
}