package chessGame.pieces;

import chessGame.Square;

import java.awt.*;
import java.util.ArrayList;

public class Piece {
    protected final boolean isBlack;
    protected boolean hasMoved;
    public Piece(boolean isBlack) {
        this.isBlack = isBlack;
        this.hasMoved = false;
    }

    public boolean isBlack() {
        return isBlack;
    }

    public ArrayList<Point> getPossibleMoves(Square[][] board, Point coordinates) {
        return new ArrayList<Point>();
    }

    public boolean isPositionOnTheBoard(Point coordinates) {
        return ((coordinates.x >= 0 && coordinates.x <= 7) && (coordinates.y >= 0 && coordinates.y <= 7));
    }

    public void setHasMoved(){
        this.hasMoved = true;
    }

}
