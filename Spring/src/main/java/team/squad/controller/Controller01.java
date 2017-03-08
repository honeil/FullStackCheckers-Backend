package team.squad.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import team.squad.Model.Move;
import team.squad.Model.MoveHandler;

import java.util.List;
import java.util.Map;

/**
 * Created by johncollins on 3/8/17.
 */
@CrossOrigin
@RestController
public class Controller01
{
    @RequestMapping("/start")
    public java.util.Map generateInitialBoardState()
    {
        return new MoveHandler().generateInitialBoardState();
    }

    @RequestMapping("/pcMove")
    public java.util.Map generateNewBoardStateFromComputerMove(){
        return new MoveHandler().generateNewBoardStateFromComputerMove();
        //return new MoveHandler().generateInitialBoardState();

    }

    //@RequestMapping("/playerMove")//needs POST to pass player move as args
    @RequestMapping(value = "/playerMove", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
   public  java.util.Map generateNewBoardStateFromPlayerMove(@RequestBody Move move){
        MoveHandler moveHandler = new MoveHandler();
        moveHandler.setTheMove(move);
        return moveHandler.generateNewBoardStateFromPlayerMove();
        //return new MoveHandler().generateInitialBoardState();

    }
}

