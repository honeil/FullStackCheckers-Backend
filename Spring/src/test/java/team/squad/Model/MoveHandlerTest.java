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

    Move validMove;
    CheckersBoard theBoard;

    @Before
    public void setup() {
        theBoard = new CheckersBoard();
        validMove = new Move();
        validMove.setxPositionInitial(0);
        validMove.setyPositionInitial(2); // aka A3
        validMove.setxPositionDesired(1);
        validMove.setyPositionDesired(3); // aka B4
    }

    @Test
    public void cellsInMoveAreAdjacentTest() {
        boolean expected = true;

        boolean actual = MoveHandler.cellsInMoveAreAdjacent(validMove, theBoard);

        assertEquals(expected, actual);
    }

    @Test
    public void isPlayerMoveValidTestOne() {

    }
}
