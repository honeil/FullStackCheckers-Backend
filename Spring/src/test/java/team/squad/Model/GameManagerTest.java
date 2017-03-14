package team.squad.Model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author William Mattern
 * @author John A. Squier
 *
 * Date Created: 3/8/17.
 */
public class GameManagerTest {

    GameManager gameManager;
    Move validMove, invalidMoveCellsNotAdjacent, redBackwardsMove, blackBackwardsMove, redJumpMove, blackJumpMove,
         validAttackDiagonal, invalidAttackDiagonal;
    CheckersBoard theBoard;

    @Before
    public void setup() {
        gameManager = new GameManager();
        theBoard = new CheckersBoard();

        validMove = new Move();
        validMove.setFirstCoordinate("A3");
        validMove.setSecondCoordinate("B4");

        invalidMoveCellsNotAdjacent = new Move();
        invalidMoveCellsNotAdjacent.setFirstCoordinate("A1");
        invalidMoveCellsNotAdjacent.setSecondCoordinate("H8");

        redBackwardsMove = new Move();
        redBackwardsMove.setFirstCoordinate("A3"); // change to B4 to A3, gets a null ptr exception
        redBackwardsMove.setSecondCoordinate("B2"); // TODO find this bug, can pieces move backwards?

        blackBackwardsMove = new Move();
        blackBackwardsMove.setFirstCoordinate("B6");
        blackBackwardsMove.setSecondCoordinate("A7");

        redJumpMove = new Move();
        redJumpMove.setFirstCoordinate("A3");
        redJumpMove.setSecondCoordinate("C5");

        blackJumpMove = new Move();
        blackJumpMove.setFirstCoordinate("H6");
        blackJumpMove.setSecondCoordinate("F4");

        validAttackDiagonal = new Move();
        validAttackDiagonal.setFirstCoordinate("C3");
        validAttackDiagonal.setSecondCoordinate("E5");

        invalidAttackDiagonal = new Move();
        invalidAttackDiagonal.setFirstCoordinate("C3");
        invalidAttackDiagonal.setSecondCoordinate("D5");
    }

    @Test
    public void cellsInMoveAreAdjacentTest() {
        gameManager.setTheMove(validMove);
        boolean expected = true;

        boolean actual = gameManager.cellsInMoveAreAdjacent();

        assertEquals(expected, actual);
    }

    @Test
    public void cellsInMoveAreNotAdjacentTest() {
        gameManager.setTheMove(invalidMoveCellsNotAdjacent);
        boolean expected = false;

        boolean actual = gameManager.cellsInMoveAreAdjacent();

        assertEquals(expected, actual);
    }

    @Test
    public void requestedCellIsEmptyTest() {
        gameManager.setTheMove(validMove);
        boolean expected = true;

        boolean actual = gameManager.requestedCellIsEmpty();

        assertEquals(expected, actual);
    }

    @Test
    public void requestedCelIsNotEmptyTest() {
        gameManager.setTheMove(invalidMoveCellsNotAdjacent);
        boolean expected = false;

        boolean actual = gameManager.requestedCellIsEmpty();

        assertEquals(expected, actual);
    }

    @Test
    public void pieceIsMovingForwardTest() {
        gameManager.setTheMove(validMove);
        boolean expected = true;

        boolean actual = gameManager.movingForward();

        assertEquals(expected, actual);
    }

    @Test
    public void redPieceIsNotMovingForwardTest() {
        gameManager.setTheMove(redBackwardsMove);
        boolean expected = false;

        boolean actual = gameManager.movingForward();

        assertEquals(expected, actual);
    }

    @Test
    public void blackPieceIsNotMovingForwardTest() {
        gameManager.setTheMove(blackBackwardsMove);
        boolean expected = false;

        boolean actual = gameManager.movingForward();

        assertEquals(expected, actual);
    }

    @Test
    public void pieceIsNotAKingTest() {
        gameManager.setTheMove(validMove);
        boolean expected = false;

        boolean actual = gameManager.pieceIsAKing();

        assertEquals(expected, actual);
    }

    @Test
    public void playerMoveIsValidTest() {
        gameManager.setTheMove(validMove);
        boolean expected = true;

        boolean actual = gameManager.isMoveValidAdjacentMove();

        assertEquals(expected, actual);
    }

    @Test
    public void playerMoveIsNotValidTest() {
        gameManager.setTheMove(invalidMoveCellsNotAdjacent);
        boolean expected = false;

        boolean actual = gameManager.isMoveValidAdjacentMove();

        assertEquals(expected, actual);
    }

    @Test
    public void playerMoveIsNotValidMoveIsBackwardsTest() {
        gameManager.setTheMove(redBackwardsMove);
        boolean expected = false;

        boolean actual = gameManager.isMoveValidAdjacentMove();

        assertEquals(expected, actual);
    }

