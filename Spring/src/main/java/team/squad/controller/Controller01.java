package team.squad.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
public class Controller01 {

    private MoveHandler moveHandler;

    @Autowired
    public Controller01(MoveHandler moveHandler) {
        this.moveHandler = moveHandler;
    }

    @RequestMapping("/start")
    public List<Map> generateInitialBoardState() {
        return moveHandler.generateInitialBoardState();
    }

    @RequestMapping("/pcMove")
    public List<Map> generateNewBoardStateFromComputerMove() {
        return moveHandler.generateNewBoardStateFromComputerMove();
    }

    @RequestMapping(value = "/playerMove", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Map> generateNewBoardStateFromPlayerMove(@RequestBody Move move) {
        moveHandler.setTheMove(move);
        return moveHandler.generateNewBoardStateFromPlayerMove();
    }
}

