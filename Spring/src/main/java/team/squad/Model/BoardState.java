package team.squad.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author William Mattern
 * @author John A. Squier
 *
 * Date Created: 3/7/17.
 */
public abstract class BoardState {

    /**
     * Takes a CheckersBoard object and coverts the 2D array representation of the board into a Map that
     *  represents the board's state. Then a map representing who's turn is next, the players or the computer's.
     *  Those two maps are put together in a list of maps ready to send off through HTTP
     * @param theBoard a CheckersBoard object with values in its 2D array.
     * @return a List of maps that represents the CheckersBoard by indicating the location of each piece and the turn
     * by indicating if it is the players turn or not.
     */
    // TODO this has way too many indentations and needs refactored
    public static List<Map> generateBoardState(CheckersBoard theBoard, boolean isPlayerMove) {
        Map<String, CellState> boardState = new HashMap<String, CellState>();
        Map<String, Boolean> whosTurnIsIt = new HashMap<String, Boolean>();
        List<Map> response = new ArrayList<>();

        whosTurnIsIt.put("isPlayerMove", isPlayerMove);

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

        response.add(whosTurnIsIt);
        response.add(boardState);
        return response;
    }

    /**
     * Generates a map that represents the cell positions of all the pieces at the start of an 8 x 8 checkers game.
     * @return A list of mapsrepresenting the starting positions of the 24 pieces in an 8 by 8 checkers game and that
     * the players turn is first.
     */
    public static List<Map> getInitialBoardState() {
        List<Map> reponse = new ArrayList<>();
        reponse = generateBoardState(new CheckersBoard(), true);
        return reponse;
    }
}
