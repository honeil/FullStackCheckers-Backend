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

    public MoveHandler() { }

    // call this when the Front end posts a player move
    public static Map generateNewBoardStateFromPlayerMove(Move move, CheckersBoard theBoard) {
        System.out.println("Im a player move, ehhhhhhhhh");
        if ( isPlayerMoveValid(move, theBoard) ) {
            System.out.println("player move was valid");
            doMove(move,theBoard);
        }
        return BoardState.generateBoardState(theBoard);
    }

    private static void doMove(Move move, CheckersBoard theBoard) {
        Cell initialCell = theBoard.getCell(move.getxPositionInitial(),move.getyPositionInitial());
        Cell desiredCell = theBoard.getCell(move.getxPositionDesired(),move.getyPositionDesired());
        desiredCell.setPiece(initialCell.getPiece());
        initialCell.removePiece();
    }

    // call this when the front end gets a computer move
    public static Map generateNewBoardStateFromComputerMove(CheckersBoard theBoard) {
        return null;
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

    private static boolean isPlayerMoveValid(Move move, CheckersBoard theBoard) {
        System.out.println("im all up in your methods checking if the move's valid");
        System.out.println("cells are adjacent? " + cellsInMoveAreAdjacent(move, theBoard));
        System.out.println("requested cell is empty? " + requestedCellIsEmpty(move, theBoard));
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

    private static boolean cellsInMoveAreAdjacent(Move move, CheckersBoard theBoard) {
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
