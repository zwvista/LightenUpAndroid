package com.zwstudio.logicgamesandroid.common.domain;

/**
 * Created by TCC-2-9002 on 2016/10/27.
 */

public interface GameInterface<G extends Game<G, GM, GS>, GM, GS extends GameState> {
    void moveAdded(G game, GM move);
    void levelInitilized(G game, GS state);
    void levelUpdated(G game, GS stateFrom, GS stateTo);
    void gameSolved(G game);
}