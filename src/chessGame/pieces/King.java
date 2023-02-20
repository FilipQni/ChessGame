package chessGame.pieces;

import chessGame.Square;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class King extends Piece {
    public King(boolean isBlack, Point coordinates) {
        super(isBlack, coordinates);
    }

    @Override
    public List<Point> getPossibleMoves(Square[][] board) {
        LinkedList<Point> possibleMoves = new LinkedList<>();
        Point possibleMove = new Point();

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;
                possibleMove.setLocation(this.coordinates.x + i, this.coordinates.y + j);
                if (this.isPositionOnTheBoard(possibleMove)) {
                    if (board[possibleMove.x][possibleMove.y].isEmpty() || this.isBlack != board[possibleMove.x][possibleMove.y].isPieceBlack())
                        possibleMoves.add(new Point(possibleMove));
                }
            }
        }

        if (!this.hasMoved) {
            castlingMove(board, possibleMoves);
        }

        return possibleMoves;
    }

    private void castlingMove(Square[][] board, LinkedList<Point> possibleMoves) {
        if (board[1][this.coordinates.y].isEmpty()
                && board[2][this.coordinates.y].isEmpty()
                && board[3][this.coordinates.y].isEmpty()
                && !board[0][this.coordinates.y].isEmpty()
                && !board[0][this.coordinates.y].getPiecehasMoved()) {
            possibleMoves.add(new Point(2, coordinates.y));
        }
        if (board[5][this.coordinates.y].isEmpty()
                && board[6][this.coordinates.y].isEmpty()
                && !board[7][this.coordinates.y].isEmpty()
                && !board[7][this.coordinates.y].getPiecehasMoved()) {
            possibleMoves.add(new Point(6, coordinates.y));
        }
    }

    @Override
    public void move(Square[][] board, Point coordinates) {
        super.move(board, coordinates);
        if(!hasMoved){
            if (coordinates.x == 6) {
                board[5][coordinates.y].movePieceHere(board, board[7][coordinates.y]);
            }
            if (coordinates.x == 2) {
                board[3][coordinates.y].movePieceHere(board, board[0][coordinates.y]);
            }
        }
    }
}
