package chessGame.pieces;

import chessGame.Square;

import java.awt.*;
import java.util.ArrayList;

public class Knight extends Piece {
    public Knight(boolean isBlack) {
        super(isBlack);
    }

    @Override
    public ArrayList<Point> getPossibleMoves(Square[][] board, Point coordinates) {
        ArrayList<Point> possibleMoves = new ArrayList<>();
        Point possibleMove = new Point();
        int[] X = {-2, -2, -1, -1, 1, 1, 2, 2};
        int[] Y = {-1, 1, -2, 2, 2, -2, 1, -1};

        for (int i = 0; i < 8; i++) {
            possibleMove.x = coordinates.x + X[i];
            possibleMove.y = coordinates.y + Y[i];
            if (isPositionOnTheBoard(possibleMove)) {
                if (board[possibleMove.x][possibleMove.y].isEmpty() || isBlack != board[possibleMove.x][possibleMove.y].isPieceBlack()) {
                    possibleMoves.add(new Point(possibleMove.x , possibleMove.y));
                }
            }
        }
        return possibleMoves;
    }
}
