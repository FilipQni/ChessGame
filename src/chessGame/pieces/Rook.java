package chessGame.pieces;

import chessGame.Square;

import java.awt.*;
import java.util.ArrayList;

public class Rook extends Piece{
    public Rook(boolean isBlack) {
        super(isBlack);
    }

    @Override
    public ArrayList<Point> getPossibleMoves(Square[][] board, Point coordinates) {
        ArrayList<Point> possibleMoves = new ArrayList<>();
        Point positionTocheck = coordinates;

        //up
        positionTocheck.y++;
        while (this.isPositionOnTheBoard(positionTocheck)){
            positionTocheck.y++;
            possibleMoves.add(positionTocheck);
        }

        //down
        positionTocheck = coordinates;
        positionTocheck.y--;
        while (this.isPositionOnTheBoard(positionTocheck)){
            positionTocheck.y--;
            possibleMoves.add(positionTocheck);
        }

        //left
        positionTocheck = coordinates;
        positionTocheck.x--;
        while (this.isPositionOnTheBoard(positionTocheck)){
            positionTocheck.x--;
            possibleMoves.add(positionTocheck);
        }

        //right
        positionTocheck = coordinates;
        positionTocheck.x++;
        while (this.isPositionOnTheBoard(positionTocheck)){
            positionTocheck.x++;
            possibleMoves.add(positionTocheck);
        }

        return possibleMoves;
    }
}
