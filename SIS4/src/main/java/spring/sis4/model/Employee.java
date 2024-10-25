package spring.sis4.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "employees")
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Integer id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private LocalDate dateOfBirth;
}
