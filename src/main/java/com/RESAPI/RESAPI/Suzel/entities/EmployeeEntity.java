package com.RESAPI.RESAPI.Suzel.entities;
import com.RESAPI.RESAPI.Suzel.annotations.EmployeeRoleValidation;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Employee")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;
    private  String name;
    private  String email;
    private Integer age;
    private LocalDate dateofJoing;

    @JsonProperty("isActive")
    private  Boolean isActive;
    private  String Role;
}
