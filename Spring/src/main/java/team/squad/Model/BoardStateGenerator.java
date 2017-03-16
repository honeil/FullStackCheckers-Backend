package team.squad.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author William Mattern
 * @author John A. Squier
 *
 * This class is abstract because all its method's are static, so there is no need to ever instantiate it.
 *
 * Date Created: 3/7/17.
 */
abstract class BoardStateGenerator {

    /**
     * Takes a CheckersBoard object and coverts the 2D array representation of the board into a Map that
     *  represents the board's state. Then a map representing who's turn is next, the players or the computer's.
     *  Lastly a map indicating who has won (RED, BLACK, null).
     *  Those three maps are put together in a list of maps ready to send off through HTTP
     * @param theBoard a CheckersBoard object with values in its 2D array.
     * @return a List of maps that represents the CheckersBoard by indicating the location of each piece and the turn
     * by indicating if it is the players turn or not, and a whether or not anyone has won.
     */
    static List<Map> generateBoardState(CheckersBoard theBoard, boolean isPlayerMove) {
        Map<String, CellState> boardState = new HashMap<String, CellState>();
        Map<String, Boolean> whosTurnIsIt = new HashMap<String, Boolean>();
        Map<String, Color> whatColorHasWon = new HashMap<String, Color>();
        List<Map> response = new ArrayList<>();

        whosTurnIsIt.put("isPlayerMove", isPlayerMove);
        whatColorHasWon.put("whoHasWon", theBoard.whoHasWon());

        for ( int i = 0; i < 8; i++ ) {
            for ( int j = 0; j < 8; j++ ) {
                Cell current = theBoard.getCell(i, j);
                if ( current.getHasPiece() ) {
                    addPieceToMap(current, boardState);
                }
            }
        }
        response.add(whosTurnIsIt);
        response.add(boardState);
        response.add(whatColorHasWon);
        return response;
    }

    private static void addPieceToMap(Cell current, Map<String, CellState> boardState) {
        if ( current.getPiece().getKing() ) {
            addKingPieceToMap(current, boardState);
        }
        else {
            addRegularPieceToMap(current, boardState);
        }
    }

    private static void addKingPieceToMap(Cell current, Map<String, CellState> boardState) {
        boardState.put(current.getCellName(),
                current.getPiece().getPieceColor().equals(Color.BLACK) ?
                        CellState.BLACK_KING_PIECE : CellState.RED_KING_PIECE);
    }

    private static void addRegularPieceToMap(Cell current, Map<String, CellState> boardState) {
        boardState.put(current.getCellName(),
                current.getPiece().getPieceColor().equals(Color.BLACK) ?
                        CellState.BLACK_PIECE : CellState.RED_PIECE);
    }

    /**
     * Generates a map that represents the cell positions of all the pieces at the start of an 8 x 8 checkers game.
     * @return A list of maps representing the starting positions of the 24 pieces in an 8 by 8 checkers game and that
     * the players turn is first.
     */
    static List<Map> getInitialBoardState() {
        List<Map> response = generateBoardState(new CheckersBoard(), true);
        return response;
    }
}
