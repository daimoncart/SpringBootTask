package test.boot.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import test.boot.spring.exception.NoTownException;
import test.boot.spring.model.Forecast;
import test.boot.spring.model.Town;
import test.boot.spring.repository.TownRepository;
import test.boot.spring.utils.PrivateLogger;

import java.util.List;

@Service
public class ForecastService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TownRepository townRepository;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private PrivateLogger privateLogger;

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
            privateLogger.log("New data in forecast cache.");
        } else {
            privateLogger.log("Results fetched from forecast cache.");
        }
    }

    public String getUrl(String town){
        String uriCall = "http://api.openweathermap.org/data/2.5/forecast?q=";
        String appId1 = "&units=metric&appid=6cd083ea34ca3da3df07f2fb02689ba";
        int appId2 = (int) Math.pow(2,3);
        return uriCall + town + appId1 + appId2;
    }

    public boolean checkForTownName(String town) throws NoTownException {
        Forecast forecast = restTemplate.getForObject(getUrl(town), Forecast.class);
        return true;
    }


}
