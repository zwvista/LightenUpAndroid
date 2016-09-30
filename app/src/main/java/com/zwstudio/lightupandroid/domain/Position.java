package com.zwstudio.lightupandroid.domain;

/**
 * Created by zwvista on 2016/09/29.
 */

public class Position implements Comparable {
    public int row, col;
    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }
    public Position() {
        this(0, 0);
    }

    public boolean equals(Object other) {
        if (other == null) return false;
        if (other instanceof Position) {
            Position x = (Position)other;
            return row == x.row && col == x.col;
        }
        return super.equals(other);
    }

    public int hashCode() {
        return row * 100 + col;
    }

    public String toString() {
        return String.format("(%d,%d)", row, col);
    }

    public Position add(Position x) {
        return new Position(row + x.row, col + x.col);
    }

    public Position subtract(Position x) {
        return new Position(row - x.row, col - x.col);
    }

    public Position plus(Position x) {
        return new Position(+x.row, +x.col);
    }

    public Position minus(Position x) {
        return new Position(-x.row, -x.col);
    }

    public void addBy(Position x) {
        row += x.row; col += x.col;
    }

    public void subtractBy(Position x) {
        row -= x.row; col -= x.col;
    }

    public int compareTo(Object other) {
        Position x = (Position)other;
        return hashCode() - x.hashCode();
    }
}
