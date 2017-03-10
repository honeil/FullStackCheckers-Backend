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
public class PieceTest {

    private Piece redPiece, blackPiece;
    private Cell testCell00, testCell20;
    private Cell testCell77;

    @Before
    public void setUp() throws Exception {
        blackPiece = new Piece(Color.BLACK);
        redPiece = new Piece(Color.RED);
        testCell00 = new Cell(0, 0);
        testCell20 = new Cell(2, 0);
        testCell77 = new Cell(7, 7);
    }

    @Test
    public void isPositionSetWhenPieceEntersCell() {
        testCell00.setPiece(blackPiece);
        assertTrue(blackPiece.getXPosition() == 0);
        assertTrue(blackPiece.getYPosition() == 0);
    }

    @Test
    public void doesPieceUpdatePositionWhenMovedTest() {
        testCell00.setPiece(blackPiece);
        testCell00.removePiece();
        testCell20.setPiece(blackPiece);
        assertTrue(blackPiece.getXPosition() == 2);
        assertTrue(blackPiece.getYPosition() == 0);
    }

    @Test
    public void setPieceColorBlackToRedTest() {
        Color expected = Color.RED;

        blackPiece.setPieceColor(Color.RED);
        Color actual = blackPiece.getPieceColor();

        assertEquals(expected, actual);
    }

    @Test
    public void setAndGetKingStatusTest() {
        boolean expectedInitial = false;
        boolean expectedFinal = true;

        boolean actualInitial = blackPiece.getKing();
        blackPiece.setKing(true);
        boolean actualFinal = blackPiece.getKing();

        assertEquals(expectedInitial, actualInitial);
        assertEquals(expectedFinal, actualFinal);
    }
}
