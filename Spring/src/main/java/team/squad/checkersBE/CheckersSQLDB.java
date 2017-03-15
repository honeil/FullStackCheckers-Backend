package team.squad.checkersBE;

import java.sql.*;

/**
 * Created by andresholland on 3/6/17.
 */


public class CheckersSQLDB
{

    static Connection checkersConn = null;
    static PreparedStatement checkersPrepareStat = null;

    //TODO move this test suite to test.java.team.squad and convert it to a proper test
    public static void main(String[] args)
    {

        try
        {
            log("Establish connection to DB");
            makeJDBCConnection();

            log("--Add game ID to DB");

            addGameToDB("13", "milton");
            addGameToDB("14", "walgreens");

            saveGame("13", "testDAta");

            log("Get game ID from DB");
            getGame("13");

            checkersPrepareStat.close();
            checkersConn.close();


        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    private static void makeJDBCConnection()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            log("JDBC Connected");
        } catch (ClassNotFoundException e)
        {
            log("JDBC Connection Error - check dependencies in Maven POM or import MYSQL Jar File to Resources");
            e.printStackTrace();
            return;
        }
        try
        {
            checkersConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/games", "root", "");
            if (checkersConn != null)
            {
                log("Connection success!");

            } else
            {
                log("Check username and password");
            }
        } catch (SQLException e)
        {
            log("SQL Connection failure");
            e.printStackTrace();
            return;
        }
    }

    private static void addGameToDB(String gameID, String contents)
    {

        try
        {
            String insertQueryStatement = "INSERT INTO games VALUES (?,?)";

            checkersPrepareStat = checkersConn.prepareStatement(insertQueryStatement);
            checkersPrepareStat.setString(1, contents);
            checkersPrepareStat.setString(2, gameID);

            checkersPrepareStat.executeUpdate();
            log(gameID + "with contents of " + contents + " created.");
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    private static String getGame(String gameID)
    {

        String ID = "", contents = "";

        try
        {
            String getQueryStatement = "SELECT * FROM games";

            checkersPrepareStat = checkersConn.prepareStatement(getQueryStatement);

            ResultSet rs = checkersPrepareStat.executeQuery();

            while (rs.next())
            {
                ID = rs.getString("ID");
                contents = rs.getString("Contents");
                System.out.format("%s, %s\n", ID, contents);
            }

            return ID + contents;

        } catch (SQLException e)
        {
            e.printStackTrace();
            return "FAILURE";
        }
    }

    private static void saveGame(String gameID, String contents)
    {

        try
        {
            String insertQueryStatement = "UPDATE games SET contents = '" + contents + "' WHERE ID = '" + gameID + "';";

            checkersPrepareStat = checkersConn.prepareStatement(insertQueryStatement);

            checkersPrepareStat.executeUpdate();
            log("game " + gameID + " contents has been updated to " + contents);

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    private static void log(String string)
    {
        System.out.println(string);
    }
}


