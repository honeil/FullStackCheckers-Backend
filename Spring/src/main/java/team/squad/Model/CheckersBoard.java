package team.squad.Model;

import java.util.Stack;

/**
 * @author William Mattern
 * @author John A. Squier
 *
 * NOTE THAT J is UP/DOWN/ROW and I is LEFT/RIGHT/COLUMN!!!
 *
 * Date Created: 3/6/17.
 */
public class CheckersBoard {
    private Cell[][] theBoard;
    private Stack<Piece> blackPieces;
    private Stack<Piece> redPieces;

    /**
     * Constructs a checkers board (the array), creates two stacks of twelve pieces each, and places
     * the pieces on the board.
     */
    public CheckersBoard() {
        this.theBoard = new Cell[8][8];
        this.blackPieces = new Stack<>();
        this.redPieces = new Stack<>();
        this.initializePieces();
        this.placePiecesOnBoard();
    }

    /**
     * Returns the cell at the given i and j.
     * @param i the x-position of the cell (0 is the left-most column)
     * @param j the y-position of the cell (0 is the bottom row)
     * @return the cell at the given x,y coords.
     */
    public Cell getCell(int i, int j){
        if ( (i < 0 || i > 7) || (j < 0 || j > 7) ) {
            throw new IndexOutOfBoundsException("Given index not in the range (0-7)");
        }
        else {
            return theBoard[i][j];
        }
    }

    /**
     * Creates 12 black pieces and 12 red pieces and places them in their appropriate stacks.
     * Think of this like creating two stack of checkers off to the side of the board before they get
     * placed on the board itself.
     */
    public void initializePieces(){
        for (int i =0; i < 12; i++){
            Piece thisPiece = new Piece(Color.BLACK);
            blackPieces.push(thisPiece);
        }
        for (int i =0; i < 12; i++){
            Piece thisPiece = new Piece(Color.RED);
            redPieces.push(thisPiece);
        }
    }

    /**
     * Places 12 red and 12 black pieces on the board in the proper positions.
     */
    private void placePiecesOnBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                theBoard[i][j] = new Cell(i, j); // also colors the cells

                // fill the correct cells w pieces
                if (j <= 2) {
                    if ( theBoard[i][j].getCellColor().equals(Color.BLACK) ) {
                        theBoard[i][j].setPiece(redPieces.pop());
                    }
                } else if (j >= 5) {
                    if ( theBoard[i][j].getCellColor().equals(Color.BLACK) ) {
                        theBoard[i][j].setPiece(blackPieces.pop());
                    }
                }
                //System.out.println("i=" + i + ", j=" + j + " = " +getCell(i,j).getCellName() + " is " + getCell(i,j).getCellColor() + " and hasPiece? " + getCell(i,j).getHasPiece());
            }
        }
    }

    /**
     * Adds a given piece to the appropriate stack after it has been jumped and removed from the board
     * @param toAddToStack
     */
    void addPieceToStack(Piece toAddToStack) {
        if ( toAddToStack.getPieceColor().equals(Color.RED) ) {
            redPieces.push(toAddToStack);
        }
        else {
            blackPieces.push(toAddToStack);
        }
    }

    /**
     * Used to determine the winner, if any.
     * @return the Color of the winner's pieces or null if there is currently no winner.
     */
    public Color whoHasWon() {
        if ( redPieces.size() == 12 ) {
            return Color.BLACK;
        }
        else if ( blackPieces.size() == 12 ) {
            return Color.RED;
        }
        else {
            return null; // could later change to unchecked exception NoPlayerWonException()
        }
    }
}
