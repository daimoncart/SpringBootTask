package test.boot.spring.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
@SequenceGenerator(name = "seq2", initialValue = 6, allocationSize = 100)
@ApiModel(description = "Entity for towns and their essential forecast data")
public class Town {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq2")
    private long id;

    @Column(nullable = false, length = 50)
    @Size(min=2, message="Town/city name should be at least 2 chars long")
    @ApiModelProperty(notes = "Should be at least 2 chars", required = true)
    private String name;

    @Column
    @ApiModelProperty(notes = "What will be the minimum visibility in the next 5 days")
    private int minVisibility;

    @Column
    @ApiModelProperty(notes = "What will be the maximum wind speed in the next 5 days")
    private float maxWind;

    @OneToMany(mappedBy="town")
    private List<Employee> employees;

    public Town(@Size(min = 2, message = "Town/city name should be at least 2 chars long") String name, int minVisibility, float maxWind) {
        this.name = name;
        this.minVisibility = minVisibility;
        this.maxWind = maxWind;
    }
}
