package team.squad.checkers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by williammattern on 3/6/17.
 * @author John A. Squier
 */
public class CheckersBoardTest {

    CheckersBoard checkersBoard;

    @Before
    public void setUp() throws Exception {
        checkersBoard = new CheckersBoard();
    }

    @Test
    public void arePiecesInCorrectPlacesTest() {
        assertTrue(checkersBoard.getCell(0, 0).getHasPiece());
        assertTrue(checkersBoard.getCell(0,0).getChecker().getPieceColor().equals(Color.RED));
        assertTrue(checkersBoard.getCell(2, 6).getHasPiece());
        assertTrue(checkersBoard.getCell(2,6).getChecker().getPieceColor().equals(Color.RED));
        assertTrue(checkersBoard.getCell(5, 1).getHasPiece());
        assertTrue(checkersBoard.getCell(5,1).getChecker().getPieceColor().equals(Color.BLACK));
        assertTrue(checkersBoard.getCell(7, 7).getHasPiece());
        assertTrue(checkersBoard.getCell(7,7).getChecker().getPieceColor().equals(Color.BLACK));
    }
}