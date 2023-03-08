package chessGame.pieces;

import chessGame.Square;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

import static chessGame.ChessGame.*;

public class Bishop extends Piece {
    public Bishop(boolean isBlack, Point coordinates) {
        super(isBlack, coordinates);
        this.symbol = 'B';
    }
    @Override
    public List<Point> getPossibleMoves() {
        List<Point> possibleMoves = new LinkedList<>();
        Point possibleMove = new Point(this.coordinates);

        //right up
        possibleMove.x++;
        possibleMove.y++;
        while (isPositionOnTheBoard(possibleMove)){
            if (!isPositionEmpty(possibleMove)) {
                if (this.isBlack != isPieceBlack(possibleMove))
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
        while (isPositionOnTheBoard(possibleMove)){
            if (!isPositionEmpty(possibleMove)) {
                if (this.isBlack != isPieceBlack(possibleMove))
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
        while (isPositionOnTheBoard(possibleMove)){
            if (!isPositionEmpty(possibleMove)) {
                if (this.isBlack != isPieceBlack(possibleMove))
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
        while (isPositionOnTheBoard(possibleMove)){
            if (!isPositionEmpty(possibleMove)) {
                if (this.isBlack != isPieceBlack(possibleMove))
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
