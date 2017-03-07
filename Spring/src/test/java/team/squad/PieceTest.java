package team.squad;

import org.junit.Before;
import org.junit.Test;
import team.squad.Model.Cell;
import team.squad.Model.Color;
import team.squad.Model.Piece;

import static org.junit.Assert.assertTrue;

/**
 * Created by williammattern on 3/6/17.
 * @author John A. Squier
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
        testCell00.setChecker(blackPiece);
        assertTrue(blackPiece.getXPosition() == 0);
        assertTrue(blackPiece.getYPosition() == 0);
    }

    @Test
    public void doesPieceUpdatePositionWhenMovedTest() {
        testCell00.setChecker(blackPiece);
        testCell00.removeChecker();
        testCell20.setChecker(blackPiece);
        assertTrue(blackPiece.getXPosition() == 2);
        assertTrue(blackPiece.getYPosition() == 0);
    }

    // need lame tests for getters and setters maybe
}