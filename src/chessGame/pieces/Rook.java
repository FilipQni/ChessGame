package chessGame.pieces;

import chessGame.Square;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

import static chessGame.ChessGame.*;

public class Rook extends Piece {
    public Rook(boolean isBlack, Point coordinates) {
        super(isBlack, coordinates);
        this.symbol = 'R';
    }

    @Override
    public List<Point> getPossibleMoves() {
        List<Point> possibleMoves = new LinkedList<>();
        Point possibleMove = new Point(coordinates);

        //up
        possibleMove.y++;
        while (isPositionOnTheBoard(possibleMove)) {
            if (!isPositionEmpty(possibleMove)) {
                if (this.isBlack != isPieceBlack(possibleMove))
                    possibleMoves.add(new Point(possibleMove.x, possibleMove.y));
                break;
            }

            possibleMoves.add(new Point(possibleMove.x, possibleMove.y));
            possibleMove.y++;
        }

        //down
        possibleMove.setLocation(this.coordinates);
        possibleMove.y--;
        while (isPositionOnTheBoard(possibleMove)) {
            if (!isPositionEmpty(possibleMove)) {
                if (this.isBlack != isPieceBlack(possibleMove))
                    possibleMoves.add(new Point(possibleMove.x, possibleMove.y));
                break;
            }

            possibleMoves.add(new Point(possibleMove.x, possibleMove.y));
            possibleMove.y--;
        }

        //left
        possibleMove.setLocation(this.coordinates);
        possibleMove.x--;
        while (isPositionOnTheBoard(possibleMove)) {
            if (!isPositionEmpty(possibleMove)) {
                if (this.isBlack != isPieceBlack(possibleMove))
                    possibleMoves.add(new Point(possibleMove.x, possibleMove.y));
                break;
            }

            possibleMoves.add(new Point(possibleMove.x, possibleMove.y));
            possibleMove.x--;
        }

        //right
        possibleMove.setLocation(coordinates);
        possibleMove.x++;
        while (isPositionOnTheBoard(possibleMove)) {
            if (!isPositionEmpty(possibleMove)) {
                if (this.isBlack != isPieceBlack(possibleMove))
                    possibleMoves.add(new Point(possibleMove.x, possibleMove.y));
                break;
            }

            possibleMoves.add(new Point(possibleMove.x, possibleMove.y));
            possibleMove.x++;
        }

        return possibleMoves;
    }
}
