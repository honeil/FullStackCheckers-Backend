package team.squad.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    @RequestMapping("/generateInitialBoardState")
    public List<Map> generateInitialBoardState()
    {
        return new MoveHandler().generateInitialBoardState();
    }
}

