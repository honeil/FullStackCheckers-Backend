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

    public static Map generateBoardState(CheckersBoard theBoard) {
        // fill in this map using the board
        Map<String, CellState> boardState = new HashMap<String, CellState>();

        boardState.put("A1", CellState.BLACK_PIECE);
        boardState.put("A2", CellState.EMPTY);

        return boardState;
    }

    /**
     * Generates a map that represents the cell positions of all the piece at the start of an 8 x 8 checkers game.
     * @return A map representing the starting positions of the 24 pieces in an 8 by 8 checkers game.
     */
    public static Map getInitialBoardState() {
        Map<String, CellState> boardState = new HashMap<String, CellState>();

        boardState.put("A1", CellState.RED_PIECE);
        boardState.put("C1", CellState.RED_PIECE);
        boardState.put("E1", CellState.RED_PIECE);
        boardState.put("G1", CellState.RED_PIECE);
        boardState.put("B2", CellState.RED_PIECE);
        boardState.put("D2", CellState.RED_PIECE);
        boardState.put("F2", CellState.RED_PIECE);
        boardState.put("H2", CellState.RED_PIECE);
        boardState.put("A3", CellState.RED_PIECE);
        boardState.put("C3", CellState.RED_PIECE);
        boardState.put("E3", CellState.RED_PIECE);
        boardState.put("G3", CellState.RED_PIECE);

        boardState.put("B6", CellState.BLACK_PIECE);
        boardState.put("D6", CellState.BLACK_PIECE);
        boardState.put("F6", CellState.BLACK_PIECE);
        boardState.put("H6", CellState.BLACK_PIECE);
        boardState.put("A7", CellState.BLACK_PIECE);
        boardState.put("C7", CellState.BLACK_PIECE);
        boardState.put("E7", CellState.BLACK_PIECE);
        boardState.put("G7", CellState.BLACK_PIECE);
        boardState.put("B8", CellState.BLACK_PIECE);
        boardState.put("D8", CellState.BLACK_PIECE);
        boardState.put("F8", CellState.BLACK_PIECE);
        boardState.put("H8", CellState.BLACK_PIECE);

        return boardState;
    }
}
