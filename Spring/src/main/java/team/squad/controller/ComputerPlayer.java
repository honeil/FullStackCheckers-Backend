package team.squad.controller;

import org.springframework.context.annotation.Configuration;
import team.squad.Model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author William Mattern
 */
@Configuration
public class ComputerPlayer {
    CheckersBoard theBoard;
    private List<Move> availableMoves;
    private List<Piece> redTeam;
    public List<Cell> blackTeamPositions;


    public Move generateMove(CheckersBoard theBoard){
        this.theBoard = theBoard;
        trackAllBlackPieces();
        availableMoves = scanForMoves();
        return null;
    }

    public List<Cell> allCells(){
        List<Cell> cells = new ArrayList<>();
        for(int i = 0; i < 8 ; i++){
            for( int j = 0; j < 8; j++){
                cells.add(theBoard.getCell(i,j));
            }
        }
        return cells;
    }

    public void trackAllBlackPieces(){
        blackTeamPositions = new ArrayList<>();
        for(Cell cell:allCells()){
            if(cell.getHasPiece()){
                if(cell.getPiece().getPieceColor().equals(Color.BLACK)){
                    blackTeamPositions.add(cell);
                }
            }
        }
    }

    public List<Move> scanForMoves(){
        ArrayList<Move> availableMoves = new ArrayList<>();
        for(Cell blackPieceCell:blackTeamPositions){
            Cell availableCell = generateMoveIfAvailable(blackPieceCell);
            if(availableCell != null){
                Move availableMove = new Move();
                availableMove.setFirstCoordinate(blackPieceCell.getCellName());
                availableMove.setSecondCoordinate(availableCell.getCellName());
                availableMoves.add(availableMove);
            }
        }
        return availableMoves;
    }

    Cell generateMoveIfAvailable(Cell cellToMovePieceFrom) {
        // computer is black pieces which are on top of the board
        if ( cellToMovePieceFrom.getPiece().getKing() ) {
            // check all four available locations
            System.out.println("gotta work out logic for king moves");//////////////////////////////////////////////////////////////////////
            return null;
        }
        else {
            //  Cell downAndLeft = getDownAndLeftCell(cellToMovePieceFrom);
            int xForDownAndLeft =  cellToMovePieceFrom.getxPosition() - 1; // left
            int yForDownAndLeft =  cellToMovePieceFrom.getyPosition() - 1; // down
            Cell downAndLeft = null;
            if ( cellToMovePieceFrom.getxPosition() > 0 ) {
                downAndLeft = theBoard.getCell(xForDownAndLeft, yForDownAndLeft);
            }

            int xforDownAndRight = cellToMovePieceFrom.getxPosition() + 1; // right
            int yforDownAndRight = cellToMovePieceFrom.getyPosition() - 1; // down
            Cell downAndRight = null;
            if ( cellToMovePieceFrom.getxPosition() < 7 ) {
                downAndRight = theBoard.getCell(xforDownAndRight, yforDownAndRight);
            }

            if ( ((downAndLeft != null) && downAndLeft.getHasPiece().equals(false))
                    && ((downAndRight != null) && downAndRight.getHasPiece().equals(false)) ) {
                if ( (int)(Math.random() * 10) < 5 ) {
                    return downAndLeft;
                }
                else {
                    return downAndRight;
                }
            }
            else if ( downAndLeft != null && downAndLeft.getHasPiece().equals(false) ) {
                return downAndLeft;
            }
            else if ( downAndRight != null && downAndRight.getHasPiece().equals(false) ) {
                return downAndRight;
            }
            else {
                return null;
            }
        }
    }
}
