package chessGame.pieces;

import chessGame.Square;

import java.awt.*;
import java.util.ArrayList;

public class King extends Piece {
    public King(boolean isBlack) {
        super(isBlack);
    }

    @Override
    public ArrayList<Point> getPossibleMoves(Square[][] board, Point coordinates) {
        ArrayList<Point> possibleMoves = new ArrayList<>();
        Point possibleMove = new Point();

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;
                possibleMove.setLocation(coordinates.x + i, coordinates.y + j);
                if(this.isPositionOnTheBoard(possibleMove)){
                    if (board[possibleMove.x][possibleMove.y].isEmpty() || this.isBlack != board[possibleMove.x][possibleMove.y].isPieceBlack())
                        possibleMoves.add(new Point(possibleMove));
                }
            }
        }

        return possibleMoves;
    }
}
