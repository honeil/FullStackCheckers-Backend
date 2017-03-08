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
 *
 * TODO refactor some of the methods, those jaunts are a mess
 */
public class MoveHandler {

    Move theMove;
    CheckersBoard theBoard = new CheckersBoard();

    public MoveHandler() { }

    /**
     * Use this to set the move as given from the front end.
     * @param theMove
     */
    public void setTheMove(Move theMove) {
        this.theMove = theMove;
    }

    /**
     * Call this when you get a player move POST request.
     * Checks if the given player move is a valid move and if so alters the board state. If the move is not valid
     * then an unchanged board state is returned.
     * @return
     */
    public Map generateNewBoardStateFromPlayerMove() {
        if ( isPlayerMoveValid() ) {
            doMove();
        }
        return BoardState.generateBoardState(theBoard);
    }

    /**
     * Call this when you get a computer move GET request.
     * Right now this just returns the current board without changing the board with any AI.
     * @return
     */
    public Map generateNewBoardStateFromComputerMove() {
        return BoardState.generateBoardState(theBoard);
    }

    /**
     * Call this when you want the initial board state, this always returns the state of the board at the very beginning.
     * @return
     */
    public Map generateInitialBoardState() {
        return BoardState.getInitialBoardState();
    }


    public boolean isPlayerMoveValid() {
        if ( cellsInMoveAreAdjacent() && requestedCellIsEmpty() ) {
            if( movingForward() || pieceIsAKing()){
                return true;
            }
            return false;
        }
        else {
            return false;
        }
    }

    private void doMove() {
        Cell initialCell = theBoard.getCell(theMove.getxPositionInitial(), theMove.getyPositionInitial());
        Cell desiredCell = theBoard.getCell(theMove.getxPositionDesired(), theMove.getyPositionDesired());
        desiredCell.setPiece(initialCell.getPiece());
        initialCell.removePiece();
    }

    boolean pieceIsAKing() {
        Piece thePiece = theBoard.getCell(theMove.getxPositionInitial(), theMove.getyPositionInitial()).getPiece();
        return thePiece.getKing();
    }

    boolean movingForward() {
        Piece thePiece = theBoard.getCell(theMove.getxPositionInitial(), theMove.getyPositionInitial()).getPiece();
        switch(thePiece.getPieceColor()){
            case RED:
                if(theMove.getyPositionInitial() < theMove.getyPositionDesired()){
                    return true;
                }
                else {
                    return false;
                }
            case BLACK:
                if(theMove.getyPositionInitial() > theMove.getyPositionDesired()){
                    return true;
                }
                else {
                    return false;
                }
            default: return false; // should never be hit
        }
    }

    boolean cellsInMoveAreAdjacent() {
        if(theMove.getxPositionDesired() == theMove.getxPositionInitial()-1 || theMove.getxPositionDesired() == theMove.getxPositionInitial()+1){
            if(theMove.getyPositionDesired() == theMove.getyPositionInitial()-1 || theMove.getyPositionDesired() == theMove.getyPositionInitial()+1){
                return true;
            }
        }
        return false;
    }

    boolean requestedCellIsEmpty() {
        Cell toCheck = theBoard.getCell(theMove.getxPositionDesired(),
                                        theMove.getyPositionDesired());
        if ( toCheck.getHasPiece() ) {
            return false;
        }
        else {
            return true;
        }
    }


}
