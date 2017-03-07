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
}
