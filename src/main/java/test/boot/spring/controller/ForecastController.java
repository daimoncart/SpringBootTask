package test.boot.spring.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import test.boot.spring.exception.TownNotFoundException;
import test.boot.spring.model.Forecast;
import test.boot.spring.service.ForecastService;
import test.boot.spring.utils.PrivateLogger;

@Api(value="ForecastController")
@RestController
public class ForecastController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ForecastService forecastService;

    @Autowired
    PrivateLogger privateLogger;

    @ApiOperation(value = "Get full forecast for a specific city/town")
    @GetMapping(path = "/weather")
    public Forecast getForecast(@RequestParam (defaultValue = "Punta Arenas") String town) {
        try{
            Forecast forecast = restTemplate.getForObject(forecastService.getUrl(town), Forecast.class);
            privateLogger.log("Forecast for " + town + " has been requested.");
            return forecast;
        }
        catch (TownNotFoundException e) {
            throw new TownNotFoundException("sd");
        }
    }


}
