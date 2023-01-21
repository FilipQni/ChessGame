package chessGame;

import chessGame.pieces.*;
import javax.swing.*;
import java.awt.*;

public class ChessGame {
    private Square[][] board;

    private JFrame frame;
    private static final int SQUARE_SIDE_LENGTH = 80;

    private static final Color DARK_SQUARE_COLOR = new Color(181, 135, 99, 255);
    private static final Color BRIGHT_SQUARE_COLOR = new Color(240, 218, 181, 255);


    public ChessGame() {
        frame = new JFrame();//creating instance of JFrame
        board = new Square[8][8];

        buildTheChessboard();
        setTheInitialPositionOfThePieces();

        frame.setSize(1200, 800);//400 width and 500 height
        frame.setLayout(null);//using no layout managers
        frame.setVisible(true);//making the frame visible
    }

    private void buildTheChessboard() {
        int x = 20;
        int y = 20;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Square square = new Square(null);
                square.setBounds(x, y, SQUARE_SIDE_LENGTH, SQUARE_SIDE_LENGTH);
                square.setBorder(BorderFactory.createEmptyBorder());
                if ((i + j) % 2 == 0) square.setBackground(BRIGHT_SQUARE_COLOR);
                else square.setBackground(DARK_SQUARE_COLOR);
                frame.add(square);
                board[j][i] = square;
                x += SQUARE_SIDE_LENGTH;
            }
            x = 20;
            y += SQUARE_SIDE_LENGTH;
        }

    }

    private void setTheInitialPositionOfThePieces() {

        //Rooks
        board[7][0].setPiece(new Rook(true));
        board[0][0].setPiece(new Rook(true));
        board[0][7].setPiece(new Rook(false));
        board[7][7].setPiece(new Rook(false));

        //Knights
        board[1][0].setPiece(new Knight(true));
        board[6][0].setPiece(new Knight(true));
        board[1][7].setPiece(new Knight(false));
        board[6][7].setPiece(new Knight(false));

        //Bishops
        board[2][0].setPiece(new Bishop(true));
        board[5][0].setPiece(new Bishop(true));
        board[2][7].setPiece(new Bishop(false));
        board[5][7].setPiece(new Bishop(false));

        //Queens
        board[3][0].setPiece(new Queen(true));
        board[3][7].setPiece(new Queen(false));

        //Kings
        board[4][0].setPiece(new King(true));
        board[4][7].setPiece(new King(false));

        //Pawns
        for (int i = 0; i < 8; i++) {
            board[i][1].setPiece(new Pawn(true));
            board[i][6].setPiece(new Pawn(false));
        }

    }

}
