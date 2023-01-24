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
        Point nextPosition = new Point();
        int[] X = {-2, -2, -1, -1, 1, 1, 2, 2};
        int[] Y = {-1, 1, -2, 2, 2, -2, 1, -1};

        for (int i = 0; i < 8; i++) {
            nextPosition.x = coordinates.x + X[i];
            nextPosition.y = coordinates.y + Y[i];
            if (isPositionOnTheBoard(nextPosition)) {
                if (board[nextPosition.x][nextPosition.y].isEmpty() || isBlack != board[nextPosition.x][nextPosition.y].isPieceBlack()) {
                    possibleMoves.add(new Point(nextPosition.x , nextPosition.y));
                }
            }
        }
        return possibleMoves;
    }
}
