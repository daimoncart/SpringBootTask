package test.boot.spring.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity @Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
@SequenceGenerator(name="seq", initialValue=6, allocationSize=100)
public class Employee {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    private long id;

    @Column(nullable=false, length=50)
    @Size(min=2, message="First name should be at least 2 chars long")
    private String firstName;

    @Column(nullable=false, length=50)
    @Size(min=2, message="Last name should be at least 2 chars long")
    private String lastName;

    @Column(nullable=false, length=100)
    @Size(min=8, message="Email should be at least 8 chars long")
    private String email;

    @ManyToOne(fetch=FetchType.LAZY)
    private Town town;

}
