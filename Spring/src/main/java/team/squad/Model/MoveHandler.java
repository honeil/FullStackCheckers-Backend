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

    Move theMove;
    CheckersBoard theBoard;

    public MoveHandler(Move move, CheckersBoard theBoard) {
        this.theMove = move;
        this.theBoard =  theBoard;
    }

    public Move checkGivenMoveAndUpdateBoard() {
        if ( playerMoveIsValid() && moveResultsInAnotherPlayerMove() ) {
            theMove.setxPositionFinal(theMove.getxPositionDesired());
            theMove.setyPositionFinal(theMove.getyPositionDesired());
            return theMove;
        }
        else if ( playerMoveIsValid() && moveResultsInComputerMove() ) {
            // what to do when the computer goes next
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