    @Test
    public void doMoveTest() {
        List<Map> expected = new ArrayList<>();
        Map<String, Boolean> expectedTurnInfo = new HashMap<>();
        expectedTurnInfo.put("isPlayerMove", false);
        Map<String, CellState> expectedCellInfo = new HashMap<>();
        expectedCellInfo.put("A1", CellState.RED_PIECE);
        expectedCellInfo.put("C1", CellState.RED_PIECE);
        expectedCellInfo.put("E1", CellState.RED_PIECE);
        expectedCellInfo.put("G1", CellState.RED_PIECE);
        expectedCellInfo.put("B2", CellState.RED_PIECE);
        expectedCellInfo.put("D2", CellState.RED_PIECE);
        expectedCellInfo.put("F2", CellState.RED_PIECE);
        expectedCellInfo.put("H2", CellState.RED_PIECE);
        expectedCellInfo.put("B4", CellState.RED_PIECE);
        expectedCellInfo.put("C3", CellState.RED_PIECE);
        expectedCellInfo.put("E3", CellState.RED_PIECE);
        expectedCellInfo.put("G3", CellState.RED_PIECE);
        expectedCellInfo.put("B6", CellState.BLACK_PIECE);
        expectedCellInfo.put("D6", CellState.BLACK_PIECE);
        expectedCellInfo.put("F6", CellState.BLACK_PIECE);
        expectedCellInfo.put("H6", CellState.BLACK_PIECE);
        expectedCellInfo.put("A7", CellState.BLACK_PIECE);
        expectedCellInfo.put("C7", CellState.BLACK_PIECE);
        expectedCellInfo.put("E7", CellState.BLACK_PIECE);
        expectedCellInfo.put("G7", CellState.BLACK_PIECE);
        expectedCellInfo.put("B8", CellState.BLACK_PIECE);
        expectedCellInfo.put("D8", CellState.BLACK_PIECE);
        expectedCellInfo.put("F8", CellState.BLACK_PIECE);
        expectedCellInfo.put("H8", CellState.BLACK_PIECE);
        expected.add(expectedTurnInfo);
        expected.add(expectedCellInfo);
        gameManager.setTheMove(validMove);

        List<Map> actual = gameManager.generateNewBoardStateFromPlayerMove();

        assertTrue(expected.equals(actual));
    }

    @Test
    public void doesRandomlyPickedCellHaveABlackPieceInIt() {
        boolean expected = true;
        Color expectedColor = Color.BLACK;

        Cell theCell = gameManager.pickRandomCellWithBlackPieceInIt();
        boolean actual = theCell.getHasPiece();
        Color actualColor = theCell.getPiece().getPieceColor();

        assertEquals(expected, actual);
        assertEquals(expectedColor, actualColor);
    }

    @Test
    public void givenCellDoesHaveAnAvailableMoveTest() {
        Cell toMoveFrom = theBoard.getCell(1, 5);
        Cell expected1 = theBoard.getCell(0, 4);
        Cell expected2 = theBoard.getCell(2, 4);

        Cell actual = gameManager.generateMoveIfAvailable(toMoveFrom);

        assertTrue(expected1.getCellName().equals(actual.getCellName())
                            || expected2.getCellName().equals(actual.getCellName()));
    }

    @Test
    public void givenCellH6IsCellG5SelectedAsAnAvailableMoveTest() {
        Cell toMoveFrom = theBoard.getCell(7, 5);
        Cell expected = theBoard.getCell(6, 4);

        Cell actual = gameManager.generateMoveIfAvailable(toMoveFrom);

        assertEquals(expected.getCellName(), actual.getCellName());
    }

    @Test
    public void generateRandomComputerMoveTest() {
        List<Map> intialBoardState = BoardState.getInitialBoardState();

        List<Map> boardStateAfterMove = gameManager.generateNewBoardStateFromComputerMove();

        assertNotEquals(intialBoardState, boardStateAfterMove);
    }

    @Test
    public void thereIsAnOpponentPieceInTheMiddleOfARedJumpTest() {
        gameManager.setTheMove(redJumpMove);
        Cell inTheMiddle = theBoard.getCell(1, 3);
        inTheMiddle.setPiece(new Piece(Color.BLACK));
        gameManager.setTheBoard(theBoard);

        System.out.println(gameManager.thereIsAnOpponentPieceInTheMiddle());
        assertTrue(gameManager.thereIsAnOpponentPieceInTheMiddle());
    }

    @Test
    public void thereIsAnOpponentPieceInTheMiddleOfABlackJumpTest() {
        gameManager.setTheMove(blackJumpMove);
        Cell inTheMiddle = theBoard.getCell(6, 4);
        inTheMiddle.setPiece(new Piece(Color.RED));
        gameManager.setTheBoard(theBoard);

        assertTrue(gameManager.thereIsAnOpponentPieceInTheMiddle());
    }

    @Test
    public void thereIsNoOppnentPieceInTheMiddleTest() {
        gameManager.setTheMove(redJumpMove);

        assertFalse(gameManager.thereIsAnOpponentPieceInTheMiddle());
    }

    public void startAndFinishAreDiagonallyOneSquareApartTest1() {
        gameManager.setTheMove(validAttackDiagonal);
        boolean expected = true;
        boolean actual = gameManager.startAndFinishAreDiagonallyOneSquareApart();
        assertEquals(expected, actual);
    }

    @Test
    public void startAndFinishAreDiagonallyOneSquareApartTest2() {
        gameManager.setTheMove(invalidAttackDiagonal);
        boolean expected = false;
        boolean actual = gameManager.startAndFinishAreDiagonallyOneSquareApart();
        assertEquals(expected, actual);
    }

    @Test
    public void validJumpMoveTest(){
        gameManager.setTheMove(blackJumpMove);
        Cell inTheMiddle = theBoard.getCell(6, 4);
        inTheMiddle.setPiece(new Piece(Color.RED));
        gameManager.setTheBoard(theBoard);
        assertTrue(gameManager.isMoveValidJumpMove());
    }

    @Test
    public void inValidJumpMoveTest(){
        gameManager.setTheMove(blackJumpMove);
        Cell inTheMiddle = theBoard.getCell(6, 4);
        inTheMiddle.setPiece(new Piece(Color.BLACK));
        gameManager.setTheBoard(theBoard);
        assertFalse(gameManager.isMoveValidJumpMove());
    }
}
