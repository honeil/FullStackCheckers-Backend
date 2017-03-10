package team.squad.Model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * @author William Mattern
 * @author John A. Squier
 *
 * Date Created: 3/7/17.
 *
 */
public class BoardStateTest {

    Map<String, CellState> expectedCellInfo;
    Map<String, Boolean> expectedTurnInfo;
    List<Map> responseToFrontEnd;

    @Before
    public void setup() {
        expectedTurnInfo = new HashMap<>();
        expectedTurnInfo.put("isPlayerMove", true);

        expectedCellInfo = new HashMap<>();
        expectedCellInfo.put("A1", CellState.RED_PIECE);
        expectedCellInfo.put("C1", CellState.RED_PIECE);
        expectedCellInfo.put("E1", CellState.RED_PIECE);
        expectedCellInfo.put("G1", CellState.RED_PIECE);
        expectedCellInfo.put("B2", CellState.RED_PIECE);
        expectedCellInfo.put("D2", CellState.RED_PIECE);
        expectedCellInfo.put("F2", CellState.RED_PIECE);
        expectedCellInfo.put("H2", CellState.RED_PIECE);
        expectedCellInfo.put("A3", CellState.RED_PIECE);
        expectedCellInfo.put("C3", CellState.RED_PIECE);
        expectedCellInfo.put("E3", CellState.RED_PIECE);
        expectedCellInfo.put("G3", CellState.RED_PIECE);

        expectedCellInfo.put("B6", CellState.BLACK_PIECE);
        expectedCellInfo.put("D6", CellState.BLACK_PIECE);
        expectedCellInfo.put("F6", CellState.BLACK_PIECE);
        expectedCellInfo.put("H6", CellState.BLACK_PIECE);
        expectedCellInfo.put("A7", CellState.BLACK_PIECE);
        expectedCellInfo.put("C7", CellState.BLACK_PIECE);
        expectedCellInfo.put("E7", CellState.BLACK_PIECE);
        expectedCellInfo.put("G7", CellState.BLACK_PIECE);
        expectedCellInfo.put("B8", CellState.BLACK_PIECE);
        expectedCellInfo.put("D8", CellState.BLACK_PIECE);
        expectedCellInfo.put("F8", CellState.BLACK_PIECE);
        expectedCellInfo.put("H8", CellState.BLACK_PIECE);

        responseToFrontEnd = new ArrayList<>();
        responseToFrontEnd.add(expectedTurnInfo);
        responseToFrontEnd.add(expectedCellInfo);
    }

    @Test
    public void generateBoardStateFromInitialBoardTest() {
        List<Map> actual = BoardState.generateBoardState(new CheckersBoard(), true);

        assertTrue(responseToFrontEnd.equals(actual));
    }

    @Test
    public void getInitialBoardStateTest() {
        List<Map> actual = BoardState.getInitialBoardState();

        assertTrue(responseToFrontEnd.equals(actual));
    }
}
