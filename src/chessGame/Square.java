package chessGame;

import chessGame.pieces.Piece;

import javax.swing.*;

public class Square extends JButton {

    private Piece piece;

    public Square(Piece piece) {
        this.piece = piece;
    }
}
