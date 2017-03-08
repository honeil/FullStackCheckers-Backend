package team.squad.Model;

/**
 * @author William Mattern
 * @author John A. Squier
 *
 * Date Created: 3/7/17.
 *
 * Class to store information about a move.
 */
public class Move {

    private String initialCell;
    private String desiredCell;
    private int xPositionInitial;
    private int yPositionInitial;
    private int xPositionDesired;
    private int yPositionDesired;
    private int xPositionFinal;
    private int yPositionFinal;

    public Move() { }


    public void setInitialCell(String intialCell) {
        this.initialCell = intialCell;
        this.xPositionInitial = convertCellNameToXCoord(intialCell);
        this.yPositionInitial = convertCellNameToYCoord(intialCell);
    }

    public void setDesiredCell(String desiredCell) {
        this.desiredCell = desiredCell;
        this.xPositionDesired = convertCellNameToXCoord(desiredCell);
        this.yPositionDesired = convertCellNameToYCoord(desiredCell);
    }

    private int convertCellNameToXCoord(String theCell) {
        switch ( theCell.charAt(0) ) {
            case 'A': return 0;
            case 'B': return 1;
            case 'C': return 2;
            case 'D': return 3;
            case 'E': return 4;
            case 'F': return 5;
            case 'G': return 6;
            case 'H': return 7;
            default: return -1;
        }
    }

    private int convertCellNameToYCoord(String theCell) {
        switch ( theCell.charAt(1) ) {
            case '1': return 0;
            case '2': return 1;
            case '3': return 2;
            case '4': return 3;
            case '5': return 4;
            case '6': return 5;
            case '7': return 6;
            case '8': return 7;
            default: return -1;
        }
    }

    public String getInitialCell() {
        return initialCell;
    }
    public String getDesiredCell() {
        return desiredCell;
    }
    public int getxPositionInitial() {
        return xPositionInitial;
    }
    public int getyPositionInitial() {
        return yPositionInitial;
    }
    public int getxPositionDesired() {
        return xPositionDesired;
    }
    public int getyPositionDesired() {
        return yPositionDesired;
    }
    public int getxPositionFinal() {
        return xPositionFinal;
    }
    public int getyPositionFinal() {
        return yPositionFinal;
    }

    // set the final x and y using some a.i. like algorithm
    // these are just dummy values

    // things we need to do to complete a move

    // player move
    // check if there is a piece at the initial position
    // check if a move from initial to the desired position is valid
    //      do the cells "connect", is there a piece in that cell already?, can the piece move in that direction?
    // if the move is valid, we return the final position of the piece and update our model.
    // if the move is invalid we send some sort of error message / indication
    // THIS DOES NOT CONSIDER JUMPS ATM

    // computer move
    // determine which piece to move
    // then determine where to move that piece
    // BOGO checkers, randomly pick a piece and then randomly move that piece to one of the available places
}
