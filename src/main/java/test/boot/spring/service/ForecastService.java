package test.boot.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import test.boot.spring.model.Forecast;
import test.boot.spring.model.Town;
import test.boot.spring.repository.TownRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ForecastService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TownRepository townRepository;

    @Autowired
    private CacheManager cacheManager;

    public void compareApiData(){
        boolean isCacheOk = true;
        List<Town> towns = townRepository.findAll();
        for (Town town: towns) {
            Forecast forecast = restTemplate.getForObject(getUrl(town.getName()), Forecast.class);
            int apiMinVisibility = forecast.getMinVisibility();
            float apiMaxWind = forecast.getMaxWind();
            if (apiMaxWind != town.getMaxWind() || apiMinVisibility != town.getMinVisibility()){
                isCacheOk = false;
                town.setMinVisibility(apiMinVisibility);
                town.setMaxWind(apiMaxWind);
                townRepository.save(town);
            }
        }
        if (!isCacheOk){
            cacheManager.getCache("towns").clear();
            System.out.println("Cache cleared");
        } else {
            System.out.println("Cache OK");
        }

//        List<String> towns = townRepository.findAll()
//                .stream()
//                .map(t -> t.getName())
//                .collect(Collectors.toList());
//
////        List<Town> apiTowns = new ArrayList<>();
//        boolean isCacheOk = true;
//        for (String townName: towns){
//            Forecast forecast = restTemplate.getForObject(getUrl(townName), Forecast.class);
//            int minVisibility = forecast.getMinVisibility();
//            float maxWindSpeed = forecast.getMaxWind();
//            Town apiTown = new Town(townName, minVisibility, maxWindSpeed);
////            Town dbTown = townRepository.findByName(townName);
//            System.out.println(apiTown);
//            if (apiTown.getMaxWind() != dbTown.getMaxWind() ||
//                    apiTown.getMinVisibility() != dbTown.getMinVisibility()) {
//                dbTown.setMaxWind(apiTown.getMaxWind());
//                dbTown.setMinVisibility(apiTown.getMinVisibility());
//                townRepository.save(dbTown);
//                isCacheOk = false;
//            }
//            apiTowns.add(apiTown);




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
