package team.squad.controller;

import org.springframework.web.bind.annotation.*;
import team.squad.Model.*;

/**
 * Created by zipcoder on 3/6/17.
 */
@RestController
public class  HomeController {

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
    Move updateBoardStateWithMove(@RequestBody Move move) {
        MoveHandler handler = new MoveHandler(move);
        return handler.checkGivenMoveAndDetermineNextTurn();
    }

}
