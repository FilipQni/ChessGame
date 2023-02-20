package chessGame.pieces;

import chessGame.Square;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class Knight extends Piece {
    public Knight(boolean isBlack, Point coordinates) {
        super(isBlack, coordinates);
    }

    @Override
    public List<Point> getPossibleMoves(Square[][] board) {
        List<Point> possibleMoves = new LinkedList<>();
        Point possibleMove = new Point();
        int[] X = {-2, -2, -1, -1, 1, 1, 2, 2};
        int[] Y = {-1, 1, -2, 2, 2, -2, 1, -1};

        for (int i = 0; i < 8; i++) {
            possibleMove.x = this.coordinates.x + X[i];
            possibleMove.y = this.coordinates.y + Y[i];
            if (isPositionOnTheBoard(possibleMove)) {
                if (board[possibleMove.x][possibleMove.y].isEmpty() || isBlack != board[possibleMove.x][possibleMove.y].isPieceBlack()) {
                    possibleMoves.add(new Point(possibleMove.x , possibleMove.y));
                }
            }
        }
        return possibleMoves;
    }
}
