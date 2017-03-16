package team.squad.Model;

import org.junit.Before;
import org.junit.Test;
import team.squad.Model.CheckersBoard;
import team.squad.Model.ComputerPlayer;
import team.squad.Model.Move;

import static org.junit.Assert.*;

/**
 * Created by williammattern on 3/14/17.
 */
public class ComputerPlayerTest {
    ComputerPlayer hal; // TODO lol at this
    CheckersBoard testBoard;

    @Before
    public void setUp() throws Exception {
        hal = new ComputerPlayer();
        testBoard = new CheckersBoard();
        testBoard.initializePieces();
        hal.theBoard = testBoard;
        hal.locateAllBlackPieces();
    }

    @Test
    public void generateMoveTest() throws Exception {

    }

    @Test
    public void allCellsTest() throws Exception {
        int expectedSize = 64, actualSize = hal.allCells().size();
        assertEquals(expectedSize,actualSize);
    }

    @Test
    public void trackAllBlackPiecesTest() throws Exception {
        hal.locateAllBlackPieces();
        int expectedSize = 12, actualSize = hal.blackTeamPositions.size();
        assertEquals(expectedSize,actualSize);
    }

    @Test
    public void scanForMovesTest() throws Exception {
        int expectedSize = 4, actualSize = hal.scanForMoves().size();
        assertEquals(expectedSize,actualSize);
        for(Move move:hal.scanForMoves()){
            assertTrue(move.getyPositionInitial() == 5);
            assertTrue(move.getyPositionDesired() == 4);
        }
    }

    @Test
    public void generateMoveIfAvailableTest() throws Exception {

    }
    
//    @Test
//    public void doesRandomlyPickedCellHaveABlackPieceInIt() {
//        boolean expected = true;
//        Color expectedColor = Color.BLACK;
//
//        Cell theCell = gameManager.pickRandomCellWithBlackPieceInIt();
//        boolean actual = theCell.getHasPiece();
//        Color actualColor = theCell.getPiece().getPieceColor();
//
//        assertEquals(expected, actual);
//        assertEquals(expectedColor, actualColor);
//    }

//    @Test
//    public void givenCellDoesHaveAnAvailableMoveTest() {
//        Cell toMoveFrom = theBoard.getCell(1, 5);
//        Cell expected1 = theBoard.getCell(0, 4);
//        Cell expected2 = theBoard.getCell(2, 4);
//
//        Cell actual = gameManager.generateMoveIfAvailable(toMoveFrom);
//
//        assertTrue(expected1.getCellName().equals(actual.getCellName())
//                            || expected2.getCellName().equals(actual.getCellName()));
//    }

//    @Test
//    public void givenCellH6IsCellG5SelectedAsAnAvailableMoveTest() {
//        Cell toMoveFrom = theBoard.getCell(7, 5);
//        Cell expected = theBoard.getCell(6, 4);
//
//        Cell actual = gameManager.generateMoveIfAvailable(toMoveFrom);
//
//        assertEquals(expected.getCellName(), actual.getCellName());
//    }

//    @Test
//    public void generateRandomComputerMoveTest() {
//        List<Map> intialBoardState = BoardState.getInitialBoardState();
//
//        List<Map> boardStateAfterMove = gameManager.generateNewBoardStateFromComputerMove();
//
//        assertNotEquals(intialBoardState, boardStateAfterMove);
//    }
}
