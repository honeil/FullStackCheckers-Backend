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

    Move theMove;
    CheckersBoard theBoard = new CheckersBoard();

    public MoveHandler() { }

    public void setTheMove(Move theMove) {
        this.theMove = theMove;
    }

    public void setTheBoard(CheckersBoard theBoard) {
        this.theBoard = theBoard;
    }

    /**
     * Call this when you get a player move POST request.
     * @param theMove
     * @return
     */
    public Map generateNewBoardStateFromPlayerMove(Move theMove) {

        if ( isPlayerMoveValid(theMove, theBoard) ) {
            doMove();
        }
        return BoardState.generateBoardState(theBoard);
    }

    /**
     * Call this when you get a computer move GET request.
     * @return
     */
    public Map generateNewBoardStateFromComputerMove() {
        return BoardState.generateBoardState(theBoard);
    }

    /**
     * Call this when you want the intial board state, this always returns the state of the board at the very beginning.
     * @return
     */
    public Map generateIntialBoardState() {
        return BoardState.getInitialBoardState();
    }


    private void doMove() {
        Cell initialCell = theBoard.getCell(theMove.getxPositionInitial(), theMove.getyPositionInitial());
        Cell desiredCell = theBoard.getCell(theMove.getxPositionDesired(), theMove.getyPositionDesired());
        desiredCell.setPiece(initialCell.getPiece());
        initialCell.removePiece();
    }

    // to get the initial board state from the "get starting board state get" call
    // BoardState.getInitialBoardState()
    // this works because when constructed the checkers board is in the proper initial state.

    private boolean moveResultsInComputerMove() {
        return true;
    }

    private boolean moveResultsInAnotherPlayerMove() {
        return false;
    }

    public static boolean isPlayerMoveValid(Move move, CheckersBoard theBoard) {
        System.out.println("im all up in your methods checking if the move's valid");//////////////////////////////////////////////////////////////////////
        System.out.println("cells are adjacent? " + cellsInMoveAreAdjacent(move, theBoard));//////////////////////////////////////////////////////////////////////
        System.out.println("requested cell is empty? " + requestedCellIsEmpty(move, theBoard));//////////////////////////////////////////////////////////////////////
        if ( cellsInMoveAreAdjacent(move, theBoard) && requestedCellIsEmpty(move, theBoard) ) {
            if( movingForward(move, theBoard) || pieceIsAKing(move, theBoard)){
                return true;
            }
            return false;
        }
        else {
            return false;
        }
    }

    private static boolean pieceIsAKing(Move move, CheckersBoard theBoard) {
        Piece thePiece = theBoard.getCell(move.getxPositionInitial(),move.getyPositionInitial()).getPiece();
        return thePiece.getKing();
    }

    private static boolean movingForward(Move move, CheckersBoard theBoard) {
        Piece thePiece = theBoard.getCell(move.getxPositionInitial(),move.getyPositionInitial()).getPiece();
        switch(thePiece.getPieceColor()){
            case RED: if(move.getyPositionInitial()<move.getyPositionDesired()){
                return true;
            }
            case BLACK: if(move.getyPositionInitial()>move.getyPositionDesired()){
                return true;
            }
            default: return false;
        }
    }

    public static boolean cellsInMoveAreAdjacent(Move move, CheckersBoard theBoard) {
        if(move.getxPositionDesired() == move.getxPositionInitial()-1 || move.getxPositionDesired() == move.getxPositionInitial()+1){
            if(move.getyPositionDesired() == move.getyPositionInitial()-1 || move.getyPositionDesired() == move.getyPositionInitial()+1){
                return true;
            }
        }
        return false;
    }

    private static boolean requestedCellIsEmpty(Move move, CheckersBoard theBoard) {
        Cell toCheck = theBoard.getCell(move.getxPositionDesired(),
                                        move.getyPositionDesired());
        System.out.println("moving to x = " + move.getxPositionDesired() + " y = " + move.getyPositionDesired());
        System.out.println("cell has a peice in it? " + toCheck.getHasPiece());
        if ( toCheck.getHasPiece() ) {
            return false;
        }
        else {
            return true;
        }
    }


}
