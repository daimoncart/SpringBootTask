package test.boot.spring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import test.boot.spring.model.Forecast;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ForecastControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void getForecastTest_withExistingTown() throws Exception {
        MvcResult result = mockMvc.perform(
                get("/weather")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String resultContent = result.getResponse().getContentAsString();
        Forecast forecast = mapper.readValue(resultContent, Forecast.class);
        Assert.assertTrue(forecast.getCod()==200);
    }

    @Test
    public void getForecastTest_withNonExistingTown() throws Exception {
        MvcResult result = mockMvc.perform(
                get("/weather?town=BongCloudCity")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        Assert.assertTrue(result.getResponse().getContentLength() == 0);
    }
}