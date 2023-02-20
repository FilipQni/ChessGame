package chessGame.pieces;

import chessGame.Square;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class Bishop extends Piece {
    public Bishop(boolean isBlack, Point coordinates) {
        super(isBlack, coordinates);
    }
    @Override
    public List<Point> getPossibleMoves(Square[][] board) {
        List<Point> possibleMoves = new LinkedList<>();
        Point possibleMove = new Point(this.coordinates);

        //right up
        possibleMove.x++;
        possibleMove.y++;
        while (this.isPositionOnTheBoard(possibleMove)){
            if (!board[possibleMove.x][possibleMove.y].isEmpty()) {
                if (this.isBlack != board[possibleMove.x][possibleMove.y].isPieceBlack())
                    possibleMoves.add(new Point(possibleMove.x, possibleMove.y));
                break;
            }

            possibleMoves.add(new Point(possibleMove.x, possibleMove.y));
            possibleMove.x++;
            possibleMove.y++;
        }

        //left up
        possibleMove.setLocation(coordinates);
        possibleMove.x--;
        possibleMove.y++;
        while (this.isPositionOnTheBoard(possibleMove)){
            if (!board[possibleMove.x][possibleMove.y].isEmpty()) {
                if (this.isBlack != board[possibleMove.x][possibleMove.y].isPieceBlack())
                    possibleMoves.add(new Point(possibleMove.x, possibleMove.y));
                break;
            }

            possibleMoves.add(new Point(possibleMove.x, possibleMove.y));
            possibleMove.x--;
            possibleMove.y++;
        }

        //left down
        possibleMove.setLocation(coordinates);
        possibleMove.x--;
        possibleMove.y--;
        while (this.isPositionOnTheBoard(possibleMove)){
            if (!board[possibleMove.x][possibleMove.y].isEmpty()) {
                if (this.isBlack != board[possibleMove.x][possibleMove.y].isPieceBlack())
                    possibleMoves.add(new Point(possibleMove.x, possibleMove.y));
                break;
            }

            possibleMoves.add(new Point(possibleMove.x, possibleMove.y));
            possibleMove.x--;
            possibleMove.y--;
        }

        //right down
        possibleMove.setLocation(coordinates);
        possibleMove.x++;
        possibleMove.y--;
        while (this.isPositionOnTheBoard(possibleMove)){
            if (!board[possibleMove.x][possibleMove.y].isEmpty()) {
                if (this.isBlack != board[possibleMove.x][possibleMove.y].isPieceBlack())
                    possibleMoves.add(new Point(possibleMove.x, possibleMove.y));
                break;
            }

            possibleMoves.add(new Point(possibleMove.x, possibleMove.y));
            possibleMove.x++;
            possibleMove.y--;
        }

        return possibleMoves;
    }
}
