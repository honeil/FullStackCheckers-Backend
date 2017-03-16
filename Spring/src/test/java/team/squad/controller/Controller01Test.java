package team.squad.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import team.squad.Model.GameManager;
import team.squad.Model.Move;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

/**
 * Created by mkulima on 3/8/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class Controller01Test {

    @Mock
    private GameManager gameManager;

    @InjectMocks
    private Controller01 controller;

    @Test
    public void shouldCallMoveHandler() throws Exception {
        controller.generateInitialBoardState();
        verify(gameManager).generateInitialBoardState();
        verify(gameManager, times(1)).generateInitialBoardState();
        verifyNoMoreInteractions(gameManager);
    }

    @Test
    public void shouldReturnMapFromMoveHandler() {
        List<Map> returnValue = new ArrayList<>();
        when(gameManager.generateInitialBoardState()).thenReturn(returnValue);
        assertThat(controller.generateInitialBoardState(), is(returnValue));
    }

    @Test
    public void shouldSetTheMove() {
        Move move = new Move();
        controller.generateNewBoardStateFromPlayerMove(move);
        verify(gameManager).setTheMove(move);
        verify(gameManager).generateNewBoardStateFromPlayerMove();
    }
}
