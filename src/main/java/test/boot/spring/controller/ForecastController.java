package test.boot.spring.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import test.boot.spring.model.Forecast;

@RestController
public class ForecastController {

    @Autowired
    RestTemplate restTemplate;

//    @Autowired
//    private Environment env;

    @GetMapping(path="/weather")
    public Forecast getForecast(@RequestParam String town){
        return restTemplate.getForObject(getUrl(town), Forecast.class);
    }


    private static String getUrl(String town){
         String uriCall = "http://api.openweathermap.org/data/2.5/forecast?q=";
         String appId1 = "&units=metric&appid=6cd083ea34ca3da3df07f2fb02689ba";
         int appId2 = (int) Math.pow(2,3);
         return uriCall + town + appId1 + String.valueOf(appId2);
    }

    public boolean checkForTown(String town){
        Forecast forecast = restTemplate.getForObject(getUrl(town), Forecast.class);
        return forecast.getCod()==200;
    }
}
