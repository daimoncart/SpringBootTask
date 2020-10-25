package test.boot.spring.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Optional;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter @Setter @ToString
public class Forecast {
    private int cod;
    private Stint[] list;

    public int getMinVisibility(){
        return Arrays.stream(list)
                .mapToInt(Stint::getVisibility)
                .min().orElseThrow(NoSuchElementException::new);
    }

    public float getMaxWind(){
        Optional<Stint> windiestStint = Arrays.stream(list)
                .max(Comparator.comparing(Stint::getWindSpeed));
        return windiestStint.map(Stint::getWindSpeed).orElse(0F);
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter @Setter @ToString
class Stint {
    private String dt;
    private int visibility;
    private Wind wind;

    float getWindSpeed(){
        return wind.getSpeed();
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter @Setter @ToString
class Wind {
    private float speed;
}
