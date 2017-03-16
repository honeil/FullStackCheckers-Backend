package team.squad.checkersBE;

import team.squad.Model.GameManager;
import team.squad.Model.Move;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by mkulima on 3/16/17.
 */
public class DatabaseIntegrationTest {

    public static void main(String[] args) {
        GameManager gm = new GameManager();
        GameManager gm2 = new GameManager();
        List<Map> initialBoard = gm.generateInitialBoardState();


        // make 2 player moves and 2 npc moves.
        Move p1 = new Move(); p1.setFirstCoordinate("A3"); p1.setSecondCoordinate("B4");
        gm.setTheMove(p1);

        Move p2 = new Move(); p2.setFirstCoordinate("C3"); p2.setSecondCoordinate("D4");
        gm.setTheMove(p2);

        List<Map> playedBoard = gm.generateNewBoardStateFromComputerMove();

        System.out.println(Arrays.toString(Arrays.asList(playedBoard).toArray()));


        // attempt to pass played map to new GameManager
        gm2.generateInitialBoardState();
    }

}
