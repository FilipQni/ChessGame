package chessGame.pieces;
import javax.imageio.ImageIO;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Piece {
    private Point coordinates;
    private final boolean isBlack;

    public Piece(boolean isBlack, Point coordinates) {
        this.coordinates = coordinates;
        this.isBlack = isBlack;
    }

    public BufferedImage loadPieceImage(){
        BufferedImage img = null;
        try {
            System.out.println("Present Project Directory : "+ System.getProperty("user.dir"));
            img = ImageIO.read(new File("../src/images/test.png"));
        } catch (IOException e) {
            System.out.println("Could not load a piece image");
        }
        return img;
    }

}
