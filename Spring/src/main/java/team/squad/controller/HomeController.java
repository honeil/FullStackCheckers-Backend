package team.squad.controller;

import org.springframework.web.bind.annotation.*;
import team.squad.Model.*;

import java.util.Map;

/**
 * Created by zipcoder on 3/6/17.
 */
@RestController
public class  HomeController {

    private CheckersBoard theBoard = new CheckersBoard();

    // example for this controller 127.0.0.1:8080/piece/imapath?name=JOHN
    @RequestMapping("/piece/{path}")
    public Piece piece(@RequestParam(value = "name") String name, @PathVariable String path){
        System.out.println(name);
        System.out.println(path);
        Piece piece = new Piece(Color.BLACK);
        piece.setXPosition(5);
        piece.setYPosition(7);
        return piece;
    }

    // see exampleMove.json
    @RequestMapping(value = "/movePiece", method = RequestMethod.POST, consumes = {"application/json"})
    public @ResponseBody
    Map updateBoardStateWithMove(@RequestBody Move move) {
        MoveHandler.generateNewBoardStateFromPlayerMove(move, theBoard);
        return BoardState.generateBoardState(theBoard);
    }

}
