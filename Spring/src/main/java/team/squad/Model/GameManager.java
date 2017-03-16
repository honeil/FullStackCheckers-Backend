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
 * and who should go next after the given move. This class also uses the ComputerMove class to generate
 * random computer moves (for now) when requested using the generateNewBoardStateFromComputerMove() method.
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

    /**
     * Call this when you get a player move POST request.
     * Checks if the given player move is a valid move and if so alters the board state. If the move is not valid
     * then an unchanged board state is returned.
     *
     * @return The updated board state as a map.
     */
    public List<Map> generateNewBoardStateFromPlayerMove() {
        if (isMoveValidJumpMove() && selectedPieceIsRed() ) {
            doJump();
            if ( moveResultsInAKing() ) {
                Piece toKing = theBoard.getCell(theMove.getxPositionDesired(), theMove.getyPositionDesired()).getPiece();
                toKing.setKing(true);
            }
            return BoardStateGenerator.generateBoardState(theBoard, false); // player doesn't get to go again
        } else if (isMoveValidAdjacentMove() && selectedPieceIsRed() ) {
            doMove();
            if ( moveResultsInAKing() ) {
                Piece toKing = theBoard.getCell(theMove.getxPositionDesired(), theMove.getyPositionDesired()).getPiece();
                toKing.setKing(true);
            }
            return BoardStateGenerator.generateBoardState(theBoard, false); // player moves and it's the computer's turn
        } else {
            return BoardStateGenerator.generateBoardState(theBoard, true); // player move is invalid
        }
    }

    /**
     * Checks if the move (which we already know is valid because this method is only called after doJump() or doMove())
     * results in the piece being moved becoming a king.
     * @return a boolean indicating if the piece has become a king or not.
     */
    boolean moveResultsInAKing() {
        if ( theMove.getyPositionDesired() == 7
                || theMove.getyPositionDesired() == 0 ) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Checks if the given move is a valid jump based on a few conditions, piece must be red.
     * @return a boolean indicating if the move is a valid jump or not.
     */
    boolean isMoveValidJumpMove() {
        if ( startAndFinishAreDiagonallyOneSquareApart()
                && thereIsAnOpponentPieceInTheMiddle()
                && movingForward() ) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Checks if the given move's initial and desired cells are diagonally adjacent to each other.
     * @return a boolean indicating if the move's cells are diagonally adjacent.
     */
    boolean isMoveValidAdjacentMove() {
        if ( cellsInMoveAreAdjacent() && requestedCellIsEmpty()
                && startCellHasPieceInIt() && movingForward() ) {
                return true;
            }
            else {
                return false;
            }
        }

    /**
     * Checks if the initial cell in the given move contains a red piece.
     * @return a boolean indicating if the given cell contains a red piece.
     */
    private boolean selectedPieceIsRed() {
        Piece pieceToCheck = theBoard.getCell(theMove.getxPositionInitial(), theMove.getyPositionInitial()).getPiece();
        if ( pieceToCheck.getPieceColor().equals(Color.RED) ) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Checks if there is an opponent piece in the middle of a potential jump move
     * @return a boolean indicating if there is an opponent piece in the middle of the two cells given in the move.
     */
    boolean thereIsAnOpponentPieceInTheMiddle() {
        Cell start = theBoard.getCell(theMove.getxPositionInitial(), theMove.getyPositionInitial());
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

    /**
     * Ensures that the initial and desired cell are diagonally one square apart
     * @return a boolean indicating if the given cells in theMove are diagonally one space apart.
     */
    boolean startAndFinishAreDiagonallyOneSquareApart() {
        if (Math.abs(theMove.getxPositionDesired() - theMove.getxPositionInitial()) == 2 && Math.abs(theMove.getyPositionDesired() - theMove.getyPositionInitial()) ==2) {
            return true;
        }
        return false;
    }

    /**
     * Ensures that the two cells given in theMove are diagonally adjacent.
     * @return a boolean indicating whether the two cells are diagonally adjacent.
     */
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

    /**
     * Ensures the given desired cell in theMove is empty.
     * @return a boolean indicating whether the desired cell is empty.
     */
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

    /**
     * Ensures that the initial cell in theMove contains a piece.
     * @return a boolean indicating if the start cell contains a piece.
     */
    private boolean startCellHasPieceInIt() {
        Cell toCheck = theBoard.getCell(theMove.getxPositionInitial(), theMove.getyPositionInitial());
        if ( toCheck.getHasPiece() ) {//&& toCheck.getPiece().getPieceColor().equals(Color.RED)) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Checks if the given move is 'forward' depending on the color of the piece being moved, kings are always
     * moving 'forward'.
     * @return a boolean indicating whether or not the requested move is a 'forward' move.
     */
    boolean movingForward() {
        Piece thePiece = theBoard.getCell(theMove.getxPositionInitial(), theMove.getyPositionInitial()).getPiece();
        if ( thePiece == null ) {
            return false;
        }
        switch(thePiece.getPieceColor()){
            case RED:
                if (thePiece.getKing()) {
                    return true;
                }
                else if(theMove.getyPositionInitial() < theMove.getyPositionDesired()){
                    return true;
                }
                else {
                    return false;
                }
            case BLACK:
                if (thePiece.getKing()) {
                    return true;
                }
                else if(theMove.getyPositionInitial() > theMove.getyPositionDesired()){
                    return true;
                }
                else {
                    return false;
                }
            default: return false; // should never be hit
        }
    }

    /**
     * Checks if the piece in the start cell is a king.
     * @return a boolean indicating whether or not the piece in initial cell is a king piece.
     */
    boolean pieceIsAKing() {
        Piece thePiece = theBoard.getCell(theMove.getxPositionInitial(), theMove.getyPositionInitial()).getPiece();
        return thePiece.getKing();
    }

    /**
     * Completes the given move, checks have already been made to ensure the move is valid before this method is called.
     */
    private void doMove() {
        Cell initialCell = theBoard.getCell(theMove.getxPositionInitial(), theMove.getyPositionInitial());
        Cell desiredCell = theBoard.getCell(theMove.getxPositionDesired(), theMove.getyPositionDesired());
        desiredCell.setPiece(initialCell.getPiece());
        initialCell.removePiece();
    }

    /**
     * Completes the given jump move, checks have already been made to ensure the move is valid before this method is called.
     */
    private void doJump() {
        System.out.println("DOING JUMP");
        Cell initialCell = theBoard.getCell(theMove.getxPositionInitial(), theMove.getyPositionInitial());
        Cell desiredCell = theBoard.getCell(theMove.getxPositionDesired(), theMove.getyPositionDesired());
        int xOfMiddleCell = (int)((theMove.getxPositionInitial() + theMove.getxPositionDesired()) / 2.0);
        int yOfMiddleCell = (int)((theMove.getyPositionInitial() + theMove.getyPositionDesired()) / 2.0);

        Cell middleCell = theBoard.getCell(xOfMiddleCell, yOfMiddleCell);
        desiredCell.setPiece(initialCell.getPiece());
        initialCell.removePiece();
        theBoard.addPieceToStack(middleCell.getPiece()); // add the jumped piece to its stack
        middleCell.removePiece();
    }

    /**
     * Call this when you get a computer move GET request.
     * @return The updated board state after the computer has made its BOGO move.
     */
    public List<Map> generateNewBoardStateFromComputerMove() {
        theMove = ai.generateMove(theBoard);
        this.setTheMove(theMove);

        if(isMoveValidJumpMove()) {
            System.out.println("DOING COMPUTER JUMP");
            doJump();
            if ( moveResultsInAKing() ) {
                Piece toKing = theBoard.getCell(theMove.getxPositionDesired(), theMove.getyPositionDesired()).getPiece();
                toKing.setKing(true);
            }
            return BoardStateGenerator.generateBoardState(theBoard, true);
        } else {
            System.out.println("DOING NON JUMP COMPUTER");
            doMove();
            if ( moveResultsInAKing() ) {
                Piece toKing = theBoard.getCell(theMove.getxPositionDesired(), theMove.getyPositionDesired()).getPiece();
                toKing.setKing(true);
            }
            return BoardStateGenerator.generateBoardState(theBoard, true);
        }
    }

    /**
     * Call this when you want the initial board state, this always returns the state of the board at the very beginning.
     * Can also be used to reset the game by calling this in the middle of the game.
     * @return a List of Maps that represents the required data for the front end.
     */
    public List<Map> generateInitialBoardState() {
        theBoard = new CheckersBoard();
        System.out.println("generating new board");
        return BoardStateGenerator.generateBoardState(theBoard, true);
    }
    
    public List<Map> getCurrentBoardStateForSave() {
        return BoardStateGenerator.generateBoardState(theBoard, true);
    }
}
