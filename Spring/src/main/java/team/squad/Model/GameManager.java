package team.squad.Model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author William Mattern
 * @author John A. Squier
 *
 * This class takes information about a move and does all the necessary checking to determine if the move is valid,
 * and who should go next after the given move. This class also generates random computer moves (for now) when
 * requested using the generateNewBoardStateFromComputerMove() method.
 *
 * Date Created: 3/7/17.
 *
 * TODO refactor some of the methods, those jawns are a mess
 */
@Component
public class GameManager {

    private Move theMove;
    private CheckersBoard theBoard = new CheckersBoard();
    private ComputerPlayer ai = new ComputerPlayer();

    public GameManager() { }

    /**
     * Use this to set the move as given from the front end.
     * @param theMove Object representing the desired move.
     */
    public void setTheMove(Move theMove) {
        this.theMove = theMove;
    }

    /**
     * Might be for testing only.
     * @param theBoard
     */
    void setTheBoard(CheckersBoard theBoard) {
        this.theBoard = theBoard;
    }

    public CheckersBoard getTheBoard(){
        return this.theBoard;
    }

    /**
     * Call this when you get a player move POST request.
     * Checks if the given player move is a valid move and if so alters the board state. If the move is not valid
     * then an unchanged board state is returned.
     *
     * TODO make sure the player can only move red pieces
     *
     * Need to add logic for handling jumps, a move can be adjacent or possibly a jump move.
     * @return The updated board state as a map.
     */
    public List<Map> generateNewBoardStateFromPlayerMove() {
        if (isMoveValidJumpMove()) {
            doJump();
            return BoardState.generateBoardState(theBoard, false); // player might go again if he jumps
        } else if (isMoveValidAdjacentMove()) {
            doMove();
            return BoardState.generateBoardState(theBoard, false);
        } else {
            return BoardState.generateBoardState(theBoard, true);
        }
    }

    public boolean isMoveValidJumpMove() {
        if ( startAndFinishAreDiagonallyOneSquareApart()
                && thereIsAnOpponentPieceInTheMiddle()
                && selectedPieceIsRed() ) {
            return true;
        }
        return false;
    }

    private boolean selectedPieceIsRed() {
        Piece pieceToCheck = theBoard.getCell(theMove.getxPositionInitial(), theMove.getyPositionInitial()).getPiece();
        if ( pieceToCheck.getPieceColor().equals(Color.RED) ) {
            return true;
        }
        else {
            return false;
        }
    }

    boolean thereIsAnOpponentPieceInTheMiddle() {
        Cell start = theBoard.getCell(theMove.getxPositionInitial(), theMove.getyPositionInitial());
        Cell finish = theBoard.getCell(theMove.getxPositionDesired(), theMove.getyPositionDesired());
        int xOfMiddleCell = (int)((theMove.getxPositionInitial() + theMove.getxPositionDesired()) / 2.0);
        int yOfMiddleCell = (int)((theMove.getyPositionInitial() + theMove.getyPositionDesired()) / 2.0);

        Cell middleCell = theBoard.getCell(xOfMiddleCell, yOfMiddleCell);

        if ( start.getPiece().getPieceColor().equals(Color.RED) ) {
            return middleCell.getHasPiece() && middleCell.getPiece().getPieceColor().equals(Color.BLACK);
        }
        if ( start.getPiece().getPieceColor().equals(Color.BLACK) ) {
            return middleCell.getHasPiece() && middleCell.getPiece().getPieceColor().equals(Color.RED);
        }
        else {
            return false;
        }
    }

    public boolean startAndFinishAreDiagonallyOneSquareApart() {
        if (Math.abs(theMove.getxPositionDesired() - theMove.getxPositionInitial()) == 2 && Math.abs(theMove.getyPositionDesired() - theMove.getyPositionInitial()) ==2) {
            return true;
        }
        return false;
    }

    boolean isMoveValidAdjacentMove() {
        if ( cellsInMoveAreAdjacent() && requestedCellIsEmpty() && startCellHasPieceInIt() ) {
            if( movingForward() || pieceIsAKing()){
                return true;
            }
            return false;
        }
        else {
            return false;
        }
    }

    boolean cellsInMoveAreAdjacent() {
        if(theMove.getxPositionDesired() == theMove.getxPositionInitial()-1
                || theMove.getxPositionDesired() == theMove.getxPositionInitial()+1){
            if(theMove.getyPositionDesired() == theMove.getyPositionInitial()-1
                    || theMove.getyPositionDesired() == theMove.getyPositionInitial()+1){
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

    private boolean startCellHasPieceInIt() {
        Cell toCheck = theBoard.getCell(theMove.getxPositionInitial(), theMove.getyPositionInitial());
        if ( toCheck.getHasPiece() ) {//&& toCheck.getPiece().getPieceColor().equals(Color.RED)) {
            return true;
        }
        else {
            return false;
        }
    }

    boolean movingForward() {
        Piece thePiece = theBoard.getCell(theMove.getxPositionInitial(), theMove.getyPositionInitial()).getPiece();
        if ( thePiece == null ) {
            return false;
        }
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

    boolean pieceIsAKing() {
        Piece thePiece = theBoard.getCell(theMove.getxPositionInitial(), theMove.getyPositionInitial()).getPiece();
        return thePiece.getKing();
    }

    private void doMove() {
        Cell initialCell = theBoard.getCell(theMove.getxPositionInitial(), theMove.getyPositionInitial());
        Cell desiredCell = theBoard.getCell(theMove.getxPositionDesired(), theMove.getyPositionDesired());
        desiredCell.setPiece(initialCell.getPiece());
        initialCell.removePiece();
    }

    public void doJump() {
        Cell initialCell = theBoard.getCell(theMove.getxPositionInitial(), theMove.getyPositionInitial());
        Cell desiredCell = theBoard.getCell(theMove.getxPositionDesired(), theMove.getyPositionDesired());
        int xOfMiddleCell = (int)((theMove.getxPositionInitial() + theMove.getxPositionDesired()) / 2.0);
        int yOfMiddleCell = (int)((theMove.getyPositionInitial() + theMove.getyPositionDesired()) / 2.0);

        Cell middleCell = theBoard.getCell(xOfMiddleCell, yOfMiddleCell);
        desiredCell.setPiece(initialCell.getPiece());
        initialCell.removePiece();
        middleCell.removePiece();
    }

    /**
     * Call this when you get a computer move GET request.
     * @return The updated board state after the computer has made its BOGO move.
     * Jumps are not incorporated at this point
     */
    public List<Map> generateNewBoardStateFromComputerMove() {
        theMove = ai.generateMove(theBoard);
        this.setTheMove(theMove);

        if(isMoveValidJumpMove()) {
            doJump();
            return BoardState.generateBoardState(theBoard, true);
        } else {
            doMove();
            return BoardState.generateBoardState(theBoard, true);
        }
    }

    /**
     * Call this when you want the initial board state, this always returns the state of the board at the very beginning.
     * Can also be used to reset the game by calling this in the middle of the game.
     * @return
     */
    public List<Map> generateInitialBoardState() {
        theBoard = new CheckersBoard();
        System.out.println("generating new board");
        return BoardState.generateBoardState(theBoard, true);
    }
}
