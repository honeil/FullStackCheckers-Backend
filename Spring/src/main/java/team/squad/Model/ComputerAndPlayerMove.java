package team.squad.Model;

/**
 * @author William Mattern
 * @author John A. Squier
 *
 * Date Created: 3/7/17.
 *
 * This class will be used to return information about the players move and will also contain the following move
 *  that the computer desires to make.
 */
public class ComputerAndPlayerMove extends PlayerMove {

    private int computerXPositionInitial;
    private int computerYPostitionInitial;
    private int computerXPositionFinal;
    private int computerYPositionFinal;

    public ComputerAndPlayerMove(PlayerMove playersMove) {
        this.xPositionInitial = playersMove.xPositionInitial;
        this.yPositionInitial = playersMove.yPositionInitial;
        this.xPositionDesired = playersMove.xPositionDesired;
        this.yPositionDesired = playersMove.yPositionDesired;
        // set the players desired position to the final position aka the player move is made
        this.xPositionFinal = playersMove.xPositionDesired;
        this.yPositionFinal = playersMove.yPositionFinal;
    }

    /**
     * Method to generate an A.I move.
     * @return
     */
    public ComputerAndPlayerMove generateAIMove() {
        this.computerXPositionInitial =  2;
        this.computerYPostitionInitial = 2; // this will represent the piece we want to move

        this.computerXPositionFinal = 3;
        this.computerYPositionFinal = 3; // and this will be where we move the piece to.

        return this;
    }
}
