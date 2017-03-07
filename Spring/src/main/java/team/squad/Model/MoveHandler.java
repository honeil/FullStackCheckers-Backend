package team.squad.Model;

/**
 * @author William Mattern
 * @author John A. Squier
 *
 * Date Created: 3/7/17.
 *
 * This class takes information about a move and does all the necessary checking to determine if the move is valid,
 * and who should go next after the given move.
 */
public class MoveHandler {

    PlayerMove thePlayerMove;

    public MoveHandler(PlayerMove playerMove) {
        this.thePlayerMove = playerMove;
    }

    public PlayerMove checkGivenMoveAndDetermineNextTurn() {
        if ( playerMoveIsValid() && moveResultsInAnotherPlayerMove() ) {
            thePlayerMove.setxPositionFinal(thePlayerMove.xPositionDesired);
            thePlayerMove.setyPositionFinal(thePlayerMove.yPositionDesired);
            return thePlayerMove;
        }
        else if ( playerMoveIsValid() && moveResultsInComputerMove() ) {
            return new ComputerAndPlayerMove(thePlayerMove);
        }
        else { // player move isn't valid
            // what to return here?
        }
        return null;
    }

    private boolean moveResultsInComputerMove() {
        return true;
    }

    private boolean moveResultsInAnotherPlayerMove() {
        return false;
    }

    private boolean playerMoveIsValid() {
        return true;
    }
}
