package team.squad.Model;

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
public class MoveHandler {

    private Move theMove;
    private CheckersBoard theBoard = new CheckersBoard();

    public MoveHandler() { }

    /**
     * Use this to set the move as given from the front end.
     * @param theMove Object representing the desired move.
     */
    public void setTheMove(Move theMove) {
        this.theMove = theMove;
    }

    /**
     * Call this when you get a player move POST request.
     * Checks if the given player move is a valid move and if so alters the board state. If the move is not valid
     * then an unchanged board state is returned.
     *
     * Need to add logic for handling jumps, a move can be adjacent or possibly a jump move.
     * @return The updated board state as a map.
     */
    public List<Map> generateNewBoardStateFromPlayerMove() {
        if ( isMoveValidAdjacentMove() ) {
            doMove();
            return BoardState.generateBoardState(theBoard, false);
        }
        else {
            return BoardState.generateBoardState(theBoard, true);
        }
    }

    public boolean isMoveValidAdjacentMove() {
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
     */
    public List<Map> generateNewBoardStateFromComputerMove() {
        Cell cellToMovePieceFrom =  pickRandomCellWithBlackPieceInIt();
        

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

    /**
     * Call this when you want the initial board state, this always returns the state of the board at the very beginning.
     * @return
     */
    public List<Map> generateInitialBoardState() {
        return BoardState.getInitialBoardState();
    }
}
