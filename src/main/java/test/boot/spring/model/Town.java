package test.boot.spring.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@SequenceGenerator(name = "seq2", initialValue = 6, allocationSize = 100)
public class Town {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq2")
    private long id;

    @Column(nullable = false, length = 50)
    @Size(min=2, message="Town/city name should be at least 2 chars long")
    private String name;

    @Column
    private int minVisibility;

    @Column
    private float maxWind;

    @OneToMany(mappedBy="town")
    private List<Employee> employees;
}
