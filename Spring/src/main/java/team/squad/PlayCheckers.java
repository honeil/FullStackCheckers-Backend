package team.squad;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import team.squad.Model.Move;
import team.squad.Model.MoveHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mkulima on 3/7/17.
 */


@Service()
public class PlayCheckers {


    public Map<Integer, String> play() {
        ApplicationContext appContext = new AnnotationConfigApplicationContext(AppConfig.class);

        //Move move = appContext.getBean("moveService", Move.class);
        MoveHandler mover = appContext.getBean("moveHandler", MoveHandler.class);
        System.out.println("\n\n\n movedata:" + mover.checkGivenMoveAndUpdateBoard());
        Map<Integer, String> theMap = new HashMap<>();
        theMap.put(1,"A1");
        theMap.put(2, "G8");
        return theMap;
    }
}
