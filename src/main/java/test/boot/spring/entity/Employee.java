package test.boot.spring.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity @Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable=false, length=50)
    private String firstName;

    @Column(nullable=false, length=50)
    private String lastName;

    @Column(nullable=false, length=100)
    private String email;

}
