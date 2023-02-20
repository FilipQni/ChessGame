package chessGame.pieces;

import chessGame.Square;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class King extends Piece {
    public King(boolean isBlack, Point coordinates) {
        super(isBlack, coordinates);
    }

    @Override
    public List<Point> getPossibleMoves(Square[][] board) {
        LinkedList<Point> possibleMoves = new LinkedList<>();
        Point possibleMove = new Point();

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;
                possibleMove.setLocation(this.coordinates.x + i, this.coordinates.y + j);
                if (this.isPositionOnTheBoard(possibleMove)) {
                    if (board[possibleMove.x][possibleMove.y].isEmpty() || this.isBlack != board[possibleMove.x][possibleMove.y].isPieceBlack())
                        possibleMoves.add(new Point(possibleMove));
                }
            }
        }

        return possibleMoves;
    }
}
