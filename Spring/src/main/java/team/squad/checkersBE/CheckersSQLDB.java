package team.squad.checkersBE;

import java.sql.*;

/**
 * Created by Andres Holland on 3/6/17.
 * and John Antonio Collins on 3/16/17.
 */

////Note: works only with live database for now. TODO use mock objects instead of live database
public class CheckersSQLDB
{

    static Connection checkersConn = null;
    static PreparedStatement checkersPrepareStat = null;


    public static void makeJDBCConnection()
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
            checkersConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/CheckersDB", "root", "");

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

    public static void addGameToDB(String gameID, String contents)
    {

        try
        {
            String insertQueryStatement = "INSERT INTO games VALUES (?,?)";

            checkersPrepareStat = checkersConn.prepareStatement(insertQueryStatement);
            checkersPrepareStat.setString(1, gameID);
            checkersPrepareStat.setString(2, contents);

            checkersPrepareStat.executeUpdate();
            log(gameID + " with contents of " + contents + " created.");
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static String getGame(String gameID)
    {

        String ID = "", contents = "";

        try
        {
            String getQueryStatement = "SELECT * FROM games;";

            checkersPrepareStat = checkersConn.prepareStatement(getQueryStatement);

            ResultSet rs = checkersPrepareStat.executeQuery();

            while (rs.next())
            {
                ID = rs.getString("ID");
                contents = rs.getString("Contents");
                System.out.format("%s, %s\n", ID, contents);
            }

            return contents;

        } catch (SQLException e)
        {
            e.printStackTrace();
            return "FAILURE";
        }
    }


    public static void saveGame(String gameID, String contents)
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

    public static void deleteGame(String gameID){

        try
        {
            String deleteQueryStatement = "DELETE FROM games where ID ="+gameID+";";


            checkersPrepareStat = checkersConn.prepareStatement(deleteQueryStatement);

            checkersPrepareStat.executeUpdate();
            log("game " + gameID + " has been deleted");

        } catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    public static void log(String string)
    {
        System.out.println(string);
    }
}


