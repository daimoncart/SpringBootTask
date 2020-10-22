package test.boot.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import test.boot.spring.controller.ForecastController;

public class TownService {
    @Autowired
    ForecastController forecastController;

}
