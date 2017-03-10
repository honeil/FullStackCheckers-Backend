package team.squad.Model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author William Mattern
 * @author John A. Squier
 *
 * Date Created: 3/7/17.
 *
 * This class takes information about a move and does all the necessary checking to determine if the move is valid,
 * and who should go next after the given move. This class also generates random computer moves (for now) when
 * requested using the generateNewBoardStateFromComputerMove() method.
 *
 * TODO refactor some of the methods, those jaunts are a mess
 */
@Component
public class GameManager {

    private Move theMove;
    private CheckersBoard theBoard = new CheckersBoard();

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
     * TODO make sure the player can only move red pieces
     *
     * Need to add logic for handling jumps, a move can be adjacent or possibly a jump move.
     * @return The updated board state as a map.
     */
    public List<Map> generateNewBoardStateFromPlayerMove() {
        if ( isMoveValidAdjacentMove() || isMoveValidJumpMove() ) {
            doMove();
            return BoardState.generateBoardState(theBoard, false);
        }
        else {
            return BoardState.generateBoardState(theBoard, true);
        }
    }

    private boolean isMoveValidJumpMove() {
        if ( startAndFinishAreDiagonallyOneSquareApart() && thereIsAnOpponentPieceInTheMiddle() ) {

        }
        return false;
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
        if ( cellsInMoveAreAdjacent() && requestedCellIsEmpty() && requestCellHasPieceInIt() ) {
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

    private boolean requestCellHasPieceInIt() {
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

    /**
     * Call this when you get a computer move GET request.
     * @return The updated board state after the computer has made its BOGO move.
     * Jumps are not incorporated at this point
     */
    public List<Map> generateNewBoardStateFromComputerMove() {
        Cell cellToMovePieceFrom;
        Cell cellToMovePieceTo = null;
        do {
            cellToMovePieceFrom = pickRandomCellWithBlackPieceInIt();
            cellToMovePieceTo = generateMoveIfAvailable(cellToMovePieceFrom);
        } while ( cellToMovePieceTo == null );

        Move computerMove = new Move();
        computerMove.setFirstCoordinate(cellToMovePieceFrom.getCellName());
        computerMove.setSecondCoordinate(cellToMovePieceTo.getCellName());

        System.out.println("piece start cell = " + computerMove.getFirstCoordinate());//////////////////////////////////////////////////////////////////////
        System.out.println("piece end cell = " + computerMove.getSecondCoordinate());//////////////////////////////////////////////////////////////////////

        this.setTheMove(computerMove);
        doMove();

        return BoardState.generateBoardState(theBoard, true);
    }

    Cell pickRandomCellWithBlackPieceInIt() {
        List<Cell> allTheCellsWithBlackPieces = new ArrayList<>();

        for ( int i = 0; i < 7; i++ ) {
            for ( int j = 0; j < 7; j++ ) {
                Cell current = theBoard.getCell(i, j);
                if ( current.getHasPiece() && current.getPiece().getPieceColor().equals(Color.BLACK) ) {
                    allTheCellsWithBlackPieces.add(current);
                }
            }
        }
        int randomIndex = (int)(Math.random() * allTheCellsWithBlackPieces.size());
        Cell randomlyPicked = allTheCellsWithBlackPieces.get(randomIndex);
        return randomlyPicked;
    }

    // TODO this is a mess, refactor also add logic for king pieces
    Cell generateMoveIfAvailable(Cell cellToMovePieceFrom) {
        // computer is black pieces which are on top of the board
        if ( cellToMovePieceFrom.getPiece().getKing() ) {
            // check all four available locations
            System.out.println("gotta work out logic for king moves");//////////////////////////////////////////////////////////////////////
            return null;
        }
        else {
            int xForDownAndLeft =  cellToMovePieceFrom.getxPosition() - 1; // left
            int yForDownAndLeft =  cellToMovePieceFrom.getyPosition() - 1; // down
            Cell downAndLeft = null;
            if ( cellToMovePieceFrom.getxPosition() > 0 ) {
                downAndLeft = theBoard.getCell(xForDownAndLeft, yForDownAndLeft);
            }

            int xforDownAndRight = cellToMovePieceFrom.getxPosition() + 1; // right
            int yforDownAndRight = cellToMovePieceFrom.getyPosition() - 1; // down
            Cell downAndRight = null;
            if ( cellToMovePieceFrom.getxPosition() < 7 ) {
                 downAndRight = theBoard.getCell(xforDownAndRight, yforDownAndRight);
            }

            if ( ((downAndLeft != null) && downAndLeft.getHasPiece().equals(false))
                    && ((downAndRight != null) && downAndRight.getHasPiece().equals(false)) ) {
                if ( (int)(Math.random() * 10) < 5 ) {
                    return downAndLeft;
                }
                else {
                    return downAndRight;
                }
            }
            else if ( downAndLeft != null && downAndLeft.getHasPiece().equals(false) ) {
                return downAndLeft;
            }
            else if ( downAndRight != null && downAndRight.getHasPiece().equals(false) ) {
                return downAndRight;
            }
            else {
                return null;
            }
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
