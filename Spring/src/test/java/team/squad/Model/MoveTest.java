package team.squad.Model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author William Mattern
 * @author John A. Squier
 *
 * Date Created: 3/6/17.
 */
public class MoveTest {

    Move move;

    @Before
    public void setUp() throws Exception {
        move = new Move();
        move.setFirstCoordinate("A3");
        move.setSecondCoordinate("B4");
    }

    @Test
    public void areCellNamesConvertedToCoordsTest() {
        int expectedXInitial = 0;
        int expectedYInitial = 2;
        int expectedXDesired = 1;
        int expectedYDesired = 3;

        int actualXInitial = move.getxPositionInitial();
        int actualYInitial = move.getyPositionInitial();
        int actualXDesired = move.getxPositionDesired();
        int actualYDesired = move.getyPositionDesired();

        assertEquals(expectedXInitial, actualXInitial);
        assertEquals(expectedYInitial, actualYInitial);
        assertEquals(expectedXDesired, actualXDesired);
        assertEquals(expectedYDesired, actualYDesired);
    }
}