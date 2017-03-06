package team.squad.checkers;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by williammattern on 3/6/17.
 * @author John A. Squier
 */
public class CheckersBoard {
    private Cell[][] theBoard;
    private Stack<Piece> blackPieces;
    private Stack<Piece> redPieces;

    public CheckersBoard() {
        this.theBoard = new Cell[8][8];

        this.blackPieces = new Stack<>();
        this.redPieces = new Stack<>();
        this.initializePieces();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                theBoard[i][j] = new Cell(i, j); // also colors the cells

                // fill the correct cells w pieces
                if (i <= 2) {
                    if (theBoard[i][j].getCellColor().equals(Color.BLACK)) {
                        theBoard[i][j].setHasPiece(true);
                        theBoard[i][j].setChecker(redPieces.pop());
                    }
                } else if (i >= 5) {
                    if (theBoard[i][j].getCellColor().equals(Color.BLACK)) {
                        if (theBoard[i][j].getCellColor().equals(Color.BLACK)) {
                            theBoard[i][j].setHasPiece(true);
                            theBoard[i][j].setChecker(blackPieces.pop());
                        }
                    }
                }
            }
        }
    }

    public Cell getCell(int i, int j){
        return theBoard[i][j];
    }

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

    /*
        Returns the Color of the winner or null if the game isn't won
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
