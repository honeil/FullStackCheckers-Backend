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

    private String firstCoordinate;
    private String secondCoordinate;
    private int xPositionInitial;
    private int yPositionInitial;
    private int xPositionDesired;
    private int yPositionDesired;

    public Move() { }


    public void setFirstCoordinate(String intialCell) {
        this.firstCoordinate = intialCell;
        this.xPositionInitial = convertCellNameToXCoord(intialCell);
        this.yPositionInitial = convertCellNameToYCoord(intialCell);
    }

    public void setSecondCoordinate(String secondCoordinate) {
        this.secondCoordinate = secondCoordinate;
        this.xPositionDesired = convertCellNameToXCoord(secondCoordinate);
        this.yPositionDesired = convertCellNameToYCoord(secondCoordinate);
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

    public String getFirstCoordinate() {
        return firstCoordinate;
    }
    public String getSecondCoordinate() {
        return secondCoordinate;
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
}
