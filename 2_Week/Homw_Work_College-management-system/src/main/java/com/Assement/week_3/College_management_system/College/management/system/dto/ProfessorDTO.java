package com.Assement.week_3.College_management_system.College.management.system.dto;

import com.Assement.week_3.College_management_system.College.management.system.entities.StudentEntity;
import lombok.*;

import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProfessorDTO {
    private Long id;
    private String title;
    private List<SubjectDTO> subjects;
    private  List<StudentEntity> students;
}



