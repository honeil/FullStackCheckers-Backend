package team.squad.controller;

import org.springframework.web.bind.annotation.*;
import team.squad.Model.Color;
import team.squad.Model.Piece;

/**
 * Created by zipcoder on 3/6/17.
 */
@RestController
public class HomeController {

    @RequestMapping("/piece/{path}")
    public Piece piece(@RequestParam(value = "name") String name, @PathVariable String path){
        System.out.println(name);
        System.out.println(path);
        Piece piece = new Piece(Color.BLACK);
        piece.setXPosition(5);
        piece.setYPosition(7);
        return piece;
    }

}
