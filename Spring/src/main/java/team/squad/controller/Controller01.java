package team.squad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import team.squad.Model.GameManager;
import team.squad.Model.Move;

import java.util.List;
import java.util.Map;

/**
 * Created by johncollins on 3/8/17.
 */
@CrossOrigin
@RestController
public class Controller01 {

    private GameManager gameManager;

    @Autowired
    public Controller01(GameManager gameManager) {
        this.gameManager = gameManager;
    }


    @RequestMapping("/start")
    public List<Map> generateInitialBoardState() {
        return gameManager.generateInitialBoardState();
    }

    @RequestMapping("/npcMove")
    public List<Map> generateNewBoardStateFromComputerMove() {
        return gameManager.generateNewBoardStateFromComputerMove();
    }

    @RequestMapping(value = "/playerMove", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Map> generateNewBoardStateFromPlayerMove(@RequestBody Move move) {
        gameManager.setTheMove(move);
        return gameManager.generateNewBoardStateFromPlayerMove();
    }
}
