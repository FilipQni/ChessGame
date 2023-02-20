package chessGame;

import chessGame.pieces.Piece;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Square extends JButton {
    private static final Color DARK_SQUARE_COLOR = new Color(181, 135, 99, 255);
    private static final Color BRIGHT_SQUARE_COLOR = new Color(240, 218, 181, 255);
    private final Point coordinates;
    private Piece piece;
    private boolean isBlack;

    public Square(Piece piece, Point coordinates) {
        this.piece = piece;
        this.coordinates = coordinates;
        this.isBlack = (coordinates.x + coordinates.y) % 2 == 0;
        if (this.isBlack) setBackground(DARK_SQUARE_COLOR);
        else setBackground(BRIGHT_SQUARE_COLOR);
    }

    public Piece getPiece() {
        return this.piece;
    }

    public void removePiece() {
        this.piece = null;
        this.setIcon(null);
    }

    public boolean isSquareBlack() {
        return this.isBlack;
    }

    public Point getCoordinates() {
        return coordinates;
    }

    public Boolean isPieceBlack() {
        if (!isEmpty()) return piece.isBlack();
        return null;
    }

    public boolean isEmpty() {
        return this.piece == null;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
        BufferedImage pieceImage = null;

        char colorFirstLetter;
        String fileExtension = ".png";
        if (this.piece.isBlack()) colorFirstLetter = 'b';
        else colorFirstLetter = 'w';

        String imagePathname = "../src/images/" + colorFirstLetter + this.piece.getClass().getSimpleName() + fileExtension;

        try {
            pieceImage = ImageIO.read(new File(imagePathname));
        } catch (IOException e) {
            System.out.println("Could not load a piece image");
        }

        this.setIcon(new ImageIcon(pieceImage));
    }

    public List<Point> getPossibleMoves(Square[][] board) {

        return piece.getPossibleMoves(board);
    }

    public void uncolorTheSquare() {
        if (isBlack) setBackground(DARK_SQUARE_COLOR);
        else setBackground(BRIGHT_SQUARE_COLOR);
    }

    public void colorTheSquare() {
        setBackground(Color.RED);
    }

    public void setPieceHasMoved(){
        this.piece.setHasMoved();
    }

    public boolean getPiecehasMoved(){
        return this.piece.getHasMoved();
    }

    public void movePieceHere(Square[][] board, Square previousPosition ){
        previousPosition.piece.move(board, this.coordinates);
        previousPosition.removePiece();
    }
}
