package test.boot.spring.service;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ForecastServiceTest {

    @InjectMocks
    private ForecastService forecastService;

    @Test
    void getUrl_test() {
        String url1 = "http://api.openweathermap.org/data/2.5/forecast?q=Success&units=metric&appid=6cd083ea34ca3da";
        String url2 = "3df07f2fb02689ba8";
        String urlTested = forecastService.getUrl("Success");
        Assert.assertEquals(url1 + url2, urlTested);
    }
}