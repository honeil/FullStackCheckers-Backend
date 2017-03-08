package team.squad.Model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        validMove.setFirstCoordinate("A3");
        validMove.setSecondCoordinate("B4");

        invalidMoveCellsNotAdjacent = new Move();
        invalidMoveCellsNotAdjacent.setFirstCoordinate("A1");
        invalidMoveCellsNotAdjacent.setSecondCoordinate("H8");

        redBackwardsMove = new Move();
        redBackwardsMove.setFirstCoordinate("A3");
        redBackwardsMove.setSecondCoordinate("B2");

        blackBackwardsMove = new Move();
        blackBackwardsMove.setFirstCoordinate("B6");
        blackBackwardsMove.setSecondCoordinate("A7");
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

        boolean actual = moveHandler.isMoveValidAdjacentMove();

        assertEquals(expected, actual);
    }

    @Test
    public void playerMoveIsNotValidTest() {
        moveHandler.setTheMove(invalidMoveCellsNotAdjacent);
        boolean expected = false;

        boolean actual = moveHandler.isMoveValidAdjacentMove();

        assertEquals(expected, actual);
    }

    @Test
    public void playerMoveIsNotValidMoveIsBackwardsTest() {
        moveHandler.setTheMove(redBackwardsMove);
        boolean expected = false;

        boolean actual = moveHandler.isMoveValidAdjacentMove();

        assertEquals(expected, actual);
    }

    @Test
    public void doMoveTest() {
        List<Map> expected = new ArrayList<>();
        Map<String, Boolean> expectedTurnInfo = new HashMap<>();
        expectedTurnInfo.put("isPlayerMove", false);
        Map<String, CellState> expectedCellInfo = new HashMap<>();
        expectedCellInfo.put("A1", CellState.RED_PIECE);
        expectedCellInfo.put("C1", CellState.RED_PIECE);
        expectedCellInfo.put("E1", CellState.RED_PIECE);
        expectedCellInfo.put("G1", CellState.RED_PIECE);
        expectedCellInfo.put("B2", CellState.RED_PIECE);
        expectedCellInfo.put("D2", CellState.RED_PIECE);
        expectedCellInfo.put("F2", CellState.RED_PIECE);
        expectedCellInfo.put("H2", CellState.RED_PIECE);
        expectedCellInfo.put("B4", CellState.RED_PIECE);
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
        expected.add(expectedTurnInfo);
        expected.add(expectedCellInfo);
        moveHandler.setTheMove(validMove);

        List<Map> actual = moveHandler.generateNewBoardStateFromPlayerMove();

        assertTrue(expected.equals(actual));
    }

    @Test
    public void doesRandomlyPickedCellHaveABlackPieceInIt() {
        boolean expected = true;
        Color expectedColor = Color.BLACK;

        Cell theCell = moveHandler.pickRandomCellWithBlackPieceInIt();
        boolean actual = theCell.getHasPiece();
        Color actualColor = theCell.getPiece().getPieceColor();

        assertEquals(expected, actual);
        assertEquals(expectedColor, actualColor);
    }

    @Test
    public void givenCellDoesHaveAnAvailableMoveTest() {
        Cell toMoveFrom = theBoard.getCell(1, 5);
        Cell expected1 = theBoard.getCell(0, 4);
        Cell expected2 = theBoard.getCell(2, 4);

        Cell actual = moveHandler.generateMoveIfAvailable(toMoveFrom);

        assertTrue(expected1.getCellName().equals(actual.getCellName())
                            || expected2.getCellName().equals(actual.getCellName()));
    }

    @Test
    public void givenCellH6IsCellG5SelectedAsAnAvailableMoveTest() {
        Cell toMoveFrom = theBoard.getCell(7, 5);
        Cell expected = theBoard.getCell(6, 4);

        Cell actual = moveHandler.generateMoveIfAvailable(toMoveFrom);

        assertEquals(expected.getCellName(), actual.getCellName());
    }

    @Test
    public void generateRandomComputerMoveTest() {
        List<Map> intialBoardState = BoardState.getInitialBoardState();

        List<Map> boardStateAfterMove = moveHandler.generateNewBoardStateFromComputerMove();

        assertNotEquals(intialBoardState, boardStateAfterMove);
    }
}
