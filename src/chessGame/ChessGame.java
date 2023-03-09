package chessGame;

import chessGame.pieces.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

public class ChessGame implements ActionListener {
    private static final int SQUARE_SIDE_LENGTH = 80;

    private static Square[][] board;
    private final JFrame frame;
    private Square previousSquare = null;
    private boolean check = false;
    private boolean checkmate = false;
    private boolean blackTurn = false;
    private Square clickedSquare;
    List<Point> possibleMoves = new LinkedList<>();
    List<Piece> whitePieces = new LinkedList<>();
    List<Piece> blackPieces = new LinkedList<>();
    private King whiteKing;
    private King blackKing;
    private Piece capturedPiece;
    private Point attackerPosition;
    private Point attackerDirection;

    public ChessGame() {
        frame = new JFrame();//creating instance of JFrame
        board = new Square[8][8];

        buildTheChessboard();
        setTheInitialPositionOfThePieces();

        frame.setSize(1200, 800);//400 width and 500 height
        frame.setLayout(null);//using no layout managers
        frame.setVisible(true);//making the frame visible
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        board[7][0].setPiece(new Rook(false, new Point(7, 0)));
        board[0][0].setPiece(new Rook(false, new Point(0, 0)));
        board[0][7].setPiece(new Rook(true, new Point(0, 7)));
        board[7][7].setPiece(new Rook(true, new Point(7, 7)));

        //Knights
        board[1][0].setPiece(new Knight(false, new Point(1, 0)));
        board[6][0].setPiece(new Knight(false, new Point(6, 0)));
        board[1][7].setPiece(new Knight(true, new Point(1, 7)));
        board[6][7].setPiece(new Knight(true, new Point(6, 7)));

        //Bishops
        board[2][0].setPiece(new Bishop(false, new Point(2, 0)));
        board[5][0].setPiece(new Bishop(false, new Point(5, 0)));
        board[2][7].setPiece(new Bishop(true, new Point(2, 7)));
        board[5][7].setPiece(new Bishop(true, new Point(5, 7)));

        //Queens
        board[3][0].setPiece(new Queen(false, new Point(3, 0)));
        board[3][7].setPiece(new Queen(true, new Point(3, 7)));

        //Kings
        board[4][0].setPiece(new King(false, new Point(4, 0)));
        board[4][7].setPiece(new King(true, new Point(4, 7)));

        //Pawns
        for (int i = 0; i < 8; i++) {
            board[i][1].setPiece(new Pawn(false, new Point(i, 1)));
            board[i][6].setPiece(new Pawn(true, new Point(i, 6)));
        }

        whiteKing = (King) board[4][0].getPiece();
        blackKing = (King) board[4][7].getPiece();

        for (int i = 0; i < 8; i++) {
            whitePieces.add(board[i][0].getPiece());
            whitePieces.add(board[i][1].getPiece());
            blackPieces.add(board[i][6].getPiece());
            blackPieces.add(board[i][7].getPiece());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        clickedSquare = (Square) e.getSource();
        Point kingPosition;
        if (!clickedSquare.isEmpty() && blackTurn == clickedSquare.isPieceBlack()) {
            uncolorPossibleMovesSquares();
            possibleMoves.clear();
            possibleMoves = clickedSquare.getPossibleMoves();
            System.out.println("You can move that piece: " + clickedSquare.getCoordinates().toString());
            colorPossibleMovesSquares();
            previousSquare = clickedSquare;
        } else {
            if (possibleMoves.contains(clickedSquare.getCoordinates())) {
                if (!clickedSquare.isEmpty()) capturedPiece = clickedSquare.getPiece();
                clickedSquare.movePieceHere(previousSquare);
                if (blackTurn) kingPosition = blackKing.getCoordinates();
                else kingPosition = whiteKing.getCoordinates();
                check = isPositionUnderAttack(kingPosition);
                if (check) {
                    System.out.println("Jest szach, cofam ruch");
                    previousSquare.movePieceHere(clickedSquare);
                    if (capturedPiece != null) clickedSquare.setPiece(capturedPiece);
                } else {
                    clickedSquare.setPieceHasMoved();
                    previousSquare = null;
                    blackTurn = !blackTurn;
                    if (blackTurn) kingPosition = blackKing.getCoordinates();
                    else kingPosition = whiteKing.getCoordinates();
                    if (isItCheckmate(kingPosition)) {
                        System.out.print("Jest mat, wygrał kolor: ");
                        if (blackTurn) System.out.print("biały");
                        else System.out.println("czarny");
                    }
                }
            }
            capturedPiece = null;
            uncolorPossibleMovesSquares();
            possibleMoves.clear();
        }
    }

    private void colorPossibleMovesSquares() {
        for (Point coordinates : possibleMoves) {
            board[coordinates.x][coordinates.y].colorTheSquare();
        }
    }

    private void uncolorPossibleMovesSquares() {
        for (Point coordinates : possibleMoves) {
            board[coordinates.x][coordinates.y].uncolorTheSquare();
        }
    }

    private boolean isItCheckmate(Point kingPosition) {
        System.out.println("metoda isItCheckmate");
        check = isPositionUnderAttack(kingPosition);
        return check && !canKingMove(kingPosition) && !canAttackerBeStopped();
    }

    private boolean isPositionUnderAttack(Point positionToCheck) {

        Point[] verticalAndHorizontalDirections = {
                new Point(0, 1), new Point(1, 0), new Point(0, -1), new Point(-1, 0),
        };
        Point[] diagonalDirections = {
                new Point(-1, 1), new Point(1, 1), new Point(1, -1), new Point(-1, -1),
        };

        for (int i = 0; i < 4; i++) {
            if (isAttackedByQueenOrRook(positionToCheck, verticalAndHorizontalDirections[i])) {
                attackerDirection = verticalAndHorizontalDirections[i];
                return true;
            }
            if (isAttackedByQueenOrBishop(positionToCheck, diagonalDirections[i])) {
                attackerDirection = diagonalDirections[i];
                return true;
            }
        }
        return isAttackedByPawn(positionToCheck) || isAttackedByKnight(positionToCheck);
    }

    private boolean isAttackedByQueenOrBishop(Point kingPosition, Point direction) {
        Point fieldToCheck = new Point();

        for (int i = 1; i < 8; i++) {
            fieldToCheck.setLocation(kingPosition.x + i * direction.x, kingPosition.y + i * direction.y);
            if (!isPositionOnTheBoard(fieldToCheck)) break;
            if (board[fieldToCheck.x][fieldToCheck.y].isEmpty()) continue;
            if (board[fieldToCheck.x][fieldToCheck.y].isPieceBlack() != blackTurn
                    && (board[fieldToCheck.x][fieldToCheck.y].getPieceSymbol() == 'Q'
                    || board[fieldToCheck.x][fieldToCheck.y].getPieceSymbol() == 'B')) {
                System.out.println("Szach z pozycji: " + fieldToCheck + " na pozycję: " + kingPosition + " (queenOrBishop)");
                attackerPosition = fieldToCheck;
                return true;
            }
            return false;
        }
        return false;
    }

    private boolean isAttackedByQueenOrRook(Point kingPosition, Point direction) {
        Point fieldToCheck = new Point();

        for (int i = 1; i < 8; i++) {
            fieldToCheck.setLocation(kingPosition.x + i * direction.x, kingPosition.y + i * direction.y);
            if (!isPositionOnTheBoard(fieldToCheck)) break;
            if (board[fieldToCheck.x][fieldToCheck.y].isEmpty()) continue;
            if (board[fieldToCheck.x][fieldToCheck.y].isPieceBlack() != blackTurn
                    && (board[fieldToCheck.x][fieldToCheck.y].getPieceSymbol() == 'Q'
                    || board[fieldToCheck.x][fieldToCheck.y].getPieceSymbol() == 'R')) {
                System.out.println("Szach z pozycji: " + fieldToCheck + " na pozycję: " + kingPosition + " (queenOrRook)");
                attackerPosition = fieldToCheck;
                return true;
            }
            return false;
        }
        return false;
    }

    private boolean isAttackedByPawn(Point kingPosition) {
        Point positionToCheck = new Point();
        if (blackTurn) {
            positionToCheck.setLocation(kingPosition.x - 1, kingPosition.y - 1);
            if (isTherePawn(positionToCheck)) {
                attackerPosition = positionToCheck;
                attackerDirection.setLocation(0, 0);
                return true;
            }
            positionToCheck.setLocation(kingPosition.x + 1, kingPosition.y - 1);
            if (isTherePawn(positionToCheck)) {
                attackerPosition = positionToCheck;
                attackerDirection.setLocation(0, 0);
                return true;
            }

        } else {
            positionToCheck.setLocation(kingPosition.x - 1, kingPosition.y + 1);
            if (isTherePawn(positionToCheck)) {
                attackerPosition = positionToCheck;
                attackerDirection.setLocation(0, 0);
                return true;
            }
            positionToCheck.setLocation(kingPosition.x + 1, kingPosition.y + 1);
            if (isTherePawn(positionToCheck)) {
                attackerPosition = positionToCheck;
                attackerDirection.setLocation(0, 0);
                return true;
            }

        }
        return false;
    }

    private boolean isTherePawn(Point kingPosition) {
        return isPositionOnTheBoard(kingPosition)
                && !board[kingPosition.x][kingPosition.y].isEmpty()
                && board[kingPosition.x][kingPosition.y].isPieceBlack() != blackTurn
                && board[kingPosition.x][kingPosition.y].getPieceSymbol() == 'P';
    }

    private boolean isAttackedByKnight(Point kingPosition) {
        Point positionToCheck = new Point();
        Point[] directions = {
                new Point(-2, 1), new Point(-1, 2), new Point(1, 2), new Point(2, 1),
                new Point(-2, -1), new Point(-1, -2), new Point(1, 2), new Point(2, -1),
        };
        for (Point direction : directions) {
            positionToCheck.setLocation(kingPosition.x + direction.x, kingPosition.y + direction.y);
            if (isPositionOnTheBoard(positionToCheck)
                    && !board[positionToCheck.x][positionToCheck.y].isEmpty()
                    && board[positionToCheck.x][positionToCheck.y].isPieceBlack() != blackTurn
                    && board[positionToCheck.x][positionToCheck.y].getPieceSymbol() == 'N'
            ) {
                attackerPosition = positionToCheck;
                attackerDirection.setLocation(0, 0);
                return true;
            }
        }

        return false;
    }

    private boolean canKingMove(Point kingPosition) {
        King tempKing = (King) board[kingPosition.x][kingPosition.y].getPiece();
        board[kingPosition.x][kingPosition.y].setEmpty();
        Point positionToCheck = new Point();
        Point[] directions = {
                new Point(0, 1), new Point(1, 1), new Point(1, 0), new Point(1, -1),
                new Point(0, -1), new Point(-1, -1), new Point(-1, 0), new Point(-1, 1),
        };

        for (Point direction : directions) {
            positionToCheck.setLocation(kingPosition.x + direction.x, kingPosition.y + direction.y);
            if (!isPositionOnTheBoard(positionToCheck)) continue;
            if ((board[positionToCheck.x][positionToCheck.y].isEmpty() || board[positionToCheck.x][positionToCheck.y].isPieceBlack() != blackTurn)
                    && !isPositionUnderAttack(positionToCheck)) {
                System.out.println("Może król iść normalnie na: " + positionToCheck);
                board[kingPosition.x][kingPosition.y].setKing(tempKing);
                return true;
            }
        }
        return false;
    }

    private boolean canAttackerBeStopped() {
        Point kingCoordinates;
        List<Piece> listOfPieces;
        List<Point> listOfPossibleMoves;
        if (blackTurn) {
            kingCoordinates = blackKing.getCoordinates();
            listOfPieces = blackPieces;
        } else {
            kingCoordinates = whiteKing.getCoordinates();
            listOfPieces = whitePieces;
        }

        if (attackerDirection.equals(new Point(0, 0))) {
            for (Piece piece : listOfPieces) {
                listOfPossibleMoves = piece.getPossibleMoves();
                if (listOfPossibleMoves.contains(kingCoordinates)) return true;
            }
        } else {
            while (!kingCoordinates.equals(attackerPosition)) {
                for (Piece piece : listOfPieces) {
                    listOfPossibleMoves = piece.getPossibleMoves();
                    if (listOfPossibleMoves.contains(kingCoordinates)) return true;
                }
                kingCoordinates.setLocation(kingCoordinates.x + attackerDirection.x, kingCoordinates.y + attackerDirection.y);
            }
        }
        return false;
    }

    public static boolean isPositionOnTheBoard(Point coordinates) {
        return ((coordinates.x >= 0 && coordinates.x <= 7) && (coordinates.y >= 0 && coordinates.y <= 7));
    }

    public static boolean isPositionEmpty(Point coordinates) {
        return board[coordinates.x][coordinates.y].isEmpty();
    }

    public static boolean isPieceBlack(Point coordinates) {
        return board[coordinates.x][coordinates.y].isPieceBlack();
    }

    public static void setPiece(Point coordinates, Piece piece) {
        board[coordinates.x][coordinates.y].setPiece(piece);
    }

    public static boolean isQueenSideCastlingPossible(int altitudeCoordinate) {
        return board[1][altitudeCoordinate].isEmpty()
                && board[2][altitudeCoordinate].isEmpty()
                && board[3][altitudeCoordinate].isEmpty()
                && !board[0][altitudeCoordinate].isEmpty()
                && !board[0][altitudeCoordinate].getPieceHasMoved();
    }

    public static boolean isKingSideCastlingPossible(int altitudeCoordinate) {
        return board[5][altitudeCoordinate].isEmpty()
                && board[6][altitudeCoordinate].isEmpty()
                && !board[7][altitudeCoordinate].isEmpty()
                && !board[7][altitudeCoordinate].getPieceHasMoved();
    }

    public static void moveRookAfterKingSideCastling(int altitudeCoordinate) {
        board[5][altitudeCoordinate].movePieceHere(board[7][altitudeCoordinate]);
    }

    public static void moveRookAfterQueenSideCastling(int altitudeCoordinate) {
        board[3][altitudeCoordinate].movePieceHere(board[0][altitudeCoordinate]);
    }


}
