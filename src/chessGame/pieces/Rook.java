package chessGame.pieces;

import chessGame.Square;

import java.awt.*;
import java.util.ArrayList;

public class Rook extends Piece {
    public Rook(boolean isBlack) {
        super(isBlack);
    }

    @Override
    public ArrayList<Point> getPossibleMoves(Square[][] board, Point coordinates) {
        ArrayList<Point> possibleMoves = new ArrayList<>();
        Point possibleMove = new Point(coordinates);

        //up
        possibleMove.y++;
        while (this.isPositionOnTheBoard(possibleMove)) {
            if (!board[possibleMove.x][possibleMove.y].isEmpty()) {
                if (this.isBlack != board[possibleMove.x][possibleMove.y].isPieceBlack())
                    possibleMoves.add(new Point(possibleMove.x, possibleMove.y));
                break;
            }

            possibleMoves.add(new Point(possibleMove.x, possibleMove.y));
            possibleMove.y++;
        }

        //down
        possibleMove.setLocation(coordinates);
        possibleMove.y--;
        while (this.isPositionOnTheBoard(possibleMove)) {
            if (!board[possibleMove.x][possibleMove.y].isEmpty()) {
                if (this.isBlack != board[possibleMove.x][possibleMove.y].isPieceBlack())
                    possibleMoves.add(new Point(possibleMove.x, possibleMove.y));
                break;
            }

            possibleMoves.add(new Point(possibleMove.x, possibleMove.y));
            possibleMove.y--;
        }

        //left
        possibleMove.setLocation(coordinates);
        possibleMove.x--;
        while (this.isPositionOnTheBoard(possibleMove)) {
            if (!board[possibleMove.x][possibleMove.y].isEmpty()) {
                if (this.isBlack != board[possibleMove.x][possibleMove.y].isPieceBlack())
                    possibleMoves.add(new Point(possibleMove.x, possibleMove.y));
                break;
            }

            possibleMoves.add(new Point(possibleMove.x, possibleMove.y));
            possibleMove.x--;
        }

        //right
        possibleMove.setLocation(coordinates);
        possibleMove.x++;
        while (this.isPositionOnTheBoard(possibleMove)) {
            if (!board[possibleMove.x][possibleMove.y].isEmpty()) {
                if (this.isBlack != board[possibleMove.x][possibleMove.y].isPieceBlack())
                    possibleMoves.add(new Point(possibleMove.x, possibleMove.y));
                break;
            }

            possibleMoves.add(new Point(possibleMove.x, possibleMove.y));
            possibleMove.x++;
        }

        return possibleMoves;
    }
}
