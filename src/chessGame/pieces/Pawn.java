package chessGame.pieces;

import chessGame.Square;

import java.awt.*;
import java.util.ArrayList;

public class Pawn extends Piece {
    private final int moveDirection;

    public Pawn(boolean isBlack) {
        super(isBlack);
        if (isBlack) moveDirection = -1;
        else moveDirection = 1;
    }

    @Override
    public ArrayList<Point> getPossibleMoves(Square[][] board, Point coordinates) {
        ArrayList<Point> possibleMoves = new ArrayList<>();
        Point possibleMove = new Point(coordinates);

        //Move
        possibleMove.y += this.moveDirection;
        if (this.isPositionOnTheBoard(possibleMove) && board[possibleMove.x][possibleMove.y].isEmpty()) {
            possibleMoves.add(new Point(possibleMove.x, possibleMove.y));
        }
        if (!this.hasMoved) {
            possibleMove.y += this.moveDirection;
            if (this.isPositionOnTheBoard(possibleMove) && board[possibleMove.x][possibleMove.y].isEmpty()) {
                possibleMoves.add(new Point(possibleMove.x, possibleMove.y));
            }
        }

        //Attack
        possibleMove.setLocation(coordinates);
        possibleMove.y += this.moveDirection;
        possibleMove.x--;
        if (canPawnAttackThisSquare(board, possibleMove))
            possibleMoves.add(new Point(possibleMove.x, possibleMove.y));

        possibleMove.setLocation(coordinates);
        possibleMove.y += this.moveDirection;
        possibleMove.x++;
        if (canPawnAttackThisSquare(board, possibleMove))
            possibleMoves.add(new Point(possibleMove.x, possibleMove.y));

        return possibleMoves;
    }

    private boolean canPawnAttackThisSquare(Square[][] board, Point squareCoordinates) {
        return (this.isPositionOnTheBoard(squareCoordinates)
                && !board[squareCoordinates.x][squareCoordinates.y].isEmpty()
                && this.isBlack != board[squareCoordinates.x][squareCoordinates.y].isPieceBlack());
    }
}
