package chessGame.pieces;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

import static chessGame.ChessGame.*;

public class Pawn extends Piece {
    private final int moveDirection;

    public Pawn(boolean isBlack, Point coordinates) {
        super(isBlack, coordinates);
        if (isBlack) moveDirection = -1;
        else moveDirection = 1;
        this.symbol = 'P';
    }

    @Override
    public List<Point> getPossibleMoves() {
        List<Point> possibleMoves = new LinkedList<>();
        Point possibleMove = new Point(coordinates);

        //Move
        possibleMove.y += this.moveDirection;
        if (isPositionOnTheBoard(possibleMove) && isPositionEmpty(possibleMove)) {
            possibleMoves.add(new Point(possibleMove.x, possibleMove.y));

            if (!this.hasMoved) {
                possibleMove.y += this.moveDirection;
                if (isPositionOnTheBoard(possibleMove) && isPositionEmpty(possibleMove)) {
                    possibleMoves.add(new Point(possibleMove.x, possibleMove.y));
                }
            }
        }

        //Attack
        possibleMove.setLocation(coordinates);
        possibleMove.y += this.moveDirection;
        possibleMove.x--;
        if (canPawnAttackThisSquare(possibleMove))
            possibleMoves.add(new Point(possibleMove.x, possibleMove.y));

        possibleMove.setLocation(coordinates);
        possibleMove.y += this.moveDirection;
        possibleMove.x++;
        if (canPawnAttackThisSquare(possibleMove))
            possibleMoves.add(new Point(possibleMove.x, possibleMove.y));

        return possibleMoves;
    }

    private boolean canPawnAttackThisSquare(Point squareCoordinates) {
        return (isPositionOnTheBoard(squareCoordinates)
                && !isPositionEmpty(squareCoordinates)
                && this.isBlack != isPieceBlack(squareCoordinates));
    }
}
