package chessGame.pieces;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

import static chessGame.ChessGame.*;

public class Queen extends Piece{
    public Queen(boolean isBlack, Point coordinates) {
        super(isBlack, coordinates);
        this.symbol = 'Q';
    }

    @Override
    public List<Point> getPossibleMoves() {
        LinkedList<Point> possibleMoves = new LinkedList<>();
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
        possibleMove.setLocation(this.coordinates);
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


        //right up
        possibleMove.setLocation(this.coordinates);
        possibleMove.x++;
        possibleMove.y++;
        while (isPositionOnTheBoard(possibleMove)) {
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
        possibleMove.setLocation(this.coordinates);
        possibleMove.x--;
        possibleMove.y++;
        while (isPositionOnTheBoard(possibleMove)) {
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
        possibleMove.setLocation(this.coordinates);
        possibleMove.x--;
        possibleMove.y--;
        while (isPositionOnTheBoard(possibleMove)) {
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
        possibleMove.setLocation(this.coordinates);
        possibleMove.x++;
        possibleMove.y--;
        while (isPositionOnTheBoard(possibleMove)) {
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
