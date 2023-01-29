package chessGame;

import chessGame.pieces.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ChessGame implements ActionListener {
    private Square[][] board;
    private JFrame frame;
    private Square previousSquare = null;
    private boolean blackTurn = false;
    ArrayList<Point> possibleMoves = new ArrayList<>();
    private static final int SQUARE_SIDE_LENGTH = 80;




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

        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                Square square = new Square(null, new Point(j, i));
                square.setBounds(x, y, SQUARE_SIDE_LENGTH, SQUARE_SIDE_LENGTH);
                square.setBorder(BorderFactory.createEmptyBorder());
                square.addActionListener(this);
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
        board[7][0].setPiece(new Rook(false));
        board[0][0].setPiece(new Rook(false));
        board[0][7].setPiece(new Rook(true));
        board[7][7].setPiece(new Rook(true));

        //Knights
        board[1][0].setPiece(new Knight(false));
        board[6][0].setPiece(new Knight(false));
        board[1][7].setPiece(new Knight(true));
        board[6][7].setPiece(new Knight(true));

        //Bishops
        board[2][0].setPiece(new Bishop(false));
        board[5][0].setPiece(new Bishop(false));
        board[2][7].setPiece(new Bishop(true));
        board[5][7].setPiece(new Bishop(true));

        //Queens
        board[3][0].setPiece(new Queen(false));
        board[3][7].setPiece(new Queen(true));

        //Kings
        board[4][0].setPiece(new King(false));
        board[4][7].setPiece(new King(true));

        //Pawns
        for (int i = 0; i < 8; i++) {
            board[i][1].setPiece(new Pawn(false));
            board[i][6].setPiece(new Pawn(true));
        }

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Square clickedSquare = (Square) e.getSource();
        if (!clickedSquare.isEmpty() && blackTurn == clickedSquare.isPieceBlack()) {
            uncolorPossibleMovesSquares();
            possibleMoves.clear();
            possibleMoves = clickedSquare.getPossibleMoves(board);
            System.out.println("You can move that piece: " + clickedSquare.getCoordinates().toString());
            colorPossibleMovesSquares();
            previousSquare = clickedSquare;
        }
        else {
            if(possibleMoves.contains(clickedSquare.getCoordinates())){
                clickedSquare.setPiece(previousSquare.getPiece());
                clickedSquare.setPieceHasMoved();
                previousSquare.removePiece();
                previousSquare = null;
                blackTurn = !blackTurn;
            }
            uncolorPossibleMovesSquares();
            possibleMoves.clear();

        }


    }
    private void colorPossibleMovesSquares(){
        for (Point coordinates: possibleMoves) {
            board[coordinates.x][coordinates.y].colorTheSquare();
        }
    }
    private void uncolorPossibleMovesSquares(){
        for (Point coordinates: possibleMoves) {
            board[coordinates.x][coordinates.y].uncolorTheSquare();
        }
    }
}
