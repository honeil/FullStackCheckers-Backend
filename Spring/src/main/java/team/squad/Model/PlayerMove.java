package team.squad.Model;

/**
 * @author William Mattern
 * @author John A. Squier
 *
 * Date Created: 3/7/17.
 *
 * Class to store information about a move.
 */
public class PlayerMove {

    protected int xPositionInitial;
    protected int yPositionInitial;
    protected int xPositionDesired;
    protected int yPositionDesired;
    protected int xPositionFinal;
    protected int yPositionFinal;

    public PlayerMove() { }

    public void setxPositionInitial(int xPositionInitial) {
        this.xPositionInitial = xPositionInitial;
    }
    public void setyPositionInitial(int yPositionInitial) {
        this.yPositionInitial = yPositionInitial;
    }

    public void setxPositionDesired(int xPositionDesired) {
        this.xPositionDesired = xPositionDesired;
    }

    public void setyPositionDesired(int yPositionDesired) {
        this.yPositionDesired = yPositionDesired;
    }

    public void setxPositionFinal(int xPositionFinal) {
        this.xPositionFinal = xPositionFinal;
    }

    public void setyPositionFinal(int yPositionFinal) {
        this.yPositionFinal = yPositionFinal;
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
