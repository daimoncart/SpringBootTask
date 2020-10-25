package test.boot.spring.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeDto {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String town;
}
