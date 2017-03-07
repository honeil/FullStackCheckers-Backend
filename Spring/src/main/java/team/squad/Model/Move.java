package team.squad.Model;

/**
 * @author William Matter
 * @author John A. Squier
 *
 * Date Created: 3/7/17.
 *
 * Class to store information about a move, this class represents the object that will be returned to the front end.
 */
public class Move {

    private int xPositionInitial;
    private int yPositionInitial;
    private int xPositionDesired;
    private int yPositionDesired;
    private int xPositionFinal;
    private int yPositionFinal;

    public Move() { }

    public void setxPositionInitial(int xPositionInitial) {
        this.xPositionInitial = xPositionInitial;
    }
    public void setyPositionInitial(int yPositionInitial) {
        this.yPositionInitial = yPositionInitial;
    }

    public void setxPositionDesired(int xPositionInitial) {
        this.xPositionDesired = xPositionInitial;
    }
    public void setyPositionDesired(int yPositionInitial) {
        this.yPositionDesired = yPositionInitial;
    }

    public int getxPositionInitial() {
        return xPositionInitial;
    }

    public int getyPositionInitial() {
        return yPositionInitial;
    }

    public int getxPositionFinal() {
        return xPositionFinal;
    }

    public int getyPositionFinal() {
        return yPositionFinal;
    }

    public Move generateMove() {
        // set the final x and y using some a.i. like algorithm
        // these are just dummy values
        xPositionFinal = 100;
        yPositionFinal = 100;
        return this;
    }
}
