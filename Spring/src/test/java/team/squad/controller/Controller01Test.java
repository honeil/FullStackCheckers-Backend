package team.squad.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import team.squad.Model.GameManager;
import team.squad.Model.Move;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

/**
 * Created by mkulima on 3/8/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class Controller01Test {

//    MockMvc mvc;
//
//    MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
//    MockHttpServletResponse response = mvc.perform(get("/start"))
//            .andExpect(status().isOk())
//            .andReturn().getResponse();


    @Mock
    private GameManager gameManager;

    @InjectMocks
    private Controller01 controller;

    public Controller01Test() throws Exception
    {
    }

    @Test
    public void shouldCallMoveHandler() {
        controller.generateInitialBoardState();


        verify(gameManager, times(1)).generateInitialBoardState();
        verifyNoMoreInteractions(gameManager);
    }

    @Test
    public void shouldReturnMapFromMoveHandler() {
        List<Map> returnValue = new ArrayList<>();
        when(gameManager.generateInitialBoardState()).thenReturn(returnValue);

//        mvc.perform(get("/npcMove"))
//                .andExpect(status().isOk());
//        //.andReturn().getResponse();

        //System.out.println(returnValue);
        assertThat(controller.generateInitialBoardState(), is(returnValue));
    }



    @Test
    public void shouldSetTheMove() {
        Move move = new Move();

        controller.generateNewBoardStateFromPlayerMove(move);

        verify(gameManager).setTheMove(move);
        verify(gameManager).generateNewBoardStateFromPlayerMove();
    }
//    @Test
//    public void findAll_TodosFound_ShouldReturnFoundTodoEntries() throws Exception {
////        Todo first = new TodoBuilder()
////                .id(1L)
////                .description("Lorem ipsum")
////                .title("Foo")
////                .build();
////        Todo second = new TodoBuilder()
////                .id(2L)
////                .description("Lorem ipsum")
////                .title("Bar")
////                .build();
//
//        when(todoServiceMock.findAll()).thenReturn(Arrays.asList(first, second));
//
//        mockMvc.perform(get("/api/todo"))
//                .andExpect(status().isOk());
////                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
////                .andExpect(jsonPath("$", hasSize(2)))
////                .andExpect(jsonPath("$[0].id", is(1)))
////                .andExpect(jsonPath("$[0].description", is("Lorem ipsum")))
////                .andExpect(jsonPath("$[0].title", is("Foo")))
////                .andExpect(jsonPath("$[1].id", is(2)))
////                .andExpect(jsonPath("$[1].description", is("Lorem ipsum")))
////                .andExpect(jsonPath("$[1].title", is("Bar")));
//
//        verify(todoServiceMock, times(1)).findAll();
//        verifyNoMoreInteractions(todoServiceMock);
//    }

}