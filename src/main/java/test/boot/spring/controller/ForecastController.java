package test.boot.spring.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import test.boot.spring.exception.NoTownException;
import test.boot.spring.model.Forecast;
import test.boot.spring.service.ForecastService;
import test.boot.spring.utils.PrivateLogger;

@RestController
public class ForecastController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ForecastService forecastService;

    @Autowired
    PrivateLogger privateLogger;

    @GetMapping(path = "/weather")
    public Forecast getForecast(@RequestParam String town) throws NoTownException {
            privateLogger.log("Forecast for " + town + " has been requested.");
            return restTemplate.getForObject(forecastService.getUrl(town), Forecast.class);
    }


}
