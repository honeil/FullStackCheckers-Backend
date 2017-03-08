package team.squad.Model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author William Mattern
 * @author John A. Squier
 *
 * Date Created: 3/6/17.
 */
public class CellTest {

    private CheckersBoard checkersBoard;
    private Cell testCell;
    private Piece testPiece;

    @Before
    public void setUp() throws Exception {
        checkersBoard = new CheckersBoard();
        testCell = new Cell(0, 0);
        testPiece = new Piece(Color.BLACK);
    }

    @Test
    public void checkCellColorsBlackTest() {
        assertTrue(checkersBoard.getCell(0, 0).getCellColor().equals(Color.BLACK));
        assertTrue(checkersBoard.getCell(0, 2).getCellColor().equals(Color.BLACK));
        assertTrue(checkersBoard.getCell(0, 4).getCellColor().equals(Color.BLACK));
        assertTrue(checkersBoard.getCell(0, 6).getCellColor().equals(Color.BLACK));

        assertTrue(checkersBoard.getCell(1, 1).getCellColor().equals(Color.BLACK));
        assertTrue(checkersBoard.getCell(1, 3).getCellColor().equals(Color.BLACK));
        assertTrue(checkersBoard.getCell(1, 5).getCellColor().equals(Color.BLACK));
        assertTrue(checkersBoard.getCell(1, 7).getCellColor().equals(Color.BLACK));

        assertTrue(checkersBoard.getCell(2, 0).getCellColor().equals(Color.BLACK));
        assertTrue(checkersBoard.getCell(2, 2).getCellColor().equals(Color.BLACK));
        assertTrue(checkersBoard.getCell(2, 4).getCellColor().equals(Color.BLACK));
        assertTrue(checkersBoard.getCell(2, 6).getCellColor().equals(Color.BLACK));

        assertTrue(checkersBoard.getCell(3, 1).getCellColor().equals(Color.BLACK));
        assertTrue(checkersBoard.getCell(3, 3).getCellColor().equals(Color.BLACK));
        assertTrue(checkersBoard.getCell(3, 5).getCellColor().equals(Color.BLACK));
        assertTrue(checkersBoard.getCell(3, 7).getCellColor().equals(Color.BLACK));

        assertTrue(checkersBoard.getCell(4, 0).getCellColor().equals(Color.BLACK));
        assertTrue(checkersBoard.getCell(4, 2).getCellColor().equals(Color.BLACK));
        assertTrue(checkersBoard.getCell(4, 4).getCellColor().equals(Color.BLACK));
        assertTrue(checkersBoard.getCell(4, 6).getCellColor().equals(Color.BLACK));

        assertTrue(checkersBoard.getCell(5, 1).getCellColor().equals(Color.BLACK));
        assertTrue(checkersBoard.getCell(5, 3).getCellColor().equals(Color.BLACK));
        assertTrue(checkersBoard.getCell(5, 5).getCellColor().equals(Color.BLACK));
        assertTrue(checkersBoard.getCell(5, 7).getCellColor().equals(Color.BLACK));

        assertTrue(checkersBoard.getCell(6, 0).getCellColor().equals(Color.BLACK));
        assertTrue(checkersBoard.getCell(6, 2).getCellColor().equals(Color.BLACK));
        assertTrue(checkersBoard.getCell(6, 4).getCellColor().equals(Color.BLACK));
        assertTrue(checkersBoard.getCell(6, 6).getCellColor().equals(Color.BLACK));

        assertTrue(checkersBoard.getCell(7, 1).getCellColor().equals(Color.BLACK));
        assertTrue(checkersBoard.getCell(7, 3).getCellColor().equals(Color.BLACK));
        assertTrue(checkersBoard.getCell(7, 5).getCellColor().equals(Color.BLACK));
        assertTrue(checkersBoard.getCell(7, 7).getCellColor().equals(Color.BLACK));
    }

