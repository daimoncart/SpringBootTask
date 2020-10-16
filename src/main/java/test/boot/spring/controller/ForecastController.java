package test.boot.spring.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import test.boot.spring.model.Forecast;

@RestController
public class ForecastController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Environment env;



    private String url = "http://api.openweathermap.org/data/2.5/forecast?q=Grozny&units=metric&appid=" +
            getKey();

    @GetMapping(path="/weather")
    public Forecast getForecast(){
        return restTemplate.getForObject(url, Forecast.class);
    }


    private static String getKey(){
         String appId1 = "6cd083ea34ca3da3df07f2fb02689ba";
         int appId2 = (int) Math.pow(2,3);
         return appId1+ String.valueOf(appId2);
    }
}
