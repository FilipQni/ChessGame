package chessGame.pieces;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

import static chessGame.ChessGame.setPiece;

public class Piece {
    protected final boolean isBlack;
    protected boolean hasMoved;
    protected Point coordinates;
    protected char symbol;

    public Piece(boolean isBlack, Point coordinates) {
        this.isBlack = isBlack;
        this.coordinates = coordinates;
        this.hasMoved = false;
    }

    public boolean isBlack() {
        return isBlack;
    }

    public boolean getHasMoved() {
        return hasMoved;
    }

    public List<Point> getPossibleMoves() {
        return new LinkedList<>();
    }

    public void setHasMoved() {
        this.hasMoved = true;
    }

    public void move(Point coordinates){
        setPiece(coordinates, this);
        this.coordinates = coordinates;
    }

    public Point getCoordinates(){
        return this.coordinates;
    }

    public char getSymbol(){
        return this.symbol;
    }
}
