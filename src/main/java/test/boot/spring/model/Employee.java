package test.boot.spring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

@ApiModel(description="Employee details - first name, last name, email address")
@Entity @Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString @Builder
@SequenceGenerator(name="seq", initialValue=6, allocationSize=100)
public class Employee {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    private long id;

    @Column(nullable=false, length=50)
    @Size(min=2, message="First name should be at least 2 chars long")
    @ApiModelProperty(notes="Should be at least 2 chars", required = true)
    private String firstName;

    @Column(nullable=false, length=50)
    @Size(min=2, message="Last name should be at least 2 chars long")
    @ApiModelProperty(notes="Should be at least 2 chars", required = true)
    private String lastName;

    @Column(nullable=false, length=100)
    @Size(min=8, message="Email should be at least 8 chars long")
    @ApiModelProperty(notes="Should be at least 8 chars", required = true)
    private String email;

    @ManyToOne(fetch=FetchType.LAZY)
    @JsonIgnore
    private Town town;

}
