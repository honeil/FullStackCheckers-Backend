package team.squad.Model;

import java.util.HashMap;
import java.util.Map;

/**
 * @author William Mattern
 * @author John A. Squier
 *
 * Date Created: 3/7/17.
 */
public class BoardState {

    /**
     * Takes a CheckersBoard object and coverts the 2D array representation of the board into a Map that
     *  represents the board's state.
     * @param theBoard a CheckersBoard object with values in its 2D array.
     * @return a map that represents to CheckersBoard by indicating the location of each piece.
     */
    // TODO this has way too many indentations and needs refactored
    public static Map generateBoardState(CheckersBoard theBoard) {
        Map<String, CellState> boardState = new HashMap<String, CellState>();

        for ( int i = 0; i < 8; i++ ) { // this is rows?
            for ( int j = 0; j < 8; j++ ) { // this is columns?
                Cell current = theBoard.getCell(i, j);

                if ( current.getHasPiece() ) {
                    if ( current.getPiece().getKing() ) {
                        boardState.put(current.getCellName(),
                                        current.getPiece().getPieceColor().equals(Color.BLACK) ?
                                                CellState.BLACK_KING_PIECE : CellState.RED_KING_PIECE);
                    }
                    else {
                        boardState.put(current.getCellName(),
                                        current.getPiece().getPieceColor().equals(Color.BLACK) ?
                                                CellState.BLACK_PIECE : CellState.RED_PIECE);
                    }
                }
            }
        }
        return boardState;
    }

    /**
     * Generates a map that represents the cell positions of all the piece at the start of an 8 x 8 checkers game.
     * @return A map representing the starting positions of the 24 pieces in an 8 by 8 checkers game.
     */
    public static Map getInitialBoardState() {
        generateBoardState(new CheckersBoard());
        Map<String, CellState> boardState = new HashMap<String, CellState>();
        return boardState;
    }
}
