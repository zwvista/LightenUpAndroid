package com.zwstudio.logicpuzzlesandroid.puzzles.battleships.domain;

import com.zwstudio.logicpuzzlesandroid.common.domain.CellsGameState;
import com.zwstudio.logicpuzzlesandroid.common.domain.Graph;
import com.zwstudio.logicpuzzlesandroid.common.domain.MarkerOptions;
import com.zwstudio.logicpuzzlesandroid.common.domain.Node;
import com.zwstudio.logicpuzzlesandroid.common.domain.Position;
import com.zwstudio.logicpuzzlesandroid.home.domain.HintState;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fj.F;

import static fj.data.List.iterableList;

/**
 * Created by zwvista on 2016/09/29.
 */

public class BattleShipsGameState extends CellsGameState<BattleShipsGame, BattleShipsGameMove, BattleShipsGameState> {
    public BattleShipsObject[] objArray;
    public HintState[] row2state;
    public HintState[] col2state;

    public BattleShipsGameState(BattleShipsGame game) {
        super(game);
        objArray = new BattleShipsObject[rows() * cols()];
        Arrays.fill(objArray, BattleShipsObject.Empty);
        for (Map.Entry<Position, BattleShipsObject> entry : game.pos2obj.entrySet()) {
            Position p = entry.getKey();
            BattleShipsObject o = entry.getValue();
            set(p, o);
        }
        row2state = new HintState[rows()];
        col2state = new HintState[cols()];
        updateIsSolved();
    }

    public BattleShipsObject get(int row, int col) {
        return objArray[row * cols() + col];
    }
    public BattleShipsObject get(Position p) {
        return get(p.row, p.col);
    }
    public void set(int row, int col, BattleShipsObject obj) {
        objArray[row * cols() + col] = obj;
    }
    public void set(Position p, BattleShipsObject obj) {
        set(p.row, p.col, obj);
    }

    public boolean setObject(BattleShipsGameMove move) {
        Position p = move.p;
        if (!isValid(p) || game.pos2obj.containsKey(p) || get(p) == move.obj) return false;
        set(p, move.obj);
        updateIsSolved();
        return true;
    }

    public boolean switchObject(BattleShipsGameMove move, MarkerOptions markerOption) {
        F<BattleShipsObject, BattleShipsObject> f = obj -> {
            switch (obj) {
            case Empty:
                return markerOption == MarkerOptions.MarkerFirst ?
                        BattleShipsObject.Marker : BattleShipsObject.BattleShipUnit;
            case BattleShipUnit:
                return BattleShipsObject.BattleShipMiddle;
            case BattleShipMiddle:
                return BattleShipsObject.BattleShipLeft;
            case BattleShipLeft:
                return BattleShipsObject.BattleShipTop;
            case BattleShipTop:
                return BattleShipsObject.BattleShipRight;
            case BattleShipRight:
                return BattleShipsObject.BattleShipBottom;
            case BattleShipBottom:
                return markerOption == MarkerOptions.MarkerLast ?
                        BattleShipsObject.Marker : BattleShipsObject.Empty;
            case Marker:
                return markerOption == MarkerOptions.MarkerFirst ?
                        BattleShipsObject.BattleShipUnit : BattleShipsObject.Empty;
            }
            return obj;
        };
        Position p = move.p;
        if (!isValid(p)) return false;
        move.obj = f.f(get(p));
        return setObject(move);
    }

    private void updateIsSolved() {
        isSolved = true;
        for (int r = 0; r < rows(); r++) {
            int n1 = 0, n2 = game.row2hint[r];
            for (int c = 0; c < cols(); c++) {
                BattleShipsObject o = get(r, c);
                if (o == BattleShipsObject.BattleShipTop || o == BattleShipsObject.BattleShipBottom ||
                        o == BattleShipsObject.BattleShipLeft || o == BattleShipsObject.BattleShipRight ||
                        o == BattleShipsObject.BattleShipMiddle || o == BattleShipsObject.BattleShipUnit)
                    n1++;
            }
            row2state[r] = n1 < n2 ? HintState.Normal : n1 == n2 ? HintState.Complete : HintState.Error;
            if (n1 != n2) isSolved = false;
        }
        for (int c = 0; c < cols(); c++) {
            int n1 = 0, n2 = game.col2hint[c];
            for (int r = 0; r < rows(); r++) {
                BattleShipsObject o = get(r, c);
                if (o == BattleShipsObject.BattleShipTop || o == BattleShipsObject.BattleShipBottom ||
                        o == BattleShipsObject.BattleShipLeft || o == BattleShipsObject.BattleShipRight ||
                        o == BattleShipsObject.BattleShipMiddle || o == BattleShipsObject.BattleShipUnit)
                    n1++;
            }
            col2state[c] = n1 < n2 ? HintState.Normal : n1 == n2 ? HintState.Complete : HintState.Error;
            if (n1 != n2) isSolved = false;
        }
        if (!isSolved) return;
        Graph g = new Graph();
        Map<Position, Node> pos2node = new HashMap<>();
        for (int r = 0; r < rows(); r++)
            for (int c = 0; c < cols(); c++) {
                Position p = new Position(r, c);
                BattleShipsObject o = get(p);
                if (o == BattleShipsObject.BattleShipTop || o == BattleShipsObject.BattleShipBottom ||
                        o == BattleShipsObject.BattleShipLeft || o == BattleShipsObject.BattleShipRight ||
                        o == BattleShipsObject.BattleShipMiddle || o == BattleShipsObject.BattleShipUnit) {
                    Node node = new Node(p.toString());
                    g.addNode(node);
                    pos2node.put(p, node);
                }
            }
        for (Map.Entry<Position, Node> entry : pos2node.entrySet()) {
            Position p = entry.getKey();
            Node node = entry.getValue();
            for (Position os : BattleShipsGame.offset) {
                Position p2 = p.add(os);
                if (pos2node.containsKey(p2))
                    g.connectNode(node, pos2node.get(p2));
            }
        }
        Integer[] shipNumbers = {0, 0, 0, 0, 0};
        while (!pos2node.isEmpty()) {
            g.setRootNode(iterableList(pos2node.values()).head());
            List<Node> nodeList = g.bfs();
        }
    }
}
