package test.boot.spring.entity;

import lombok.*;

import javax.persistence.*;

@Entity @Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
@SequenceGenerator(name="seq", initialValue=6, allocationSize=100)
public class Employee {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    private long id;

    @Column(nullable=false, length=50)
    private String firstName;

    @Column(nullable=false, length=50)
    private String lastName;

    @Column(nullable=false, length=100)
    private String email;

}
