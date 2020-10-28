package test.boot.spring.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.FileCopyUtils;
import test.boot.spring.model.Forecast;

import java.io.*;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ForecastServiceTest {

    @InjectMocks
    private ForecastService forecastService;

    @Value("classpath:wellington.json")
    private Resource resource;

    ObjectMapper mapper = new ObjectMapper();

    @Test
    void getUrl_test() {
        String url1 = "http://api.openweathermap.org/data/2.5/forecast?q=Success&units=metric&appid=6cd083ea34ca3da";
        String url2 = "3df07f2fb02689ba8";
        String urlTested = forecastService.getUrl("Success");
        Assert.assertEquals(url1 + url2, urlTested);
    }

    @Test
    void calculateForecastData_test() throws IOException {
        String jsonText;
        try (Reader reader = new InputStreamReader(resource.getInputStream(), UTF_8)) {
            jsonText = FileCopyUtils.copyToString(reader);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        Forecast forecast = mapper.readValue(jsonText, Forecast.class);
        int minVisibility = forecast.getMinVisibility();
        float maxWind = forecast.getMaxWind();
        Assert.assertEquals(119.48f, maxWind, 0.001);
        Assert.assertEquals(-10, minVisibility);
    }
}