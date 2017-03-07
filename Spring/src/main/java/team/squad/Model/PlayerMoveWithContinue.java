package team.squad.Model;

/**
 * @author William Mattern
 * @author John A. Squier
 *
 * Date Created: 3/6/17.
 *
 * This class will be used to indicate the players' move and will also tell the front end the player gets to go again,
 * i.e. the player jumps a piece successfully
 */
public class PlayerMoveWithContinue extends Move {

    private int xPositionDesired;
    private int yPositionDesired;

    public void setxPositionDesired(int xPositionInitial) {
        this.xPositionDesired = xPositionInitial;
    }
    public void setyPositionDesired(int yPositionInitial) {
        this.yPositionDesired = yPositionInitial;
    }
}
