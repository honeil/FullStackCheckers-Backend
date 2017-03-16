package team.squad.CheckersBE;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import team.squad.checkersBE.CheckersSQLDB;

import static org.junit.Assert.assertEquals;

/**
 * Created by John Antonio Collins
 */
public class CheckersSQLDBTest
{
    CheckersSQLDB dbTest;

    @Before
    public void setUp() throws Exception
    {
        dbTest = new CheckersSQLDB();
        dbTest.makeJDBCConnection();

    }

    @After
    public void cleanUp()
    {

        dbTest.deleteGame("-2");

    }

    @Test
    public void makeJDBCConnectionTest()
    {
        dbTest.addGameToDB("-1", "initial_state");
        String actual = dbTest.getGame("-1");
        String expected = "initial_state";
        assertEquals("should say 'initial_state'", expected, actual);

    }

    @Test
    public void addGameToDBtest()
    {
        dbTest.addGameToDB("-2", "addGameToDBtest_DATA");
        String actual = dbTest.getGame("-2");
        String expected = "addGameToDBtest_DATA";
        assertEquals("should say 'addGameToDBtest_DATA'", expected, actual);

    }

    @Test
    public void getGameTest()
    {
        String actual = dbTest.getGame("-1");
        String expected = "initial_state";
        assertEquals("should say 'initial_state'", expected, actual);
    }

    @Test
    public void saveGameTest()
    {
        dbTest.addGameToDB("-1", "initial_state");

        dbTest.saveGame("-1", "saveGameTest_DATA");
        String actual = dbTest.getGame("-1");
        String expected = "saveGameTest_DATA";
        assertEquals("should say 'saveGameTest_DATA'", expected, actual);

    }

    @Test
    public void deleteGameTest()
    {
        String actual = "pass";
        dbTest.addGameToDB("-1", "deleteGameTest_DATA");
        dbTest.deleteGame("-1");
        try
        {
            dbTest.getGame("-1");
        } catch (NullPointerException e)
        {
            actual = "fail";
        }
        String expected = "pass";
        assertEquals("should say ...", expected, actual);
    }
}