package chessGame.pieces;

public class Piece {
    protected final boolean isBlack;

    public Piece(boolean isBlack) {
        this.isBlack = isBlack;
    }

    public boolean isBlack() {
        return isBlack;
    }

}
