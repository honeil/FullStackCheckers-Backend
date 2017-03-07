package team.squad.Model;

/**
 * @author William Mattern
 * @author John A. Squier
 *
 * Date Created: 3/6/17.
 *
 */
public class Cell {

    private int xPosition;
    private int yPosition;
    private Color cellColor;
    private Piece piece;
    private Boolean hasPiece = false;

    public Cell(int xPosition, int yPosition){
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.cellColor = determineCellColor();
    }

    private Color determineCellColor() {
        if((xPosition + yPosition) % 2 == 0) {
            return Color.BLACK;
        }
        else {
            return Color.RED;
        }
    }

    public Color getCellColor() {
        return cellColor;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
        this.setHasPiece(true);
        this.piece.setXPosition(this.xPosition);
        this.piece.setYPosition(this.yPosition);
    }

    public void removePiece() {
        this.piece = null;
        this.setHasPiece(false);
    }

    public Boolean getHasPiece() {
        return hasPiece;
    }

    private void setHasPiece(Boolean hasPiece) {
        this.hasPiece = hasPiece;
    }
}
