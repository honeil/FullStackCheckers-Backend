package team.squad.Model;

/**
 * @author William Mattern
 * @author John A. Squier
 *
 * This class represents a cell (square) on a game board.
 *
 * Date Created: 3/6/17.
 *
 * TODO when pieces are removed we need to put them back in their respective stack
 */
public class Cell {

    private int xPosition;
    private int yPosition;
    private Color cellColor;
    private String cellName;
    private Piece piece;
    private Boolean hasPiece = false;

    Cell(int xPosition, int yPosition){
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.cellColor = determineCellColor();
        this.cellName = determineCellName();
    }

    private String determineCellName() {
        return convertIToColumnLetter(xPosition) + convertJToRowNumber(yPosition);
    }

    private Color determineCellColor() {
        if((xPosition + yPosition) % 2 == 0) {
            return Color.BLACK;
        }
        else {
            return Color.RED;
        }
    }

    int getxPosition() {
        return this.xPosition;
    }

    int getyPosition() {
        return this.yPosition;
    }

    Color getCellColor() {
        return cellColor;
    }

    String getCellName() {
        return cellName;
    }

    Piece getPiece() {
        return piece;
    }

    void setPiece(Piece piece) {
        this.piece = piece;
        this.setHasPiece(true);
        this.piece.setXPosition(this.xPosition);
        this.piece.setYPosition(this.yPosition);
    }

    void removePiece() {
        this.piece = null;
        this.setHasPiece(false);
    }

    Boolean getHasPiece() {
        return hasPiece;
    }

    private void setHasPiece(Boolean hasPiece) {
        this.hasPiece = hasPiece;
    }

    private String convertIToColumnLetter(int i) {
        switch( i ) {
            case 0: return "A";
            case 1: return "B";
            case 2: return "C";
            case 3: return "D";
            case 4: return "E";
            case 5: return "F";
            case 6: return "G";
            case 7: return "H";
            default: return "ERROR"; // should never be hit
        }
    }

    private String convertJToRowNumber(int j) {
        switch ( j ) {
            case 0: return "1";
            case 1: return "2";
            case 2: return "3";
            case 3: return "4";
            case 4: return "5";
            case 5: return "6";
            case 6: return "7";
            case 7: return "8";
            default: return "ERROR"; // should never be hit
        }
    }
}
