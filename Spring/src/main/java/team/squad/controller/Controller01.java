package team.squad.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.squad.Model.MoveHandler;

/**
 * Created by johncollins on 3/8/17.
 */
@CrossOrigin
@RestController
public class Controller01
{
    @RequestMapping("/generateInitialBoardState")
    public java.util.Map generateInitialBoardState()
    {
        return new MoveHandler().generateInitialBoardState();
    }

    
}

