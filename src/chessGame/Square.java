package chessGame;

import chessGame.pieces.Piece;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Square extends JButton{
    private Point coordinates;
    private Piece piece;
    public Square(Piece piece, Point coordinates) {
        this.piece = piece;
        this.coordinates = coordinates;
    }
    public Point getCoordinates() {
        return coordinates;
    }
    public Boolean isPieceBlack(){
        if (!isEmpty()) return piece.isBlack();
        return null;
    }
    public boolean isEmpty(){
        if (this.piece == null) return true;
        return false;
    }
    public void setPiece(Piece piece) {
        this.piece = piece;
        BufferedImage pieceImage = null;

        char colorFirstLetter;
        String fileExtension = ".png";
        if(this.piece.isBlack()) colorFirstLetter = 'b';
        else colorFirstLetter = 'w';

        String imagePathname = "../src/images/" + colorFirstLetter + this.piece.getClass().getSimpleName() + fileExtension;

        try {
            pieceImage = ImageIO.read(new File(imagePathname));
        } catch (IOException e) {
            System.out.println("Could not load a piece image");
        }

        this.setIcon(new ImageIcon(pieceImage));
    }


}
