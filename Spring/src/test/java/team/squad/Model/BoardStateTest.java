package team.squad.Model;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
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

    Map<String, CellState> expected;

    @Before
    public void setup() {
        expected = new HashMap<>();
        expected.put("A1", CellState.RED_PIECE);
        expected.put("C1", CellState.RED_PIECE);
        expected.put("E1", CellState.RED_PIECE);
        expected.put("G1", CellState.RED_PIECE);
        expected.put("B2", CellState.RED_PIECE);
        expected.put("D2", CellState.RED_PIECE);
        expected.put("F2", CellState.RED_PIECE);
        expected.put("H2", CellState.RED_PIECE);
        expected.put("A3", CellState.RED_PIECE);
        expected.put("C3", CellState.RED_PIECE);
        expected.put("E3", CellState.RED_PIECE);
        expected.put("G3", CellState.RED_PIECE);

        expected.put("B6", CellState.BLACK_PIECE);
        expected.put("D6", CellState.BLACK_PIECE);
        expected.put("F6", CellState.BLACK_PIECE);
        expected.put("H6", CellState.BLACK_PIECE);
        expected.put("A7", CellState.BLACK_PIECE);
        expected.put("C7", CellState.BLACK_PIECE);
        expected.put("E7", CellState.BLACK_PIECE);
        expected.put("G7", CellState.BLACK_PIECE);
        expected.put("B8", CellState.BLACK_PIECE);
        expected.put("D8", CellState.BLACK_PIECE);
        expected.put("F8", CellState.BLACK_PIECE);
        expected.put("H8", CellState.BLACK_PIECE);
    }

    @Test
    public void generateBoardStateFromInitialBoardTest() {
        Map actual = BoardState.generateBoardState(new CheckersBoard());

        assertTrue(expected.equals(actual));
    }

    @Test
    public void getInitialBoardStateTest() {
<<<<<<< HEAD
<<<<<<< HEAD
        // this test calls the wrong method
        Map actual = BoardState.generateBoardState(new CheckersBoard());
        System.out.println(actual);
=======
        Map actual = BoardState.getInitialBoardState();

>>>>>>> origin/cleanupandtestboard
=======
        Map actual = BoardState.getInitialBoardState();

>>>>>>> fc8c077a24926f87e376d0eca74bf6cbba9a49b4
        assertTrue(expected.equals(actual));
    }
}
