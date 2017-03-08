package team.squad.Model;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author William Mattern
 * @author John A. Squier
 *
 * Date Created: 3/8/17.
 */
public class MoveHandlerTest {

    MoveHandler moveHandler;
    Move validMove, invalidMoveCellsNotAdjacent, redBackwardsMove, blackBackwardsMove;
    CheckersBoard theBoard;

    @Before
    public void setup() {
        moveHandler = new MoveHandler();
        theBoard = new CheckersBoard();

        validMove = new Move();
        validMove.setInitialCell("A3");
        validMove.setDesiredCell("B4");

        invalidMoveCellsNotAdjacent = new Move();
        invalidMoveCellsNotAdjacent.setInitialCell("A1");
        invalidMoveCellsNotAdjacent.setDesiredCell("H8");

        redBackwardsMove = new Move();
        redBackwardsMove.setInitialCell("A3");
        redBackwardsMove.setDesiredCell("B2");

        blackBackwardsMove = new Move();
        blackBackwardsMove.setInitialCell("B6");
        blackBackwardsMove.setDesiredCell("A7");
    }

    @Test
    public void cellsInMoveAreAdjacentTest() {
        moveHandler.setTheMove(validMove);
        boolean expected = true;

        boolean actual = moveHandler.cellsInMoveAreAdjacent();

        assertEquals(expected, actual);
    }

    @Test
    public void cellsInMoveAreNotAdjacentTest() {
        moveHandler.setTheMove(invalidMoveCellsNotAdjacent);
        boolean expected = false;

        boolean actual = moveHandler.cellsInMoveAreAdjacent();

        assertEquals(expected, actual);
    }

    @Test
    public void requestedCellIsEmptyTest() {
        moveHandler.setTheMove(validMove);
        boolean expected = true;

        boolean actual = moveHandler.requestedCellIsEmpty();

        assertEquals(expected, actual);
    }

    @Test
    public void requestedCelIsNotEmptyTest() {
        moveHandler.setTheMove(invalidMoveCellsNotAdjacent);
        boolean expected = false;

        boolean actual = moveHandler.requestedCellIsEmpty();

        assertEquals(expected, actual);
    }

    @Test
    public void pieceIsMovingForwardTest() {
        moveHandler.setTheMove(validMove);
        boolean expected = true;

        boolean actual = moveHandler.movingForward();

        assertEquals(expected, actual);
    }

    @Test
    public void redPieceIsNotMovingForwardTest() {
        moveHandler.setTheMove(redBackwardsMove);
        boolean expected = false;

        boolean actual = moveHandler.movingForward();

        assertEquals(expected, actual);
    }

    @Test
    public void blackPieceIsNotMovingForwardTest() {
        moveHandler.setTheMove(blackBackwardsMove);
        boolean expected = false;

        boolean actual = moveHandler.movingForward();

        assertEquals(expected, actual);
    }

    @Test
    public void pieceIsNotAKingTest() {
        moveHandler.setTheMove(validMove);
        boolean expected = false;

        boolean actual = moveHandler.pieceIsAKing();

        assertEquals(expected, actual);
    }

    @Test
    public void playerMoveIsValidTest() {
        moveHandler.setTheMove(validMove);
        boolean expected = true;

        boolean actual = moveHandler.isPlayerMoveValid();

        assertEquals(expected, actual);
    }

    @Test
    public void playerMoveIsNotValidTest() {
        moveHandler.setTheMove(invalidMoveCellsNotAdjacent);
        boolean expected = false;

        boolean actual = moveHandler.isPlayerMoveValid();

        assertEquals(expected, actual);
    }

    @Test
    public void playerMoveIsNotValidMoveIsBackwardsTest() {
        moveHandler.setTheMove(redBackwardsMove);
        boolean expected = false;

        boolean actual = moveHandler.isPlayerMoveValid();

        assertEquals(expected, actual);
    }

    @Test
    public void doMoveTest() {
        Map initialBoard = BoardState.getInitialBoardState();
        Map<String, CellState> expected = new HashMap<>();
        expected.put("A1", CellState.RED_PIECE);
        expected.put("C1", CellState.RED_PIECE);
        expected.put("E1", CellState.RED_PIECE);
        expected.put("G1", CellState.RED_PIECE);
        expected.put("B2", CellState.RED_PIECE);
        expected.put("D2", CellState.RED_PIECE);
        expected.put("F2", CellState.RED_PIECE);
        expected.put("H2", CellState.RED_PIECE);
        expected.put("B4", CellState.RED_PIECE);
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
        moveHandler.setTheMove(validMove);

        Map actual = moveHandler.generateNewBoardStateFromPlayerMove();

        assertTrue(expected.equals(actual));
    }
}
