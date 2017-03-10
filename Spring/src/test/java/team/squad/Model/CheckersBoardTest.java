package team.squad.Model;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * @author William Mattern
 * @author John A. Squier
 *
 * Date Created: 3/6/17.
 *
 * TODO remove repeat code, refactor it
 */
public class CheckersBoardTest {

    CheckersBoard checkersBoard;

    @Before
    public void setUp() throws Exception {
        checkersBoard = new CheckersBoard();
    }

    @Test
    public void areTheRedPiecesInTheCorrectCellsWhenBoardIsConstructedTest() {
        boolean[] expectedHasPiece = new boolean[12];
        Arrays.fill(expectedHasPiece, true);
        Color[] expectedColors = new Color[12];
        Arrays.fill(expectedColors, Color.RED);
        String[] expectedNames = {"A1", "C1", "E1", "G1", "B2", "D2", "F2", "H2", "A3", "C3", "E3", "G3"};

        boolean[] actualHasPiece = new boolean[12];
        Color[] actualColors = new Color[12];
        String[] actualNames = new String[12];
        for ( int i = 0, column = 0, row = 0; i < 12; i++, column+=2 ) {
            // iterate properly through row and column nums
            if ( (i % 4 == 0) && (i != 0) ) {
                row++;
                if ( (row % 2) == 0 ) {
                    column = 0;
                } else {
                    column = 1;
                }
            }
            actualHasPiece[i] = checkersBoard.getCell(column, row).getHasPiece();
            actualColors[i] = checkersBoard.getCell(column, row).getPiece().getPieceColor();
            actualNames[i] = checkersBoard.getCell(column, row).getCellName();
        }

        assertArrayEquals(expectedHasPiece, actualHasPiece);
        assertArrayEquals(expectedColors, actualColors);
        assertArrayEquals(expectedNames, actualNames);
    }

    @Test
    public void areTheBlackPiecesInTheCorrectCellsWhenBoardIsConstructedTest() {
        boolean[] expectedHasPiece = new boolean[12];
        Arrays.fill(expectedHasPiece, true);
        Color[] expectedColors = new Color[12];
        Arrays.fill(expectedColors, Color.BLACK);
        String[] expectedNames = {"B6", "D6", "F6", "H6", "A7", "C7", "E7", "G7", "B8", "D8", "F8", "H8"};

        boolean[] actualHasPiece = new boolean[12];
        Color[] actualColors = new Color[12];
        String[] actualNames = new String[12];
        for ( int i = 0, column = 1, row = 5; i < 12; i++, column+=2 ) {
            // iterate properly through row and column nums
            if ( (i % 4 == 0) && (i != 0) ) {
                row++;
                if ( (row % 2) == 0 ) {
                    column = 0;
                } else {
                    column = 1;
                }
            }
            actualHasPiece[i] = checkersBoard.getCell(column, row).getHasPiece();
            actualColors[i] = checkersBoard.getCell(column, row).getPiece().getPieceColor();
            actualNames[i] = checkersBoard.getCell(column, row).getCellName();
        }

        assertArrayEquals(expectedHasPiece, actualHasPiece);
        assertArrayEquals(expectedColors, actualColors);
        assertArrayEquals(expectedNames, actualNames);
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
            actual[i] = checkersBoard.getCell(column, row).getHasPiece();
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
            actual[i] = checkersBoard.getCell(column, row).getHasPiece();
        }
    }
}