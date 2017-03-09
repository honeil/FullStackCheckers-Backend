package team.squad.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import team.squad.Model.Move;
import team.squad.Model.MoveHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by mkulima on 3/8/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class Controller01Test {

    @Mock
    private MoveHandler moveHandler;

    @InjectMocks
    private Controller01 controller;

    @Test
    public void shouldCallMoveHandler() {
        controller.generateInitialBoardState();
        verify(moveHandler).generateInitialBoardState();
    }

    @Test
    public void shouldReturnMapFromMoveHandler() {
        List<Map> returnValue = new ArrayList<>();
        when(moveHandler.generateInitialBoardState()).thenReturn(returnValue);

        assertThat(controller.generateInitialBoardState(), is(returnValue));
    }

    @Test
    public void shouldSetTheMove() {
        Move move = new Move();

        controller.generateNewBoardStateFromPlayerMove(move);

        verify(moveHandler).setTheMove(move);
        verify(moveHandler).generateNewBoardStateFromPlayerMove();
    }

}