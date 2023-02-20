package chessGame.pieces;

import chessGame.Square;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class Piece {
    protected final boolean isBlack;
    protected boolean hasMoved;
    protected Point coordinates;

    public Piece(boolean isBlack, Point coordinates) {
        this.isBlack = isBlack;
        this.hasMoved = false;
        this.coordinates = coordinates;
    }

    public boolean isBlack() {
        return isBlack;
    }

    public boolean getHasMoved() {
        return hasMoved;
    }

    public List<Point> getPossibleMoves(Square[][] board) {
        return new LinkedList<>();
    }

    public boolean isPositionOnTheBoard(Point coordinates) {
        return ((coordinates.x >= 0 && coordinates.x <= 7) && (coordinates.y >= 0 && coordinates.y <= 7));
    }

    public void setHasMoved() {
        this.hasMoved = true;
    }

    public void move(Square[][] board, Point coordinates){
        board[coordinates.x][coordinates.y].setPiece(this);
        this.coordinates = coordinates;
    }

}
