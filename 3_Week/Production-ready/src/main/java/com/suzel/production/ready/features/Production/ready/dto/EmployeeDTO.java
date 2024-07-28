package com.suzel.production.ready.features.Production.ready.dto;


import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeDTO {

    private Long id;
    private  String name;
    private  String email;
    private Integer age;
    private LocalDate dateofJoing;
    private  Boolean isActive;
    private  String Role;

}
