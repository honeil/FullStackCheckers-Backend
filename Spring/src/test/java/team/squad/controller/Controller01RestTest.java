package team.squad.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by williammattern on 3/15/17.
 */

@WebAppConfiguration(value = "/start")
@RunWith(SpringJUnit4ClassRunner.class)
public class Controller01RestTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext webApplicationContext;


    private Controller01 controller01;

    @Before
    public void setUp(){
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testGetNewGameEndpoint() throws Exception{
        String uri = "/start";
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_UTF8)).andReturn();

        System.out.println();
        System.out.println(result.getResponse().getStatus());
        System.out.println();
    }
}
