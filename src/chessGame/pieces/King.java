package chessGame.pieces;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

import static chessGame.ChessGame.*;

public class King extends Piece {
    public King(boolean isBlack, Point coordinates) {
        super(isBlack, coordinates);
        this.symbol = 'K';
    }

    @Override
    public List<Point> getPossibleMoves() {
        LinkedList<Point> possibleMoves = new LinkedList<>();
        Point possibleMove = new Point();

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;
                possibleMove.setLocation(this.coordinates.x + i, this.coordinates.y + j);
                if (isPositionOnTheBoard(possibleMove)) {
                    if (isPositionEmpty(possibleMove) || this.isBlack != isPieceBlack(possibleMove))
                        possibleMoves.add(new Point(possibleMove));
                }
            }
        }

        if (!this.hasMoved) {
            castlingMove(possibleMoves);
        }

        return possibleMoves;
    }

    private void castlingMove(LinkedList<Point> possibleMoves) {
        if (isQueenSideCastlingPossible(coordinates.y))
            possibleMoves.add(new Point(2, coordinates.y));

        if (isKingSideCastlingPossible(coordinates.y))
            possibleMoves.add(new Point(6, coordinates.y));
    }

    @Override
    public void move(Point coordinates) {
        super.move(coordinates);
        if (!hasMoved) {
            if (coordinates.x == 6) {
                moveRookAfterKingSideCastling(coordinates.y);
            }
            if (coordinates.x == 2) {
                moveRookAfterQueenSideCastling(coordinates.y);
            }
        }
    }


}
