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

        for ( int i = 0; i < 8; i++ ) {
            for ( int j = 0; j < 8; j++ ) {
                Cell current = theBoard.getCell(i, j);

                if ( current.getHasPiece() ) {
                    if ( current.getPiece().getKing() ) {
                        boardState.put(convertIJIntoCellName(j, i),
                                        current.getPiece().getPieceColor().equals(Color.BLACK) ?
                                                CellState.BLACK_KING_PIECE : CellState.RED_KING_PIECE);
                    }
                    else {
                        boardState.put(convertIJIntoCellName(j, i),
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

    private static String convertIJIntoCellName(int i, int j) {
        String result = convertIToColumnLetter(i) + convertJToRowNumber(j);
        return result;
    }

    private static String convertIToColumnLetter(int i) {
        switch( i ) {
            case 0: return "A";
            case 1: return "B";
            case 2: return "C";
            case 3: return "D";
            case 4: return "E";
            case 5: return "F";
            case 6: return "G";
            case 7: return "H";
            default: return "ERROR";
        }
    }

    private static String convertJToRowNumber(int j) {
        switch ( j ) {
            case 0: return "1";
            case 1: return "2";
            case 2: return "3";
            case 3: return "4";
            case 4: return "5";
            case 5: return "6";
            case 6: return "7";
            case 7: return "8";
            default: return "ERROR";
        }
    }
}
