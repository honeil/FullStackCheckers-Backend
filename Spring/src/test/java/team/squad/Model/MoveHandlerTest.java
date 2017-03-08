package team.squad.Model;

import org.junit.Before;
import org.junit.Test;
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
}
