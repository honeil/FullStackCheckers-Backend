package team.squad.Model;

/**
 * @author William Mattern
 * @author John A. Squier
 *
 * This class is used to represent a piece in a game.
 *
 * Date Created: 3/6/17.
 */
public class Piece {

    private Color pieceColor;
    private int xPosition;
    private int yPosition;
    private Boolean isKing;

    Piece(Color pieceColor) {
        this.pieceColor = pieceColor;
        this.isKing = false;
    }

    int getXPosition() {
        return xPosition;
    }

    void setXPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    int getYPosition() {
        return yPosition;
    }

    void setYPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    Color getPieceColor() {
        return pieceColor;
    }

    void setPieceColor(Color pieceColor) {
        this.pieceColor = pieceColor;
    }

    Boolean getKing() {
        return isKing;
    }

    void setKing(Boolean king) {
        isKing = king;
    }
}

