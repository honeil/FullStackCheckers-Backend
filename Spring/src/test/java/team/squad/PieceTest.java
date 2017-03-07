package team.squad;

import org.junit.Before;
import org.junit.Test;
import team.squad.Model.Cell;
import team.squad.Model.Color;
import team.squad.Model.Piece;

import static org.junit.Assert.*;

/**
 * @author William Matter
 * @author John A. Squier
 *
 * Date Created: 3/6/17.
 */
public class PieceTest {

    Piece redPiece, blackPiece;
    Cell testCell00, testCell20;
    Cell testCell77;

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

    // need lame tests for getters and setters maybe
}