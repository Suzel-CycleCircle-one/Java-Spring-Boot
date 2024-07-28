package com.RESAPI.RESAPI.Suzel.dto;

import com.RESAPI.RESAPI.Suzel.annotations.DepatmentTitleValidation;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {

    private Long id;

    @DepatmentTitleValidation
    private String title;

    @JsonProperty("isActive")
    private boolean isActive;
    private LocalDate createdAt;
}
