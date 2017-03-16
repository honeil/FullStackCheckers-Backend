package team.squad.Model;

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
    private List<Move> availableJumps;
    public List<Cell> blackTeamPositions;


    public Move generateMove(CheckersBoard theBoard){
        this.theBoard = theBoard;
        locateAllBlackPieces();
        availableJumps = scanForJumps();
        if(availableJumps.size()>0){
            return availableJumps.get(0);
        }
        availableMoves = scanForMoves();
        return availableMoves.get(0);
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

    public void locateAllBlackPieces(){
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

    public List<Move> scanForJumps(){
        ArrayList<Move> availableJumps = new ArrayList<>();
        for(Cell blackPieceCell:blackTeamPositions){
            Cell availableCell = generateJumpIfAvailable(blackPieceCell);
            if(availableCell != null){
                Move availableMove = new Move();
                availableMove.setFirstCoordinate(blackPieceCell.getCellName());
                availableMove.setSecondCoordinate(availableCell.getCellName());
                availableJumps.add(availableMove);
            }
        }
        return availableJumps;
    }

    Cell generateMoveIfAvailable(Cell cellToMovePieceFrom) {
        int xForDownAndLeft =  cellToMovePieceFrom.getxPosition() - 1; // left
        int yForDownAndLeft =  cellToMovePieceFrom.getyPosition() - 1; // down
        Cell downAndLeft = null;
        if ( cellToMovePieceFrom.getxPosition() > 0 && cellToMovePieceFrom.getyPosition() > 0) {
            downAndLeft = theBoard.getCell(xForDownAndLeft, yForDownAndLeft);
        }

        int xforDownAndRight = cellToMovePieceFrom.getxPosition() + 1; // right
        int yforDownAndRight = cellToMovePieceFrom.getyPosition() - 1; // down
        Cell downAndRight = null;
        if ( cellToMovePieceFrom.getxPosition() < 7 && cellToMovePieceFrom.getyPosition() > 0) {
            downAndRight = theBoard.getCell(xforDownAndRight, yforDownAndRight);
        }

        int xForUpAndLeft =  cellToMovePieceFrom.getxPosition() - 1; // left
        int yForUpAndLeft =  cellToMovePieceFrom.getyPosition() + 1; // up
        Cell upAndLeft = null;
        if ( cellToMovePieceFrom.getxPosition() > 0 && cellToMovePieceFrom.getyPosition() < 7) {
            upAndLeft = theBoard.getCell(xForUpAndLeft, yForUpAndLeft);
        }

        int xforUpAndRight = cellToMovePieceFrom.getxPosition() + 1; // right
        int yforUpAndRight = cellToMovePieceFrom.getyPosition() - 1; // up
        Cell upAndRight = null;
        if ( cellToMovePieceFrom.getxPosition() < 7 && cellToMovePieceFrom.getyPosition() < 7) {
            upAndRight = theBoard.getCell(xforUpAndRight, yforUpAndRight);
        }

        if ( cellToMovePieceFrom.getPiece().getKing() ) {//piece is a king
            if ( upAndRight != null && upAndRight.getHasPiece().equals(false) ) {
                return upAndRight;
            } else if ( upAndLeft != null && upAndLeft.getHasPiece().equals(false) ) {
                return upAndLeft;
            } else if ( downAndLeft != null && downAndLeft.getHasPiece().equals(false) ) {
                return downAndLeft;
            } else if ( downAndRight != null && downAndRight.getHasPiece().equals(false) ) {
                return downAndRight;
            }
            else {
                return null;
            }
        } else { //piece is not a king
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

    Cell generateJumpIfAvailable(Cell cellToMovePieceFrom) {
        int xForDownAndLeft =  cellToMovePieceFrom.getxPosition() - 1; // left
        int yForDownAndLeft =  cellToMovePieceFrom.getyPosition() - 1; // down
        int xForDownAndLeft2 =  cellToMovePieceFrom.getxPosition() - 2; // left
        int yForDownAndLeft2 =  cellToMovePieceFrom.getyPosition() - 2; // down
        Cell downAndLeft = null, downAndLeft2 = null;
        if ( cellToMovePieceFrom.getxPosition() > 1 && cellToMovePieceFrom.getyPosition() > 1) {
            downAndLeft = theBoard.getCell(xForDownAndLeft, yForDownAndLeft);
            downAndLeft2 = theBoard.getCell(xForDownAndLeft2,yForDownAndLeft2);
        }

        int xforDownAndRight = cellToMovePieceFrom.getxPosition() + 1; // right
        int yforDownAndRight = cellToMovePieceFrom.getyPosition() - 1; // down
        int xforDownAndRight2 = cellToMovePieceFrom.getxPosition() + 2; // right
        int yforDownAndRight2 = cellToMovePieceFrom.getyPosition() - 2; // down
        Cell downAndRight = null, downAndRight2 = null;
        if ( cellToMovePieceFrom.getxPosition() < 6 && cellToMovePieceFrom.getyPosition() > 1) {
            downAndRight = theBoard.getCell(xforDownAndRight, yforDownAndRight);
            downAndRight2 = theBoard.getCell(xforDownAndRight2, yforDownAndRight2);
        }

        int xForUpAndLeft = cellToMovePieceFrom.getxPosition() - 1;
        int yForUpAndLeft = cellToMovePieceFrom.getyPosition() + 1;
        int xForUpAndLeft2 = cellToMovePieceFrom.getxPosition() - 2;
        int yForUpAndLeft2 = cellToMovePieceFrom.getyPosition() + 2;
        Cell upAndLeft = null, upAndLeft2 = null;
        if ( cellToMovePieceFrom.getxPosition() > 1 && cellToMovePieceFrom.getyPosition() < 6 ) {
            upAndLeft = theBoard.getCell(xForUpAndLeft, yForUpAndLeft);
            upAndLeft2 = theBoard.getCell(xForUpAndLeft2, yForUpAndLeft2);
        }

        int xForUpAndRight = cellToMovePieceFrom.getxPosition() + 1;
        int yForUpAndRight = cellToMovePieceFrom.getyPosition() + 1;
        int xForUpAndRight2 = cellToMovePieceFrom.getxPosition() + 2;
        int yForUpAndRight2 = cellToMovePieceFrom.getyPosition() + 2;
        Cell upAndRight = null, upAndRight2 = null;
        if ( cellToMovePieceFrom.getxPosition() < 6 && cellToMovePieceFrom.getyPosition() < 6 ) {
            upAndRight = theBoard.getCell(xForUpAndRight, yForUpAndRight);
            upAndRight2 = theBoard.getCell(xForUpAndRight2, yForUpAndRight2);
        }

        if ( cellToMovePieceFrom.getPiece().getKing() ) {
            if ( upAndLeft2 != null && upAndLeft.getHasPiece().equals(true) && upAndLeft2.getHasPiece().equals(false) ) {
                if ( upAndLeft.getPiece().getPieceColor().equals(Color.RED) ) {
                    return upAndLeft2;
                }
            }
            if ( upAndRight2 != null && upAndRight.getHasPiece().equals(true) && upAndRight2.getHasPiece().equals(false) ) {
                if ( upAndRight.getPiece().getPieceColor().equals(Color.RED) ) {
                    return upAndRight2;
                }
            }
            if ( downAndLeft2 != null && downAndLeft.getHasPiece().equals(true) && downAndLeft2.getHasPiece().equals(false) ) {
                if(downAndLeft.getPiece().getPieceColor().equals(Color.RED)) {
                    return downAndLeft2;
                }
            }
            if ( downAndRight2 != null && downAndRight.getHasPiece().equals(true) && downAndRight2.getHasPiece().equals(false) ) {
                if(downAndRight.getPiece().getPieceColor().equals(Color.RED)){
                    return downAndRight2;
                }
            }
            return null;
        }
        else {
            if ( downAndLeft2 != null && downAndLeft.getHasPiece().equals(true) && downAndLeft2.getHasPiece().equals(false) ) {
                if(downAndLeft.getPiece().getPieceColor().equals(Color.RED)) {
                    return downAndLeft2;
                }
            }
            if ( downAndRight2 != null && downAndRight.getHasPiece().equals(true) && downAndRight2.getHasPiece().equals(false) ) {
                if(downAndRight.getPiece().getPieceColor().equals(Color.RED)){
                    return downAndRight2;
                }
            }
            return null;
        }
    }
}