    @Test
    public void checkCellColorsRedTest() {
        assertTrue(checkersBoard.getCell(0, 1).getCellColor().equals(Color.RED));
        assertTrue(checkersBoard.getCell(0, 3).getCellColor().equals(Color.RED));
        assertTrue(checkersBoard.getCell(0, 5).getCellColor().equals(Color.RED));
        assertTrue(checkersBoard.getCell(0, 7).getCellColor().equals(Color.RED));

        assertTrue(checkersBoard.getCell(1, 0).getCellColor().equals(Color.RED));
        assertTrue(checkersBoard.getCell(1, 2).getCellColor().equals(Color.RED));
        assertTrue(checkersBoard.getCell(1, 4).getCellColor().equals(Color.RED));
        assertTrue(checkersBoard.getCell(1, 6).getCellColor().equals(Color.RED));

        assertTrue(checkersBoard.getCell(2, 1).getCellColor().equals(Color.RED));
        assertTrue(checkersBoard.getCell(2, 3).getCellColor().equals(Color.RED));
        assertTrue(checkersBoard.getCell(2, 5).getCellColor().equals(Color.RED));
        assertTrue(checkersBoard.getCell(2, 7).getCellColor().equals(Color.RED));

        assertTrue(checkersBoard.getCell(3, 0).getCellColor().equals(Color.RED));
        assertTrue(checkersBoard.getCell(3, 2).getCellColor().equals(Color.RED));
        assertTrue(checkersBoard.getCell(3, 4).getCellColor().equals(Color.RED));
        assertTrue(checkersBoard.getCell(3, 6).getCellColor().equals(Color.RED));

        assertTrue(checkersBoard.getCell(4, 1).getCellColor().equals(Color.RED));
        assertTrue(checkersBoard.getCell(4, 3).getCellColor().equals(Color.RED));
        assertTrue(checkersBoard.getCell(4, 5).getCellColor().equals(Color.RED));
        assertTrue(checkersBoard.getCell(4, 7).getCellColor().equals(Color.RED));

        assertTrue(checkersBoard.getCell(5, 0).getCellColor().equals(Color.RED));
        assertTrue(checkersBoard.getCell(5, 2).getCellColor().equals(Color.RED));
        assertTrue(checkersBoard.getCell(5, 4).getCellColor().equals(Color.RED));
        assertTrue(checkersBoard.getCell(5, 6).getCellColor().equals(Color.RED));

        assertTrue(checkersBoard.getCell(6, 1).getCellColor().equals(Color.RED));
        assertTrue(checkersBoard.getCell(6, 3).getCellColor().equals(Color.RED));
        assertTrue(checkersBoard.getCell(6, 5).getCellColor().equals(Color.RED));
        assertTrue(checkersBoard.getCell(6, 7).getCellColor().equals(Color.RED));

        assertTrue(checkersBoard.getCell(7, 0).getCellColor().equals(Color.RED));
        assertTrue(checkersBoard.getCell(7, 2).getCellColor().equals(Color.RED));
        assertTrue(checkersBoard.getCell(7, 4).getCellColor().equals(Color.RED));
        assertTrue(checkersBoard.getCell(7, 6).getCellColor().equals(Color.RED));
    }

    @Test
    public void doesNewlyConstructedCellHaveAPieceTest() {
        boolean expected = false;

        boolean actual = testCell.getHasPiece();

        assertEquals(expected, actual);
    }

    @Test
    public void getPieceFromCellTest() {
        testCell.setPiece(testPiece);
        Piece expected = testPiece;

        Piece actual = testCell.getPiece();

        assertEquals(expected, actual);
    }

    @Test
    public void removePieceFromCellAndCheckCellStateTest() {
        testCell.setPiece(testPiece);
        Piece expectedPiece = null;
        boolean expected = false;

        testCell.removePiece();
        Piece actualPiece = testCell.getPiece();
        boolean actual = testCell.getHasPiece();

        assertEquals(expectedPiece, actualPiece);
        assertEquals(expected, actual);
    }

    @Test
    public void isCell01CalledA2Test() {
        Cell a2 = new Cell(0, 1);
        String expected = "A2";

        String actual = a2.getCellName();

        assertEquals(expected, actual);
    }

    @Test
    public void isCell10CalledB1Test() {
        Cell b1 = new Cell(1, 0);
        String expected = "B1";

        String actual = b1.getCellName();

        assertEquals(expected, actual);
    }
}