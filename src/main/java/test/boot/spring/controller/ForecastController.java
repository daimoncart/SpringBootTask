package test.boot.spring.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import test.boot.spring.model.Forecast;
import test.boot.spring.service.ForecastService;

@RestController
public class ForecastController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ForecastService forecastService;

//    @Autowired
//    private Environment env;

    @GetMapping(path="/weather")
    public Forecast getForecast(@RequestParam String town){
        return restTemplate.getForObject(forecastService.getUrl(town), Forecast.class);
    }



}
