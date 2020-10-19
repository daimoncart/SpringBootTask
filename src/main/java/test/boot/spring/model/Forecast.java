package test.boot.spring.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter @Setter @ToString
public class Forecast {
    private Stint[] list;
}

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter @Setter @ToString
class Stint {
    private String dt;
    private int visibility;
    private Wind wind;
}

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter @Setter @ToString
class Wind {
    private float speed;
}
