package chessGame.pieces;

import chessGame.Square;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class Queen extends Piece{
    public Queen(boolean isBlack, Point coordinates) {
        super(isBlack, coordinates);
    }

    @Override
    public List<Point> getPossibleMoves(Square[][] board) {
        LinkedList<Point> possibleMoves = new LinkedList<>();
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
        possibleMove.setLocation(this.coordinates);
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
        possibleMove.setLocation(this.coordinates);
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
        possibleMove.setLocation(this.coordinates);
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


        //right up
        possibleMove.setLocation(this.coordinates);
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
        possibleMove.setLocation(this.coordinates);
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
        possibleMove.setLocation(this.coordinates);
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
        possibleMove.setLocation(this.coordinates);
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
