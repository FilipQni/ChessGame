package chessGame;

import chessGame.pieces.Piece;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Square extends JButton {

    private Piece piece;

    public Square(Piece piece) {
        this.piece = piece;
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
