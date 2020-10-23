package test.boot.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import test.boot.spring.controller.ForecastController;
import test.boot.spring.model.Town;
import test.boot.spring.repository.TownRepository;

import java.util.List;

@Service
public class TownService {
    @Autowired
    TownRepository townRepository;

    @Cacheable("towns")
    public Town findByName(String name){
        simulateSlowService();
        return townRepository.findByName(name);
    }

    private void simulateSlowService() {
        try {
            long time = 5000L;
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }

}
