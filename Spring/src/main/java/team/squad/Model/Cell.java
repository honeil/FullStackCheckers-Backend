package team.squad.Model;

/**
 * @author William Mattern
 * @author John A. Squier
 *
 * This class represents a cell (square) on a game board.
 *
 * Date Created: 3/6/17.
 */
class Cell {

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
        String result = "";
        switch( i ) {
            case 0: result = "A";
                    break;
            case 1: result = "B";
                    break;
            case 2: result = "C";
                    break;
            case 3: result = "D";
                    break;
            case 4: result = "E";
                    break;
            case 5: result = "F";
                    break;
            case 6: result = "G";
                    break;
            case 7: result = "H";
                    break;
        }
        return result;
    }

    private String convertJToRowNumber(int j) {
        String result = "";
        switch ( j ) {
            case 0: result = "1";
                    break;
            case 1: result = "2";
                    break;
            case 2: result = "3";
                    break;
            case 3: result = "4";
                    break;
            case 4: result = "5";
                    break;
            case 5: result = "6";
                    break;
            case 6: result = "7";
                    break;
            case 7: result = "8";
                    break;
        }
        return result;
    }
}
