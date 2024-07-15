package com.RESAPI.RESAPI.Suzel.dto;
import com.RESAPI.RESAPI.Suzel.annotations.EmployeeRoleValidation;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmployDTO {
  private Long id;

  @NotEmpty(message = "Required field in Employee : name")
  @Size(min = 3 , max =10, message = "range is 3 to 10")
  private  String name;

  @Email(message = "Email Should be a valid email")
  private  String email;
  private Integer age;
  private LocalDate dateofJoing;
  @JsonProperty("isActive")
  private  Boolean isActive;
  @EmployeeRoleValidation
  private  String Role;


}
