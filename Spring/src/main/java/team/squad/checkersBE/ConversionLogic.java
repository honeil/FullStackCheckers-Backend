package team.squad.checkersBE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import team.squad.Model.GameManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by andresholland on 3/16/17.
 */
public class ConversionLogic {

    CheckersSQLDB checkersSQLDB = new CheckersSQLDB();

    Map<String, String> original = new HashMap<>();

    public Map<String, String> getMap(List<Map> listOfMaps, int index){
        original = listOfMaps.get(index);
        return original;
    }

    public String convertMapToString(Map<String, String> map){
        return map.toString();
    }

    /* public static void main(String[] args) {
            //SpringApplication.run(CheckersApplication.class, args);
            GameManager gameManager = new GameManager();
            List<Map> original = gameManager.getCurrentBoardStateForSave();

            for (int i = 0; i < original.size(); i++) {
                System.out.println(original.get(i));
            }

            Map<String, String> original1 = original.get(0);
            Map<String, String> original2 = original.get(1);
            Map<String, String> original3 = original.get(2);


            String [] playerTurn = original1.toString().split(",");
            String [] pieces = original2.toString().split(",");
            String [] gameStatus = original3.toString().split(",");

            Map<String, String> playerTurnMap = new HashMap<>();
            Map<String, String> pieceMap = new HashMap<>();
            Map<String, String> gameStatusMap = new HashMap<>();


            for (int i = 0; i < playerTurn.length; i++) {
                String playerT = playerTurn[i];
                String [] keyValue = playerT.split("=");
                keyValue[0] = keyValue[0].substring(1);
                keyValue[1] = keyValue[1].substring(0, (keyValue[1].length() -1 ));
                playerTurnMap.put(keyValue[0], String.valueOf(keyValue[1]));
            }

            for (int i = 0; i < pieces.length; i++) {
                String piece = pieces[i];
                String [] keyValue = piece.split("=");
                keyValue[0] = keyValue[0].substring(1);
                keyValue[1] = keyValue[1].replaceAll("[\\{\\}]","");
                pieceMap.put(keyValue[0], String.valueOf(keyValue[1]));
            }

            for (int i = 0; i < gameStatus.length; i++) {
                String gameS = gameStatus[i];
                String [] keyValue = gameS.split("=");
                keyValue[0] = keyValue[0].substring(1);
                keyValue[1] = keyValue[1].substring(0, (keyValue[1].length() -1 ));
                gameStatusMap.put(keyValue[0], String.valueOf(keyValue[1]));
            }
            //String [] originalArray = original.split(",")
            //System.out.println(pieces);

		*//*for (Map.Entry<String, String> entry: playerTurnMap.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}

		for (Map.Entry<String, String> entry: pieceMap.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}

		for (Map.Entry<String, String> entry: gameStatusMap.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}*//*

            List<Map> boardState = new ArrayList<>();
            boardState.add(playerTurnMap);
            boardState.add(pieceMap);
            boardState.add(gameStatusMap);

            for (int i = 0; i < boardState.size(); i++) {
                System.out.println(boardState.get(i));
            }


        }*/


}
