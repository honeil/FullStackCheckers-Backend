package team.squad.checkers;

/**
 * Created by williammattern on 3/6/17.
 */
public class Cell {
    private Color cellColor;
    private Boolean hasPiece = false;
    private int xPosition;
    private int yPosition;
    private Piece checker;

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

    public Boolean getHasPiece() {
        return hasPiece;
    }

    public void setHasPiece(Boolean hasPiece) {
        this.hasPiece = hasPiece;
    }

    public Piece getChecker() {
        return checker;
    }

    public void setChecker(Piece checker) {
        this.checker = checker;
        this.checker.setXPosition(this.xPosition);
        this.checker.setYPosition(this.yPosition);
    }

    public void removeChecker() {
        this.checker = null;
        this.setHasPiece(false);
    }
}
