package team.squad.controller;

import org.springframework.web.bind.annotation.*;
import team.squad.Model.Move;
import team.squad.Model.MoveHandler;

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
    // keep @RequestMapping(value = "/playerMove", method = RequestMethod.POST, consumes = {"application/json"})
    // keep public  java.util.Map generateNewBoardStateFromPlayerMove(@ResponseBody Move move){

        // keep return new MoveHandler().generateNewBoardStateFromPlayerMove();
        //return new MoveHandler().generateInitialBoardState();

    }

}

