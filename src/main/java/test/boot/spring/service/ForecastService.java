package test.boot.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import test.boot.spring.model.Forecast;
import test.boot.spring.repository.TownRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ForecastService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TownRepository townRepository;

    public void compareApiData(){
        List<String> towns = townRepository.findAll()
                .stream()
                .map(t -> t.getName())
                .collect(Collectors.toList());

        towns.stream().forEach(s -> System.out.println(s));
//        System.out.println("Hello there ______________________________________________________");

    }

    public String getUrl(String town){
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
