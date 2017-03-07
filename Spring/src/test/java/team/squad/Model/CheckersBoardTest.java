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
public class CheckersBoardTest {

    CheckersBoard checkersBoard;

    @Before
    public void setUp() throws Exception {
        checkersBoard = new CheckersBoard();
    }

    @Test // gonna scrap this test
    public void doesCell00ContainARedPieceTest() {
        boolean expected = true;

        boolean actualBoardHasPiece = checkersBoard.getCell(0,0).getHasPiece();
        boolean actualPieceIsRed = checkersBoard.getCell(0,0).getPiece().getPieceColor().equals(Color.RED);

        assertEquals(expected, actualBoardHasPiece);
        assertEquals(expected, actualPieceIsRed);
    }

    @Test // gonna scrap this test
    public void doesCell02ContainARedPieceTest() {
        boolean expected = true;

        boolean actualBoardHasPiece = checkersBoard.getCell(0,2).getHasPiece();
        boolean actualPieceIsRed = checkersBoard.getCell(0,2).getPiece().getPieceColor().equals(Color.RED);

        assertEquals(expected, actualBoardHasPiece);
        assertEquals(expected, actualPieceIsRed);
    }

    @Test
    public void areTheRedPiecesInTheCorrectCellsWhenBoardIsConstructedTest() {

    }

    @Test
    public void areTheBlackPiecesInTheCorrectCellsWhenBoardIsConstructedTest() {

    }

    @Test
    public void areTheCorrectCellsEmptyWhenBoardIsConstructedTest() {
        boolean[] expected = new boolean[40];
        for ( int i = 0; i < expected.length; i++ ) {
            expected[i] = false;
        }

        boolean[] actual = new boolean[40];
        this.determineIfAllTheRedSquaresAreEmpty(actual);
        this.determineIfTheBlackSquaresInTheMiddleTwoRowsAreEmpty(actual);

        assertArrayEquals(expected, actual);
    }

    private void determineIfAllTheRedSquaresAreEmpty(boolean[] actual) {
        for ( int i = 0, row = 0, column = 1; i < 32; i++, column += 2 ) {
            if ( ((i % 4) == 0) && (i != 0) ) {
                row++;
                if ( (row % 2) == 0 ) {
                    column = 1;
                } else {
                    column = 0;
                }
            }
            actual[i] = checkersBoard.getCell(row, column).getHasPiece();
        }
    }

    private void determineIfTheBlackSquaresInTheMiddleTwoRowsAreEmpty(boolean[] actual) {
        for ( int i = 32, row = 3, column = 1; i < 40; i++, column+=2 ) {
            if ( ((i % 4) == 0) && (i != 32) ) {
                row++;
                if ( (row % 2) == 0 ) {
                    column = 0;
                } else {
                    column = 1;
                }
            }
            actual[i] = checkersBoard.getCell(row, column).getHasPiece();
        }
    }
}