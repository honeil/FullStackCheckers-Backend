package team.squad.Model;

import java.util.Map;

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

    private Move theMove;
    private CheckersBoard theBoard;

    public MoveHandler() { }

    // call this when the Front end posts a player move
    public static Map generateNewBoardStateFromPlayerMove(Move move, CheckersBoard theBoard) {
        // things to do in this method...
        return null;
    }

    // call this when the front end gets a computer move
    public static Map generateNewBoardStateFromComputerMove(CheckersBoard theBoard) {
        return null;
    }

    // to get the initial board state from the "get starting board state get" call
    // BoardState.getInitialBoardState()
    // this works because when constructed the checkers board is in the proper initial state.






















    // use this constructor when it's a player move
//    public MoveHandler(Move move, CheckersBoard theBoard) {
//        this.theMove = move;
//        this.theBoard = theBoard;
//    }

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
